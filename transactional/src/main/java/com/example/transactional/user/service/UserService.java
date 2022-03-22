package com.example.transactional.user.service;

import com.example.transactional.test.TestService;
import com.example.transactional.user.RegisterDto;
import com.example.transactional.user.UpdateDto;
import com.example.transactional.user.domain.User;
import com.example.transactional.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TestService testService;

    @Transactional(readOnly = true)
    public User getUser(String name) {
        testService.create(); // read-only false
        return userRepository.findByName(name);
    }

    @Transactional(readOnly = false)
    public void register(RegisterDto registerDto) {
        testService.getTest(1L); // read-only true
        userRepository.save(new User(registerDto.getName()));
    }

    @Transactional(readOnly = false)
    public void update(UpdateDto updateDto) {
        User user = getUser(updateDto.getCurrentName());
        user.changeName(updateDto.getUpdatedName());
    }
}
