package com.manager.phanquyenchucnang.web.rest;
import com.manager.phanquyenchucnang.service.ScreenService;
import com.manager.phanquyenchucnang.web.rest.errors.BadRequestAlertException;
import com.manager.phanquyenchucnang.web.rest.util.HeaderUtil;
import com.manager.phanquyenchucnang.web.rest.util.PaginationUtil;
import com.manager.phanquyenchucnang.service.dto.ScreenDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Screen.
 */
@RestController
@RequestMapping("/api")
public class ScreenResource {

    private final Logger log = LoggerFactory.getLogger(ScreenResource.class);

    private static final String ENTITY_NAME = "phanquyenchucnangScreen";

    private final ScreenService screenService;

    public ScreenResource(ScreenService screenService) {
        this.screenService = screenService;
    }

    /**
     * POST  /screens : Create a new screen.
     *
     * @param screenDTO the screenDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new screenDTO, or with status 400 (Bad Request) if the screen has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/screens")
    public ResponseEntity<ScreenDTO> createScreen(@Valid @RequestBody ScreenDTO screenDTO) throws URISyntaxException {
        log.debug("REST request to save Screen : {}", screenDTO);
        if (screenDTO.getId() != null) {
            throw new BadRequestAlertException("A new screen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ScreenDTO result = screenService.save(screenDTO);
        return ResponseEntity.created(new URI("/api/screens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /screens : Updates an existing screen.
     *
     * @param screenDTO the screenDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated screenDTO,
     * or with status 400 (Bad Request) if the screenDTO is not valid,
     * or with status 500 (Internal Server Error) if the screenDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/screens")
    public ResponseEntity<ScreenDTO> updateScreen(@Valid @RequestBody ScreenDTO screenDTO) throws URISyntaxException {
        log.debug("REST request to update Screen : {}", screenDTO);
        if (screenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ScreenDTO result = screenService.save(screenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, screenDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /screens : get all the screens.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of screens in body
     */
    @GetMapping("/screens")
    public ResponseEntity<List<ScreenDTO>> getAllScreens(Pageable pageable) {
        log.debug("REST request to get a page of Screens");
        Page<ScreenDTO> page = screenService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/screens");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /screens/:id : get the "id" screen.
     *
     * @param id the id of the screenDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the screenDTO, or with status 404 (Not Found)
     */
    @GetMapping("/screens/{id}")
    public ResponseEntity<ScreenDTO> getScreen(@PathVariable Long id) {
        log.debug("REST request to get Screen : {}", id);
        Optional<ScreenDTO> screenDTO = screenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(screenDTO);
    }

    /**
     * DELETE  /screens/:id : delete the "id" screen.
     *
     * @param id the id of the screenDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/screens/{id}")
    public ResponseEntity<Void> deleteScreen(@PathVariable Long id) {
        log.debug("REST request to delete Screen : {}", id);
        screenService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
