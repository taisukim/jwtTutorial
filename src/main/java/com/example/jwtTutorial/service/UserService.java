package com.example.jwtTutorial.service;

import com.example.jwtTutorial.domain.Authority;
import com.example.jwtTutorial.domain.User;
import com.example.jwtTutorial.dto.UserDto;
import com.example.jwtTutorial.repository.UserRepository;
import com.example.jwtTutorial.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User signup(UserDto userDto){
        if(userRepository.findOwnWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null){
            throw new RuntimeException("이미 가입되어있는 유저입니다");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String username){
        return userRepository.findOwnWithAuthoritiesByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities(){
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOwnWithAuthoritiesByUsername);
    }

}
