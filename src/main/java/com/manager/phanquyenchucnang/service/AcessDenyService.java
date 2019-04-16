package com.manager.phanquyenchucnang.service;

import com.manager.phanquyenchucnang.domain.AcessDeny;
import com.manager.phanquyenchucnang.repository.AcessDenyRepository;
import com.manager.phanquyenchucnang.service.dto.AcessDenyDTO;
import com.manager.phanquyenchucnang.service.mapper.AcessDenyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing AcessDeny.
 */
@Service
@Transactional
public class AcessDenyService {

    private final Logger log = LoggerFactory.getLogger(AcessDenyService.class);

    private final AcessDenyRepository acessDenyRepository;

    private final AcessDenyMapper acessDenyMapper;

    public AcessDenyService(AcessDenyRepository acessDenyRepository, AcessDenyMapper acessDenyMapper) {
        this.acessDenyRepository = acessDenyRepository;
        this.acessDenyMapper = acessDenyMapper;
    }

    /**
     * Save a acessDeny.
     *
     * @param acessDenyDTO the entity to save
     * @return the persisted entity
     */
    public AcessDenyDTO save(AcessDenyDTO acessDenyDTO) {
        log.debug("Request to save AcessDeny : {}", acessDenyDTO);
        AcessDeny acessDeny = acessDenyMapper.toEntity(acessDenyDTO);
        acessDeny = acessDenyRepository.save(acessDeny);
        return acessDenyMapper.toDto(acessDeny);
    }

    /**
     * Get all the acessDenies.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AcessDenyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AcessDenies");
        return acessDenyRepository.findAll(pageable)
            .map(acessDenyMapper::toDto);
    }


    /**
     * Get one acessDeny by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AcessDenyDTO> findOne(Long id) {
        log.debug("Request to get AcessDeny : {}", id);
        return acessDenyRepository.findById(id)
            .map(acessDenyMapper::toDto);
    }

    /**
     * Delete the acessDeny by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AcessDeny : {}", id);
        acessDenyRepository.deleteById(id);
    }
}
