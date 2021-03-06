package com.manager.phanquyenchucnang.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Menu entity.
 */
public class MenuDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String menuCode;

    @NotNull
    private String name;

    @NotNull
    private String icon;

    @NotNull
    private String parentCode;


    private Long screenId;

    private String screenScreenCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
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

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MenuDTO menuDTO = (MenuDTO) o;
        if (menuDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), menuDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
            "id=" + getId() +
            ", menuCode='" + getMenuCode() + "'" +
            ", name='" + getName() + "'" +
            ", icon='" + getIcon() + "'" +
            ", parentCode='" + getParentCode() + "'" +
            ", screen=" + getScreenId() +
            ", screen='" + getScreenScreenCode() + "'" +
            "}";
    }
}
