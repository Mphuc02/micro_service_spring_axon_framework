class Const{
    constructor(){
        this.serverIp = "172.31.26.128"
        this.quizTaskUrl = `http://${this.serverIp}:9000/api/v1/create-quiz`
        this.frontEndUrl = `http://${this.serverIp}:8888`
        this.serverUrl = `http://${this.serverIp}:9000`
        this.authenticateUrl = {
            auth : this.serverUrl + "/api/v1/auth",
            registerUrl: () => {
                return this.authenticateUrl.auth + "/register"
            },
            authenticateUrl: () => {
                return this.authenticateUrl.auth + "/authentication"
            },
            logOut: () => {
                return this.authenticateUrl.auth + "/logout"
            }
        },
        this.userUrl = {
            user: this.serverUrl + "/api/v1/auth",
            authenticated: () => {
                return this.userUrl.user + "/authenticated"
            }
        },
        this.quiz = {
            url: this.serverUrl + "/api/v1/quiz",
            downloadTemplate: () => {
                return this.quiz.url + "/download-template"
            },
            playQuiz: (id) => {
                return this.quiz.url + `/play/${id}`
            }
        },
        this.jwt = 'jwt',
        this.webSocket = {
            url: this.serverUrl + "/web-socket/create-quiz"
        }
    }
}