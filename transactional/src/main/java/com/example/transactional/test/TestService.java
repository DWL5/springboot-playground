package com.example.transactional.test;

import com.example.transactional.async.AsyncService;
import com.example.transactional.user.UpdateDto;
import com.example.transactional.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {
    private final UserService userService;
    private final TestRepository testRepository;
    private final AsyncService asyncService;

    @Transactional(readOnly = true)
    public void testTransactionalReadOnlyCallFalse() {
        testRepository.findById(1L);
        userService.register(UUID.randomUUID().toString()); // read-only false
    }

    @Transactional(readOnly = false)
    public void testTransactionalReadOnlyFalseCallTrue() {
        testRepository.save(new Test());
        userService.getUser("test1"); // read-only true
    }


    @Transactional(readOnly = false)
    public void testCallAsyncReadOnly() {
        log.info("TestService called testCallAsyncReadOnly thread : " + Thread.currentThread().getName());
        testRepository.save(new Test());
        asyncService.testAsyncReadOnly("test1");
    }

    @Async
    @Transactional(readOnly = false)
    public void testAsyncTransactionalCallReadOnly() {
        log.info("TestService called testAsyncTransactionalCallReadOnly thread : " + Thread.currentThread().getName());
        userService.getUser("test1"); // read-only true
    }

    @Transactional(readOnly = true)
    public void testReadOnlyCallAsyncReadOnly() {
        log.info("TestService called testReadOnlyCallAsyncReadOnly thread : " + Thread.currentThread().getName());
        testRepository.findById(1L); // read-only true
        asyncService.testAsyncReadOnly("test1");
    }

    @Async
    public void callWithAsync() {
        log.info("TestService called callWithAsync thread : " + Thread.currentThread().getName());
        userService.getUser("test1");
        this.callReadOnlyAsync();
        this.callReadOnlyFalseAsync();
    }


    public void callWithoutAsync() {
        log.info("TestService called callWithoutAsync thread : " + Thread.currentThread().getName());
        userService.getUser("test1");
        this.callReadOnlyAsync();
        this.callReadOnlyFalseAsync();
    }

    @Async
    public void callReadOnlyAsync() {
        log.info("TestService called callReadOnlyAsync thread : " + Thread.currentThread().getName());
        userService.getUser("test1");
    }

    @Async
    public void callReadOnlyFalseAsync() {
        log.info("TestService called callReadOnlyFalseAsync thread : " + Thread.currentThread().getName());
        userService.register(UUID.randomUUID().toString());
    }

    public void callTransactional() {
        this.update();
    }

    public void callTransactionalExternal(UpdateDto updateDto) {
        userService.update(updateDto);
    }

    @Transactional(readOnly = true)
    public Test getTest(long id) {
        return testRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = false)
    public void create() {
        testRepository.save(new Test());
    }

    @Transactional(readOnly = false)
    public void update() {
       Test test = getTest(1L);
       test.change("success");
    }
}
