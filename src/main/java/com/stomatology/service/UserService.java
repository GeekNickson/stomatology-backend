package com.stomatology.service;

import com.stomatology.dto.CreateUserDto;
import com.stomatology.dto.UserDto;
import com.stomatology.entity.Account;
import com.stomatology.entity.Role;
import com.stomatology.entity.User;
import com.stomatology.entity.enums.RoleName;
import com.stomatology.mapper.UserMapper;
import com.stomatology.repository.RoleRepository;
import com.stomatology.repository.UserRepository;
import com.stomatology.service.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto create(CreateUserDto createUserDto) {
        User newUser = new User();
        newUser.setFirstName(createUserDto.getFirstName());
        newUser.setLastName(createUserDto.getLastName());
        newUser.setProfilePicture(createUserDto.getProfilePicture());
        newUser.setRole(roleRepository.findByName(RoleName.USER));

        Account userAccount = new Account();
        userAccount.setEmail(createUserDto.getEmail());
        userAccount.setPassword(passwordEncoder.encode(createUserDto.getPassword()));

        newUser.setAccount(userAccount);

        return userMapper.toDto(userRepository.save(newUser));
    }

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto findOne(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
