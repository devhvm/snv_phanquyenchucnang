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
    private String menuRoleCode;

    @NotNull
    private String role;

    @NotNull
    private String name;


    private Long menuId;

    private String menuMenuCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuRoleCode() {
        return menuRoleCode;
    }

    public void setMenuRoleCode(String menuRoleCode) {
        this.menuRoleCode = menuRoleCode;
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
            ", menuRoleCode='" + getMenuRoleCode() + "'" +
            ", role='" + getRole() + "'" +
            ", name='" + getName() + "'" +
            ", menu=" + getMenuId() +
            ", menu='" + getMenuMenuCode() + "'" +
            "}";
    }
}
