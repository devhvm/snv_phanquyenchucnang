package com.manager.phanquyenchucnang.service;

import com.manager.phanquyenchucnang.domain.MenuRole;
import com.manager.phanquyenchucnang.repository.MenuRoleRepository;
import com.manager.phanquyenchucnang.service.dto.MenuRoleDTO;
import com.manager.phanquyenchucnang.service.mapper.MenuRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing MenuRole.
 */
@Service
@Transactional
public class MenuRoleService {

    private final Logger log = LoggerFactory.getLogger(MenuRoleService.class);

    private final MenuRoleRepository menuRoleRepository;

    private final MenuRoleMapper menuRoleMapper;

    public MenuRoleService(MenuRoleRepository menuRoleRepository, MenuRoleMapper menuRoleMapper) {
        this.menuRoleRepository = menuRoleRepository;
        this.menuRoleMapper = menuRoleMapper;
    }

    /**
     * Save a menuRole.
     *
     * @param menuRoleDTO the entity to save
     * @return the persisted entity
     */
    public MenuRoleDTO save(MenuRoleDTO menuRoleDTO) {
        log.debug("Request to save MenuRole : {}", menuRoleDTO);
        MenuRole menuRole = menuRoleMapper.toEntity(menuRoleDTO);
        menuRole = menuRoleRepository.save(menuRole);
        return menuRoleMapper.toDto(menuRole);
    }

    /**
     * Get all the menuRoles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<MenuRoleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MenuRoles");
        return menuRoleRepository.findAll(pageable)
            .map(menuRoleMapper::toDto);
    }


    /**
     * Get one menuRole by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<MenuRoleDTO> findOne(Long id) {
        log.debug("Request to get MenuRole : {}", id);
        return menuRoleRepository.findById(id)
            .map(menuRoleMapper::toDto);
    }

    /**
     * Delete the menuRole by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete MenuRole : {}", id);
        menuRoleRepository.deleteById(id);
    }
}
