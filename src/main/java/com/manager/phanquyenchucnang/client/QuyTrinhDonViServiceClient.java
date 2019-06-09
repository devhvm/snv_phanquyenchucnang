package com.manager.phanquyenchucnang.client;

import com.manager.phanquyenchucnang.service.dto.CoQuanHanhChinhDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "quytrinhdonvi", qualifier = "quytrinhdonvi", url = "${snv.quytrinhdonvi:}", path = "api", fallback = QuyTrinhDonViServiceClientFallback.class)
public interface QuyTrinhDonViServiceClient {

    @GetMapping("/co-quan-hanh-chinhs/{id}")
    ResponseEntity<CoQuanHanhChinhDTO> getQuyTrinhDonVi(@RequestParam("id") Long id) throws RuntimeException;
}
