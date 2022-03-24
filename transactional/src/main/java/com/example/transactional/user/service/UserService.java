package com.example.transactional.user.service;

import com.example.transactional.user.RegisterDto;
import com.example.transactional.user.UpdateDto;
import com.example.transactional.user.domain.User;
import com.example.transactional.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getUser(String name) {
        log.info("UserService called getUser thread : " + Thread.currentThread().getName());
        return userRepository.findByName(name);
    }

    @Transactional(readOnly = false)
    public void register(RegisterDto registerDto) {
        userRepository.save(new User(registerDto.getName()));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void register(String name) {
        userRepository.save(new User(name));
    }

    @Transactional(readOnly = false)
    public void update(UpdateDto updateDto) {
        User user = getUser(updateDto.getCurrentName());
        user.changeName(updateDto.getUpdatedName());
    }
}
