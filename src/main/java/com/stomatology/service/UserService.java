package com.stomatology.service;

import com.stomatology.dto.create.CreateUserDto;
import com.stomatology.entity.enums.RoleName;
import com.stomatology.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AccountService accountService;
    private final ImageService imageService;

    public User create(CreateUserDto createUserDto, RoleName roleName) {
        User user = new User();
        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());

        try {
            user.setProfilePicture(imageService.save(createUserDto.getProfilePicture()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        user.setAccount(accountService.create(createUserDto.getEmail(), createUserDto.getPassword(), roleName));
        return user;
    }
}
