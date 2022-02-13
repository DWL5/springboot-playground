import React, { Component } from 'react';
import BoardService from '../service/BoardService'

class ListBoardComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            boards: []
        }

        this.createBoard = this.createBoard.bind(this);
    }

    componentDidUpdate() {
        BoardService.getBoard().then((res) => {
            this.setState({boards : res.data});
        })
    }

    componentDidMount() {
        BoardService.getBoard().then((res) => {
            this.setState({boards : res.data});
        })
    }

    createBoard() {
        this.props.history.push('/create-board/');
    }


    test() {
        BoardService.test("Xay7_MWjiPg0m8BVXLoHFWmxjpE3autgp9cnUIsnzhbcVF7WeyVCzvVpRzj-fRUHP3bZwgo9cxcAAAF6stNtvA");
    }
    
    render() {
        return (
            <div>
                <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=d187d243372db29c85e85eef81d4723a&redirect_uri=	
	http://localhost:9000/register/url">
 kakao 로그인 페이지로 이동
                </a>
<a href="https://accounts.google.com/o/oauth2/v2/auth
?client_id=153785509866-72pebue5t5qqcpci2d1bncrh497ootcc.apps.googleusercontent.com&redirect_uri=http://localhost:3000/create-board&response_type=code&scope=https://www.googleapis.com/auth/userinfo.profile email&access_type=offline">
    google 로그인 페이지로 이동
</a>
                   
                <h2 className = "text-center">Boards List</h2>
                <button className="btn btn-primary" onClick = {this.createBoard} >글 작성</button>
                <button className="btn btn-primary" onClick = {this.test} >테스트</button>
                <div className = "row mt-2">
                    <table className = "table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>글 번호</th>
                                <th>타이틀 </th>
                                <th>작성자 </th>
                                <th>작성일 </th>
                                <th>갱신일 </th>
                                <th>좋아요수</th>
                                <th>조회수 </th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.boards.map(
                                    board =>
                                    <tr ket = {board.no}>
                                        <td>{board.no}</td>
                                        <td>{board.title}</td>
                                        <td>{board.memberNo}</td>
                                        <td>{board.createdTime}</td>
                                        <td>{board.updatedTime}</td>
                                        <td>{board.likes}</td>
                                        <td>{board.counts}</td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default ListBoardComponent;