package com.manager.phanquyenchucnang.client;

import com.manager.phanquyenchucnang.service.dto.CoQuanHanhChinhDTO;
import com.manager.phanquyenchucnang.service.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class QuyTrinhDonViServiceClientFallback implements QuyTrinhDonViServiceClient {
    @Override
    public ResponseEntity<CoQuanHanhChinhDTO> getQuyTrinhDonVi(Long id) throws RuntimeException {
        return null;
    }
}
