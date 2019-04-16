package com.manager.phanquyenchucnang.repository;

import com.manager.phanquyenchucnang.domain.MenuRole;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MenuRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MenuRoleRepository extends JpaRepository<MenuRole, Long> {

}
