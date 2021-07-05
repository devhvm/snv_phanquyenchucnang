package com.manager.phanquyenchucnang.repository;

import com.manager.phanquyenchucnang.domain.DonViNguoiDung;
import com.manager.phanquyenchucnang.domain.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the MenuItem entity.
 */
@Repository
public interface DonViNguoiDungRepository extends JpaRepository<DonViNguoiDung, Long> {
    Optional<DonViNguoiDung> findByLogin(String login);
}
