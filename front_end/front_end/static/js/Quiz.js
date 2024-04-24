
class Quiz{
    constructor(){
        this.const = new Const()
        this.authenticated = new Authenticate()

        let jwt = this.authenticated.getJwtFromCookie()
        if(jwt == null) 
            window.location.href = "/login";
    }

    downloadTemplate = async() => {
        let jwt = this.authenticated.getJwtFromCookie()
        if(jwt == null) 
            return

        $.ajax({
            url: this.const.quiz.downloadTemplate(),
            headers: {
                'Authorization': `Bearer ${jwt}`
            },
            method: 'GET',
            success: (data, textStatus, xhr) => {
                if (xhr.status === 200) {
                    
                } else {
                    // Xử lý trường hợp lỗi khác
                }
            },
            error: function(xhr, textStatus, errorThrown) {
                alert(errorThrown)
            }
        })
    }

    saveNewQuiz = async () => {
        let jwt = this.authenticated.getJwtFromCookie()
        if(jwt == null) 
            return

        let file = $("#question")[0]
        let question
        if(file.files && file.files.length > 0){
            question = file.files[0]
        }
        else{
            return
        }

        var inputs = $("#list-participants").find("input[name='username']");
        var values = inputs.map(function() {
            return $(this).val(); // Lấy giá trị của mỗi input và thêm vào mảng
        }).get();

        let data = {
            'name': $('#name').val(),
            'participants': values,
        }

        let quiz = new FormData()
        quiz.append('file', question)
        quiz.append('quiz', new Blob([JSON.stringify(data)], { type: 'application/json' }))

        $.ajax({
            url: this.const.quizTaskUrl,
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${jwt}`
            },
            processData: false, // Không xử lý dữ liệu
            contentType: false, // Không cung cấp kiểu nội dung
            data: quiz,
            success: (data, textStatus, xhr) => {
                if (xhr.status === 200) {
                    window.location.href = "/invite/" + xhr.responseText.replaceAll('"', '');
                } else {

                }
            },
            error: function(xhr, textStatus, errorThrown) {
                let error = xhr.responseJSON
                if(error.participants != null){
                    alert('Users in below list are not exist: \n' + error.participants)
                }
                else{
                    alert(xhr.responseText)
                }
            }
        })
    } 
}