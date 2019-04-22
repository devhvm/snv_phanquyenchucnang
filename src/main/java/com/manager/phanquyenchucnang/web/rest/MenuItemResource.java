package com.manager.phanquyenchucnang.web.rest;
import com.manager.phanquyenchucnang.domain.MenuItem;
import com.manager.phanquyenchucnang.repository.MenuItemRepository;
import com.manager.phanquyenchucnang.web.rest.errors.BadRequestAlertException;
import com.manager.phanquyenchucnang.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final MenuItemRepository menuItemRepository;

    public MenuItemResource(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    /**
     * POST  /menu-items : Create a new menuItem.
     *
     * @param menuItem the menuItem to create
     * @return the ResponseEntity with status 201 (Created) and with body the new menuItem, or with status 400 (Bad Request) if the menuItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/menu-items")
    public ResponseEntity<MenuItem> createMenuItem(@Valid @RequestBody MenuItem menuItem) throws URISyntaxException {
        log.debug("REST request to save MenuItem : {}", menuItem);
        if (menuItem.getId() != null) {
            throw new BadRequestAlertException("A new menuItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MenuItem result = menuItemRepository.save(menuItem);
        return ResponseEntity.created(new URI("/api/menu-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /menu-items : Updates an existing menuItem.
     *
     * @param menuItem the menuItem to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated menuItem,
     * or with status 400 (Bad Request) if the menuItem is not valid,
     * or with status 500 (Internal Server Error) if the menuItem couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/menu-items")
    public ResponseEntity<MenuItem> updateMenuItem(@Valid @RequestBody MenuItem menuItem) throws URISyntaxException {
        log.debug("REST request to update MenuItem : {}", menuItem);
        if (menuItem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MenuItem result = menuItemRepository.save(menuItem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, menuItem.getId().toString()))
            .body(result);
    }

    /**
     * GET  /menu-items : get all the menuItems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of menuItems in body
     */
    @GetMapping("/menu-items")
    public List<MenuItem> getAllMenuItems() {
        log.debug("REST request to get all MenuItems");
        return menuItemRepository.findAll();
    }

    /**
     * GET  /menu-items/:id : get the "id" menuItem.
     *
     * @param id the id of the menuItem to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the menuItem, or with status 404 (Not Found)
     */
    @GetMapping("/menu-items/{id}")
    public ResponseEntity<MenuItem> getMenuItem(@PathVariable Long id) {
        log.debug("REST request to get MenuItem : {}", id);
        Optional<MenuItem> menuItem = menuItemRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(menuItem);
    }

    /**
     * DELETE  /menu-items/:id : delete the "id" menuItem.
     *
     * @param id the id of the menuItem to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/menu-items/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        log.debug("REST request to delete MenuItem : {}", id);
        menuItemRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
