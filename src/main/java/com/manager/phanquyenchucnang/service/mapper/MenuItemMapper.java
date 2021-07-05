package com.manager.phanquyenchucnang.service.mapper;

import com.manager.phanquyenchucnang.domain.*;
import com.manager.phanquyenchucnang.service.dto.MenuItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MenuItem and its DTO MenuItemDTO.
 */
@Mapper(componentModel = "spring", uses = {ScreenMapper.class, MenuMapper.class})
public interface MenuItemMapper extends EntityMapper<MenuItemDTO, MenuItem> {

    @Mapping(source = "screen.id", target = "screenId")
    @Mapping(source = "screen.name", target = "screenName")
    @Mapping(source = "menu.id", target = "menuId")
    @Mapping(source = "menu.name", target = "menuName")
    MenuItemDTO toDto(MenuItem menuItem);

    @Mapping(target = "acessdenies", ignore = true)
    @Mapping(target = "menuroles", ignore = true)
    @Mapping(source = "screenId", target = "screen")
    @Mapping(source = "menuId", target = "menu")
    MenuItem toEntity(MenuItemDTO menuItemDTO);

    default MenuItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        MenuItem menuItem = new MenuItem();
        menuItem.setId(id);
        return menuItem;
    }
}
