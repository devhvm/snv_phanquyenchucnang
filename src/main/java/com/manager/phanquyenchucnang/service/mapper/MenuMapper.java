package com.manager.phanquyenchucnang.service.mapper;

import com.manager.phanquyenchucnang.domain.*;
import com.manager.phanquyenchucnang.service.dto.MenuDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Menu and its DTO MenuDTO.
 */
@Mapper(componentModel = "spring", uses = {ScreenMapper.class})
public interface MenuMapper extends EntityMapper<MenuDTO, Menu> {

    @Mapping(source = "screen.id", target = "screenId")
    @Mapping(source = "screen.screenCode", target = "screenScreenCode")
    MenuDTO toDto(Menu menu);

    @Mapping(target = "acessdenies", ignore = true)
    @Mapping(target = "menuroles", ignore = true)
    @Mapping(source = "screenId", target = "screen")
    Menu toEntity(MenuDTO menuDTO);

    default Menu fromId(Long id) {
        if (id == null) {
            return null;
        }
        Menu menu = new Menu();
        menu.setId(id);
        return menu;
    }
}
