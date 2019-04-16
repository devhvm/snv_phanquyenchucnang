package com.manager.phanquyenchucnang.web.rest;
import com.manager.phanquyenchucnang.service.MenuItemService;
import com.manager.phanquyenchucnang.web.rest.errors.BadRequestAlertException;
import com.manager.phanquyenchucnang.web.rest.util.HeaderUtil;
import com.manager.phanquyenchucnang.web.rest.util.PaginationUtil;
import com.manager.phanquyenchucnang.service.dto.MenuItemDTO;
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
 * REST controller for managing MenuItem.
 */
@RestController
@RequestMapping("/api")
public class MenuItemResource {

    private final Logger log = LoggerFactory.getLogger(MenuItemResource.class);

    private static final String ENTITY_NAME = "phanquyenchucnangMenuItem";

    private final MenuItemService menuItemService;

    public MenuItemResource(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    /**
     * POST  /menu-items : Create a new menuItem.
     *
     * @param menuItemDTO the menuItemDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new menuItemDTO, or with status 400 (Bad Request) if the menuItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/menu-items")
    public ResponseEntity<MenuItemDTO> createMenuItem(@Valid @RequestBody MenuItemDTO menuItemDTO) throws URISyntaxException {
        log.debug("REST request to save MenuItem : {}", menuItemDTO);
        if (menuItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new menuItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MenuItemDTO result = menuItemService.save(menuItemDTO);
        return ResponseEntity.created(new URI("/api/menu-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /menu-items : Updates an existing menuItem.
     *
     * @param menuItemDTO the menuItemDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated menuItemDTO,
     * or with status 400 (Bad Request) if the menuItemDTO is not valid,
     * or with status 500 (Internal Server Error) if the menuItemDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/menu-items")
    public ResponseEntity<MenuItemDTO> updateMenuItem(@Valid @RequestBody MenuItemDTO menuItemDTO) throws URISyntaxException {
        log.debug("REST request to update MenuItem : {}", menuItemDTO);
        if (menuItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MenuItemDTO result = menuItemService.save(menuItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, menuItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /menu-items : get all the menuItems.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menuItems in body
     */
    @GetMapping("/menu-items")
    public ResponseEntity<List<MenuItemDTO>> getAllMenuItems(Pageable pageable) {
        log.debug("REST request to get a page of MenuItems");
        Page<MenuItemDTO> page = menuItemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/menu-items");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /menu-items/:id : get the "id" menuItem.
     *
     * @param id the id of the menuItemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the menuItemDTO, or with status 404 (Not Found)
     */
    @GetMapping("/menu-items/{id}")
    public ResponseEntity<MenuItemDTO> getMenuItem(@PathVariable Long id) {
        log.debug("REST request to get MenuItem : {}", id);
        Optional<MenuItemDTO> menuItemDTO = menuItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(menuItemDTO);
    }

    /**
     * DELETE  /menu-items/:id : delete the "id" menuItem.
     *
     * @param id the id of the menuItemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/menu-items/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        log.debug("REST request to delete MenuItem : {}", id);
        menuItemService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
