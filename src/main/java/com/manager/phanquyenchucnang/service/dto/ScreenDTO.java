package com.manager.phanquyenchucnang.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Screen entity.
 */
public class ScreenDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String screenCode;

    @NotNull
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScreenCode() {
        return screenCode;
    }

    public void setScreenCode(String screenCode) {
        this.screenCode = screenCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ScreenDTO screenDTO = (ScreenDTO) o;
        if (screenDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), screenDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ScreenDTO{" +
            "id=" + getId() +
            ", screenCode='" + getScreenCode() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
