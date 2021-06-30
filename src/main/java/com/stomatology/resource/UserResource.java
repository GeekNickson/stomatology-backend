package com.stomatology.resource;

import com.stomatology.dto.CreateUserDto;
import com.stomatology.dto.UserDto;
import com.stomatology.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.ok().body(userService.create(createUserDto));
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findOne(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
