package com.suri.board.service;

import com.suri.board.domain.Board;
import com.suri.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(final BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoard() {
        return boardRepository.findAll();
    }

    public Board createBoard(final Board board) {
        return boardRepository.save(board);
    }
}
