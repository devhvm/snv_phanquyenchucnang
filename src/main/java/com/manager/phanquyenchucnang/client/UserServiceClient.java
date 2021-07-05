package com.manager.phanquyenchucnang.client;

import com.manager.phanquyenchucnang.service.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "gateway", qualifier = "gateway", url = "${snv.gateway:}", path = "api", fallback = UserServiceClientFallback.class)
public interface UserServiceClient {

    @GetMapping("/users")
    ResponseEntity<List<UserDTO>> getUsers() throws RuntimeException;

    @GetMapping("/users/{login}")
    UserDTO getUser(@PathVariable("login") String login) throws RuntimeException;

    @PostMapping("/users")
    ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) throws RuntimeException;
}
