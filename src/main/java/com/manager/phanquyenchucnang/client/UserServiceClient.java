package com.manager.phanquyenchucnang.client;

import com.manager.phanquyenchucnang.service.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "gateway", qualifier = "gateway", url = "${snv.gateway:}", path = "api", fallback = UserServiceClientFallback.class)
public interface UserServiceClient {

    @GetMapping("/users")
    ResponseEntity<List<UserDTO>> getUsers() throws RuntimeException;

    @PostMapping("/users")
    ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) throws RuntimeException;
}
