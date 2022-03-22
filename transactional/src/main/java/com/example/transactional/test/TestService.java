package com.example.transactional.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    @Transactional(readOnly = true)
    public Test getTest(long id) {
        return testRepository.findById(id).orElse(null);
    }

    public void create() {
        testRepository.save(new Test());
    }
}
