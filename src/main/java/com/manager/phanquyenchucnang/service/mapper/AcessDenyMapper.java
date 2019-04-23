package com.manager.phanquyenchucnang.service.mapper;

import com.manager.phanquyenchucnang.domain.*;
import com.manager.phanquyenchucnang.service.dto.AcessDenyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AcessDeny and its DTO AcessDenyDTO.
 */
@Mapper(componentModel = "spring", uses = {MenuItemMapper.class})
public interface AcessDenyMapper extends EntityMapper<AcessDenyDTO, AcessDeny> {

    @Mapping(source = "menuItem.id", target = "menuItemId")
    AcessDenyDTO toDto(AcessDeny acessDeny);

    @Mapping(source = "menuItemId", target = "menuItem")
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
