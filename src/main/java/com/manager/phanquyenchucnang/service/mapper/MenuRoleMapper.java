package com.manager.phanquyenchucnang.service.mapper;

import com.manager.phanquyenchucnang.domain.*;
import com.manager.phanquyenchucnang.service.dto.MenuRoleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MenuRole and its DTO MenuRoleDTO.
 */
@Mapper(componentModel = "spring", uses = {MenuItemMapper.class})
public interface MenuRoleMapper extends EntityMapper<MenuRoleDTO, MenuRole> {

    @Mapping(source = "menuItem.id", target = "menuItemId")
    @Mapping(source = "menuItem.menuItemCode", target = "menuItemMenuItemCode")
    MenuRoleDTO toDto(MenuRole menuRole);

    @Mapping(source = "menuItemId", target = "menuItem")
    MenuRole toEntity(MenuRoleDTO menuRoleDTO);

    default MenuRole fromId(Long id) {
        if (id == null) {
            return null;
        }
        MenuRole menuRole = new MenuRole();
        menuRole.setId(id);
        return menuRole;
    }
}
