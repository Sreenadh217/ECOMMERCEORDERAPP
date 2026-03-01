package com.example.controller;

import com.example.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private List<User> users = new ArrayList<>();
    private Long nextId = 1L;

    public UserController() {
        users.add(new User(nextId++, "John Doe", "john@example.com"));
        users.add(new User(nextId++, "Jane Smith", "jane@example.com"));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setId(nextId++);
        users.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> user = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();

        if (user.isPresent()) {
            User u = user.get();
            u.setName(userDetails.getName());
            u.setEmail(userDetails.getEmail());
            return ResponseEntity.ok(u);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean removed = users.removeIf(u -> u.getId().equals(id));
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
