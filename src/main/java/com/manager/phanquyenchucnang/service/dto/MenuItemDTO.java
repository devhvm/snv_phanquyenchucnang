package com.manager.phanquyenchucnang.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MenuItem entity.
 */
public class MenuItemDTO implements Serializable {

    private Long id;

    @NotNull
    private String menuItemCode;

    @NotNull
    private String name;

    @NotNull
    private String icon;


    private Long screenId;

    private String screenScreenCode;

    private Long menuId;

    private String menuMenuCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuItemCode() {
        return menuItemCode;
    }

    public void setMenuItemCode(String menuItemCode) {
        this.menuItemCode = menuItemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getScreenId() {
        return screenId;
    }

    public void setScreenId(Long screenId) {
        this.screenId = screenId;
    }

    public String getScreenScreenCode() {
        return screenScreenCode;
    }

    public void setScreenScreenCode(String screenScreenCode) {
        this.screenScreenCode = screenScreenCode;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuMenuCode() {
        return menuMenuCode;
    }

    public void setMenuMenuCode(String menuMenuCode) {
        this.menuMenuCode = menuMenuCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MenuItemDTO menuItemDTO = (MenuItemDTO) o;
        if (menuItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), menuItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MenuItemDTO{" +
            "id=" + getId() +
            ", menuItemCode='" + getMenuItemCode() + "'" +
            ", name='" + getName() + "'" +
            ", icon='" + getIcon() + "'" +
            ", screen=" + getScreenId() +
            ", screen='" + getScreenScreenCode() + "'" +
            ", menu=" + getMenuId() +
            ", menu='" + getMenuMenuCode() + "'" +
            "}";
    }
}
