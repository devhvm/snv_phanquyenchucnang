package com.manager.phanquyenchucnang.service.mapper;

import com.manager.phanquyenchucnang.domain.*;
import com.manager.phanquyenchucnang.service.dto.AcessDenyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AcessDeny and its DTO AcessDenyDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AcessDenyMapper extends EntityMapper<AcessDenyDTO, AcessDeny> {



    default AcessDeny fromId(Long id) {
        if (id == null) {
            return null;
        }
        AcessDeny acessDeny = new AcessDeny();
        acessDeny.setId(id);
        return acessDeny;
    }
}
