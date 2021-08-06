package com.impelitsolutions.service.idm.service;

import com.impelitsolutions.service.common.utils.Utils;
import com.impelitsolutions.service.idm.payload.User;
import com.impelitsolutions.service.idm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserDetails(String userId) {
        if (!Utils.isOk(userId)) {
            return new User();
        }
        Optional<com.impelitsolutions.service.idm.model.User> userEo = userRepository.findUserByUsername(userId);
        if (userEo.isPresent()){
            User userDto = new User(userEo.get());
//            BeanUtils.copyProperties(userEo.get(),userDto);
            return userDto;
        }
        return new User();
    }
}
