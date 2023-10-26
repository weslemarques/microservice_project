package com.ms.user.service;

import com.ms.user.dtos.UserDTO;
import com.ms.user.dtos.UserResponseDTO;
import com.ms.user.model.UserModel;
import com.ms.user.producer.UserProducer;
import com.ms.user.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserProducer userProducer;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    public UserResponseDTO saveUser(UserDTO userDTO) {
        var userEntity = new UserModel();
                BeanUtils.copyProperties(userDTO, userEntity);
        userEntity = userRepository.save(userEntity);

        userProducer.publishMessageEmail(userEntity);

        return new UserResponseDTO(userEntity);
    }
}
