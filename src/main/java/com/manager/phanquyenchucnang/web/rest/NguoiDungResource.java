package com.manager.phanquyenchucnang.web.rest;

import com.manager.phanquyenchucnang.security.AuthoritiesConstants;
import com.manager.phanquyenchucnang.service.DonViNguoiDungService;
import com.manager.phanquyenchucnang.service.UserService;
import com.manager.phanquyenchucnang.service.dto.MenuDTO;
import com.manager.phanquyenchucnang.service.dto.UserDTO;
import com.manager.phanquyenchucnang.web.rest.errors.BadRequestAlertException;
import com.manager.phanquyenchucnang.web.rest.util.HeaderUtil;
import com.manager.phanquyenchucnang.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Menu.
 */
@RestController
@RequestMapping("/api")
public class NguoiDungResource {

    private final Logger log = LoggerFactory.getLogger(NguoiDungResource.class);

    private static final String ENTITY_NAME = "phanquyenchucnangNguoiDung";

    private final DonViNguoiDungService donViNguoiDungService;

    private final UserService userService;

    public NguoiDungResource(DonViNguoiDungService donViNguoiDungService, UserService userService) {
        this.donViNguoiDungService = donViNguoiDungService;
        this.userService = userService;
    }

    /**
     * GET  /menus : get all the users.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of menus in body
     */
    @GetMapping("/nguoiDungs")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        log.debug("REST request to get all Users");
        return userService.findAll();
    }

    /**
     * GET  /menus : get all the users.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of menus in body
     */
    @GetMapping("/nguoiDung/{login}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String login) {
        log.debug("REST request to get User by login");
        Optional<UserDTO> userDTO = userService.getUser(login);
        return ResponseUtil.wrapOrNotFound(userDTO);
    }


    /**
     * POST  /users  : Creates a new user.
     * <p>
     * Creates a new user if the login and email are not already used, and sends an
     * mail with an activation link.
     * The user needs to be activated on creation.
     *
     * @param userDTO the user to create
     * @return the ResponseEntity with status 201 (Created) and with body the new user, or with status 400 (Bad Request) if the login or email is already in use
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws BadRequestAlertException 400 (Bad Request) if the login or email is already in use
     */
    @PostMapping("/nguoiDungs")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
        log.debug("REST request to save User : {}", userDTO);
        UserDTO response = userService.createUser(userDTO);
            return ResponseEntity.created(new URI("/api/nguoiDungs/" + response.getLogin()))
                .headers(HeaderUtil.createAlert( "userManagement.created", response.getLogin()))
                .body(response);
        }
    }
