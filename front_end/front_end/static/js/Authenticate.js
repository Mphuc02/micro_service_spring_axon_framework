class Authenticate{
    constructor(){
        this.constranst = new Const()
        this.authenticatedUser = null
        this.getAuthenticatedUser()
        this.signInOption = $("#sigin-option")[0]
        this.avatarOption = $('#avatar-option')[0]

    }
    
    authentcicate = async () => {
        $("#error").innerHTML = ''

        const authenticateData = {
            userName: document.querySelector('#username').value,
            passWord: document.querySelector('#password').value
        }
        console.log(authenticateData)

        $.ajax({
            url: this.constranst.authenticateUrl.authenticateUrl(),
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(authenticateData),
            success: (data, textStatus, xhr) => {
                if (xhr.status === 200) {   
                    document.cookie = `jwt=${data}; domain=${this.constranst.serverIp} ; max-age=${3600}; path=/`;
                    window.location.href = '/'
                } else {
                    // Xử lý trường hợp lỗi khác
                }
            },
            error: function(xhr, textStatus, errorThrown) {
                $("#error").html('User name or password incorrect');
            }
        });
        
    }

    logOut = async () => {
        const response = await fetch(this.constranst.authenticateUrl.logOut(), {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        })  

        // Đặt thời gian hết hạn (expires) là một ngày trước đây
        const pastDate = new Date();
        pastDate.setTime(pastDate.getTime() - 1);
        // Đặt giá trị cookie thành một chuỗi rỗng và thời gian hết hạn là một ngày trước đây
        document.cookie = `jwt=; expires=${pastDate.toGMTString()}; path=/`;
        // Hoặc chuyển đến trang cùng domain
        window.location.href = "/";
    }

    // Lấy JWT từ cookie
    getJwtFromCookie = () => {
        const cookies = document.cookie.split(';');
        for (const cookie of cookies) {
            const [name, value] = cookie.trim().split('=');
            if (name === 'jwt') {
                return value;
            }
        }
        return null;
    }

    getAuthenticatedUser = async () => {
        let jwtToken = this.getJwtFromCookie()

        if(jwtToken == null)
            return

        $.ajax({
            url: this.constranst.userUrl.authenticated(),
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            },
            type: 'GET',
            success: (data, status, xhr) => {
                this.authenticatedUser = data
                this.avatarOption.style.display = 'block'
                this.signInOption.style.display = 'none'
            },
            error(xhr,status,error){
                console.log(error)
            }
        })
    }
}