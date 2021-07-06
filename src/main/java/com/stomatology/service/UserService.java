package com.stomatology.service;

import com.stomatology.dto.UserDto;
import com.stomatology.dto.create.CreateUserDto;
import com.stomatology.entity.enums.RoleName;
import com.stomatology.entity.user.Account;
import com.stomatology.entity.user.User;
import com.stomatology.mapper.UserMapper;
import com.stomatology.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AccountService accountService;
    private final ImageService imageService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

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

    public UserDto findByAccount(Account account) {
        return userRepository.findByAccount(account)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with given account not found"));
    }
}
