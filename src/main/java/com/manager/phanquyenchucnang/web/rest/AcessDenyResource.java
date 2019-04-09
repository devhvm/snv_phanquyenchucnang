package com.manager.phanquyenchucnang.web.rest;
import com.manager.phanquyenchucnang.service.AcessDenyService;
import com.manager.phanquyenchucnang.web.rest.errors.BadRequestAlertException;
import com.manager.phanquyenchucnang.web.rest.util.HeaderUtil;
import com.manager.phanquyenchucnang.web.rest.util.PaginationUtil;
import com.manager.phanquyenchucnang.service.dto.AcessDenyDTO;
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
 * REST controller for managing AcessDeny.
 */
@RestController
@RequestMapping("/api")
public class AcessDenyResource {

    private final Logger log = LoggerFactory.getLogger(AcessDenyResource.class);

    private static final String ENTITY_NAME = "phanquyenchucnangAcessDeny";

    private final AcessDenyService acessDenyService;

    public AcessDenyResource(AcessDenyService acessDenyService) {
        this.acessDenyService = acessDenyService;
    }

    /**
     * POST  /acess-denies : Create a new acessDeny.
     *
     * @param acessDenyDTO the acessDenyDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new acessDenyDTO, or with status 400 (Bad Request) if the acessDeny has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/acess-denies")
    public ResponseEntity<AcessDenyDTO> createAcessDeny(@Valid @RequestBody AcessDenyDTO acessDenyDTO) throws URISyntaxException {
        log.debug("REST request to save AcessDeny : {}", acessDenyDTO);
        if (acessDenyDTO.getId() != null) {
            throw new BadRequestAlertException("A new acessDeny cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AcessDenyDTO result = acessDenyService.save(acessDenyDTO);
        return ResponseEntity.created(new URI("/api/acess-denies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /acess-denies : Updates an existing acessDeny.
     *
     * @param acessDenyDTO the acessDenyDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated acessDenyDTO,
     * or with status 400 (Bad Request) if the acessDenyDTO is not valid,
     * or with status 500 (Internal Server Error) if the acessDenyDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/acess-denies")
    public ResponseEntity<AcessDenyDTO> updateAcessDeny(@Valid @RequestBody AcessDenyDTO acessDenyDTO) throws URISyntaxException {
        log.debug("REST request to update AcessDeny : {}", acessDenyDTO);
        if (acessDenyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AcessDenyDTO result = acessDenyService.save(acessDenyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, acessDenyDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /acess-denies : get all the acessDenies.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of acessDenies in body
     */
    @GetMapping("/acess-denies")
    public ResponseEntity<List<AcessDenyDTO>> getAllAcessDenies(Pageable pageable) {
        log.debug("REST request to get a page of AcessDenies");
        Page<AcessDenyDTO> page = acessDenyService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/acess-denies");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /acess-denies/:id : get the "id" acessDeny.
     *
     * @param id the id of the acessDenyDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the acessDenyDTO, or with status 404 (Not Found)
     */
    @GetMapping("/acess-denies/{id}")
    public ResponseEntity<AcessDenyDTO> getAcessDeny(@PathVariable Long id) {
        log.debug("REST request to get AcessDeny : {}", id);
        Optional<AcessDenyDTO> acessDenyDTO = acessDenyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(acessDenyDTO);
    }

    /**
     * DELETE  /acess-denies/:id : delete the "id" acessDeny.
     *
     * @param id the id of the acessDenyDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/acess-denies/{id}")
    public ResponseEntity<Void> deleteAcessDeny(@PathVariable Long id) {
        log.debug("REST request to delete AcessDeny : {}", id);
        acessDenyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
