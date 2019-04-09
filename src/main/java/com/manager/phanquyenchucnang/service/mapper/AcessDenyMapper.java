package com.manager.phanquyenchucnang.service.mapper;

import com.manager.phanquyenchucnang.domain.*;
import com.manager.phanquyenchucnang.service.dto.AcessDenyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AcessDeny and its DTO AcessDenyDTO.
 */
@Mapper(componentModel = "spring", uses = {MenuMapper.class})
public interface AcessDenyMapper extends EntityMapper<AcessDenyDTO, AcessDeny> {

    @Mapping(source = "menu.id", target = "menuId")
    @Mapping(source = "menu.menuCode", target = "menuMenuCode")
    AcessDenyDTO toDto(AcessDeny acessDeny);

    @Mapping(source = "menuId", target = "menu")
    AcessDeny toEntity(AcessDenyDTO acessDenyDTO);

    default AcessDeny fromId(Long id) {
        if (id == null) {
            return null;
        }
        AcessDeny acessDeny = new AcessDeny();
        acessDeny.setId(id);
        return acessDeny;
    }
}
