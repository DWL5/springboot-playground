package com.example.transactional.async;

import com.example.transactional.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class AsyncService {
    private final UserService userService;

    @Async
    @Transactional(readOnly = true)
    public void testAsyncReadOnly(String name) {
        log.info("AsyncService called testAsyncReadOnly thread: " + Thread.currentThread().getName());
        userService.getUser(name);
    }
}
