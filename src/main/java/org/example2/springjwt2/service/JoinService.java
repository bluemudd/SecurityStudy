package org.example2.springjwt2.service;

import org.example2.springjwt2.dto.JoinDTO;
import org.example2.springjwt2.entity.UserEntity;
import org.example2.springjwt2.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public void joinProcess(JoinDTO joinDTO){

        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();

        if(!userRepository.existsByUsername(username)){
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setPassword(bCryptPasswordEncoder.encode(password));
            userEntity.setRole("ROLE_ADMIN");
            userRepository.save(userEntity);
        }
    }
}
