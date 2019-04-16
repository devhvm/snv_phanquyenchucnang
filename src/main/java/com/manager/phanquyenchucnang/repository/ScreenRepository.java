package com.manager.phanquyenchucnang.repository;

import com.manager.phanquyenchucnang.domain.Screen;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Screen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {

}
