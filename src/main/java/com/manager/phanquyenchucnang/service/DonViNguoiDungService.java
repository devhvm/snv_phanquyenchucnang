package com.manager.phanquyenchucnang.service;

import com.manager.phanquyenchucnang.domain.DonViNguoiDung;
import com.manager.phanquyenchucnang.repository.DonViNguoiDungRepository;
import com.manager.phanquyenchucnang.service.dto.MenuItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Menu.
 */
@Service
@Transactional
public class DonViNguoiDungService {
    private final Logger log = LoggerFactory.getLogger(DonViNguoiDungService.class);

    private final DonViNguoiDungRepository donViNguoiDungRepository;

    public DonViNguoiDungService(DonViNguoiDungRepository donViNguoiDungRepository) {
        this.donViNguoiDungRepository = donViNguoiDungRepository;
    }

    /**
     * Save a donViNguoiDung.
     *
     * @param donViNguoiDung the entity to save
     * @return the persisted entity
     */
    public DonViNguoiDung save(DonViNguoiDung donViNguoiDung) {
        log.debug("Request to save don vi nguoi dung : {}", donViNguoiDung);
        return donViNguoiDungRepository.save(donViNguoiDung);
    }

    /**
     * Get one co quan hanh chinh by login.
     *
     * @param login the login of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<DonViNguoiDung> findByLogin(String login) {
        log.debug("Request to get coQuanHanhChinhCode : {}", login);
        return donViNguoiDungRepository.findByLogin(login);
    }
}
