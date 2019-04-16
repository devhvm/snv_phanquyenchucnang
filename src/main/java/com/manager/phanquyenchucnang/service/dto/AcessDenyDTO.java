package com.manager.phanquyenchucnang.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AcessDeny entity.
 */
public class AcessDenyDTO implements Serializable {

    private Long id;

    @NotNull
    private String userId;


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
            "}";
    }
}
