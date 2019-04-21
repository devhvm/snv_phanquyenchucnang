package com.manager.phanquyenchucnang.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AcessDeny entity.
 */
public class AcessDenyDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String userId;


    private Long menuItemId;

    private String menuItemMenuItemCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getMenuItemMenuItemCode() {
        return menuItemMenuItemCode;
    }

    public void setMenuItemMenuItemCode(String menuItemMenuItemCode) {
        this.menuItemMenuItemCode = menuItemMenuItemCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AcessDenyDTO acessDenyDTO = (AcessDenyDTO) o;
        if (acessDenyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), acessDenyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AcessDenyDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", menuItem=" + getMenuItemId() +
            ", menuItem='" + getMenuItemMenuItemCode() + "'" +
            "}";
    }
}
