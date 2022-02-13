import axios from 'axios';

const BOARD_API_BASE_URL = "http://localhost:8080/api/board";

class BoardService {

    getBoard() {
        return axios.get(BOARD_API_BASE_URL);
    }

    createBoard(board) {
        return axios.post(BOARD_API_BASE_URL, board);
    }

    login(code) {
        return axios.get("http://localhost:8080/oauth2/login/KAKAO?code="+code)
    }

    ready(code) {
        return axios.get("http://localhost:8080/signup/", {
            "email" : "ssop5403",
            "oauthType" : "KAKAO",
            "nickname" : "nickname",
            "pageName" : "pageNAme" 
        })
    }

    test(code) {
        //return axios.get("https://thank-you-for-test.kro.kr/members/curations")
        return axios.get("https://thank-you-for-test.kro.kr/oauth2/signup/ready/KAKAO?code="+code)
    }
}

export default new BoardService();