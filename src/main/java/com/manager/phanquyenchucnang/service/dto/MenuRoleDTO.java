package com.manager.phanquyenchucnang.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MenuRole entity.
 */
public class MenuRoleDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String menuItemRoleCode;

    @NotNull
    private String role;

    @NotNull
    private String name;


    private Long menuItemId;

    private String menuItemName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuItemRoleCode() {
        return menuItemRoleCode;
    }

    public void setMenuItemRoleCode(String menuItemRoleCode) {
        this.menuItemRoleCode = menuItemRoleCode;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MenuRoleDTO menuRoleDTO = (MenuRoleDTO) o;
        if (menuRoleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), menuRoleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MenuRoleDTO{" +
            "id=" + getId() +
            ", menuItemRoleCode='" + getMenuItemRoleCode() + "'" +
            ", role='" + getRole() + "'" +
            ", name='" + getName() + "'" +
            ", menuItem=" + getMenuItemId() +
            ", menuItem='" + getMenuItemName() + "'" +
            "}";
    }
}
