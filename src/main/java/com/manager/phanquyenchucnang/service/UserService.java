package com.manager.phanquyenchucnang.service;

import com.manager.phanquyenchucnang.client.QuyTrinhDonViServiceClient;
import com.manager.phanquyenchucnang.client.UserServiceClient;
import com.manager.phanquyenchucnang.domain.DonViNguoiDung;
import com.manager.phanquyenchucnang.service.dto.CoQuanHanhChinhDTO;
import com.manager.phanquyenchucnang.service.dto.UserDTO;
import com.manager.phanquyenchucnang.web.rest.errors.InternalServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    @Qualifier("gateway")
    UserServiceClient userServiceClient;

    @Autowired
    @Qualifier("quytrinhdonvi")
    QuyTrinhDonViServiceClient quyTrinhDonViServiceClient;

    private final DonViNguoiDungService donViNguoiDungService;

    public UserService(DonViNguoiDungService donViNguoiDungService) {
        this.donViNguoiDungService = donViNguoiDungService;
    }

    public UserDTO createUser(UserDTO userDTO) {
        log.debug("REST request to create User : {}", userDTO);
        ResponseEntity<UserDTO> responseEntity = userServiceClient.createUser(userDTO);
        if (responseEntity == null)
            throw new InternalServerErrorException("đăng ký user không thành công");
        DonViNguoiDung donViNguoiDung = new DonViNguoiDung();
        donViNguoiDung.setLogin(userDTO.getLogin());
        donViNguoiDung.setCoQuanHanhChinhId(userDTO.getCoQuanHanhChinhId());
        donViNguoiDungService.save(donViNguoiDung);
        return userDTO;
    }

    /**
     * Get all the Users.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public ResponseEntity<List<UserDTO>> findAll() {
        log.debug("Request to get all Users");
        ResponseEntity<List<UserDTO>> users = userServiceClient.getUsers();
        users.getBody().forEach(
            userDTO -> {
                Optional<DonViNguoiDung> donViNguoiDung = donViNguoiDungService.findByLogin(userDTO.getLogin());
                if (donViNguoiDung.isPresent()) {
                    userDTO.setCoQuanHanhChinhId(donViNguoiDung.get().getCoQuanHanhChinhId());

                    ResponseEntity<CoQuanHanhChinhDTO> coQuanHanhChinhDTO = quyTrinhDonViServiceClient.getQuyTrinhDonVi(donViNguoiDung.get().getCoQuanHanhChinhId());
                    if (coQuanHanhChinhDTO != null)
                    userDTO.setCoQuanHanhChinhName(coQuanHanhChinhDTO.getBody().getName());
                }
            }
        );
        return users;
    }

    /**
     * Get all the Users.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Optional<UserDTO> getUser(String login) {
        log.debug("Request to get User by login");
        UserDTO userDTO = userServiceClient.getUser(login);
        Optional<DonViNguoiDung> donViNguoiDung = donViNguoiDungService.findByLogin(login);
        if (donViNguoiDung.isPresent()) {
            userDTO.setCoQuanHanhChinhId(donViNguoiDung.get().getCoQuanHanhChinhId());
            ResponseEntity<CoQuanHanhChinhDTO> coQuanHanhChinhDTO = quyTrinhDonViServiceClient.getQuyTrinhDonVi(donViNguoiDung.get().getCoQuanHanhChinhId());
            if (coQuanHanhChinhDTO != null) {
                userDTO.setCoQuanHanhChinhName(coQuanHanhChinhDTO.getBody().getName());
                userDTO.setCoQuanHanhChinhCode(coQuanHanhChinhDTO.getBody().getCoQuanHanhChinhCode());
            }
        }
        return Optional.of(userDTO);
    }
}
