package com.manager.phanquyenchucnang.service.mapper;

import com.manager.phanquyenchucnang.domain.*;
import com.manager.phanquyenchucnang.service.dto.MenuRoleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MenuRole and its DTO MenuRoleDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MenuRoleMapper extends EntityMapper<MenuRoleDTO, MenuRole> {



    default MenuRole fromId(Long id) {
        if (id == null) {
            return null;
        }
        MenuRole menuRole = new MenuRole();
        menuRole.setId(id);
        return menuRole;
    }
}
