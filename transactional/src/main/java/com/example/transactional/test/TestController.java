package com.example.transactional.test;

import com.example.transactional.user.RegisterDto;
import com.example.transactional.user.UpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/transactional1")
    public ResponseEntity<Void> testTransactionalReadOnlyCallFalse() {
        testService.testTransactionalReadOnlyCallFalse();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transactional2")
    public ResponseEntity<Void> testTransactionalReadOnlyFalseCallTrue() {
        testService.testTransactionalReadOnlyFalseCallTrue();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transactional3")
    public ResponseEntity<Void> testCallTransaction() {
        testService.callTransactional();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transactional4")
    public ResponseEntity<Void> testCallModifyTransaction(@RequestBody UpdateDto updateDto) {
        testService.callTransactionalExternal(updateDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/async1")
    public ResponseEntity<Void> testCallAsyncReadOnly() {
        testService.testCallAsyncReadOnly();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/async2")
    public ResponseEntity<Void> testAsyncTransactionalCallReadOnly() {
        testService.testAsyncTransactionalCallReadOnly();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/async3")
    public ResponseEntity<Void> testReadOnlyCallAsyncReadOnly() {
        testService.testReadOnlyCallAsyncReadOnly();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/async4")
    public ResponseEntity<Void> testCallWithAsync() {
        testService.callWithAsync();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/async5")
    public ResponseEntity<Void> testCallWithOutAsync() {
        testService.callWithoutAsync();
        return ResponseEntity.ok().build();
    }
}
