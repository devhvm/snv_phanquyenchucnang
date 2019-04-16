package com.manager.phanquyenchucnang.service.mapper;

import com.manager.phanquyenchucnang.domain.*;
import com.manager.phanquyenchucnang.service.dto.ScreenDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Screen and its DTO ScreenDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ScreenMapper extends EntityMapper<ScreenDTO, Screen> {


    @Mapping(target = "menus", ignore = true)
    Screen toEntity(ScreenDTO screenDTO);

    default Screen fromId(Long id) {
        if (id == null) {
            return null;
        }
        Screen screen = new Screen();
        screen.setId(id);
        return screen;
    }
}
