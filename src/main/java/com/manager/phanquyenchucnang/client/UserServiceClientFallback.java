package com.manager.phanquyenchucnang.client;

import com.manager.phanquyenchucnang.service.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserServiceClientFallback implements UserServiceClient {
    @Override
    public ResponseEntity<List<UserDTO>> getUsers() throws RuntimeException {
        return null;
    }

    @Override
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) throws RuntimeException {
        return null;
    }
}
