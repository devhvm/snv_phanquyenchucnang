package com.manager.phanquyenchucnang.repository;

import com.manager.phanquyenchucnang.domain.AcessDeny;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AcessDeny entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcessDenyRepository extends JpaRepository<AcessDeny, Long> {

}
