package com.manager.phanquyenchucnang.service;

import com.manager.phanquyenchucnang.domain.Screen;
import com.manager.phanquyenchucnang.repository.ScreenRepository;
import com.manager.phanquyenchucnang.service.dto.ScreenDTO;
import com.manager.phanquyenchucnang.service.mapper.ScreenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Screen.
 */
@Service
@Transactional
public class ScreenService {

    private final Logger log = LoggerFactory.getLogger(ScreenService.class);

    private final ScreenRepository screenRepository;

    private final ScreenMapper screenMapper;

    public ScreenService(ScreenRepository screenRepository, ScreenMapper screenMapper) {
        this.screenRepository = screenRepository;
        this.screenMapper = screenMapper;
    }

    /**
     * Save a screen.
     *
     * @param screenDTO the entity to save
     * @return the persisted entity
     */
    public ScreenDTO save(ScreenDTO screenDTO) {
        log.debug("Request to save Screen : {}", screenDTO);
        Screen screen = screenMapper.toEntity(screenDTO);
        screen = screenRepository.save(screen);
        return screenMapper.toDto(screen);
    }

    /**
     * Get all the screens.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ScreenDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Screens");
        return screenRepository.findAll(pageable)
            .map(screenMapper::toDto);
    }


    /**
     * Get one screen by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ScreenDTO> findOne(Long id) {
        log.debug("Request to get Screen : {}", id);
        return screenRepository.findById(id)
            .map(screenMapper::toDto);
    }

    /**
     * Delete the screen by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Screen : {}", id);
        screenRepository.deleteById(id);
    }
}
