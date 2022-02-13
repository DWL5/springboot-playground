package com.suri.board.infra;

import com.suri.board.domain.Board;
import com.suri.board.repository.BoardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DummyDataConfiguration implements CommandLineRunner {

    private final BoardRepository boardRepository;

    public DummyDataConfiguration(final BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public void run(final String... args) throws Exception {
        boardRepository.save(new Board());
    }
}
