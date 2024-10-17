package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.DTO.UserDataDTO;
import com.grocipes_backend.grocipes.models.UserEntity;
import com.grocipes_backend.grocipes.repositories.UserEntityRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserDataService {

    UserEntityRepository userEntityRepository;

    public UserDataService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public UserDataDTO getUserByEmail(String email){
        UserEntity user = this.userEntityRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("Email not found"));
        return new UserDataDTO(user.getId(), user.getName(), user.getSurname(), user.getEmail(),user.getGender(), user.getBirthday());
    }

}
