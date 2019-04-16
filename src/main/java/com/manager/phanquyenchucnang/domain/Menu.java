package com.manager.phanquyenchucnang.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Menu.
 */
@Entity
@Table(name = "menu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Menu extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "menu_code", nullable = false)
    private String menuCode;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "icon", nullable = false)
    private String icon;

    @NotNull
    @Column(name = "parent_code", nullable = false)
    private String parentCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public Menu menuCode(String menuCode) {
        this.menuCode = menuCode;
        return this;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getName() {
        return name;
    }

    public Menu name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public Menu icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getParentCode() {
        return parentCode;
    }

    public Menu parentCode(String parentCode) {
        this.parentCode = parentCode;
        return this;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Menu menu = (Menu) o;
        if (menu.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), menu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Menu{" +
            "id=" + getId() +
            ", menuCode='" + getMenuCode() + "'" +
            ", name='" + getName() + "'" +
            ", icon='" + getIcon() + "'" +
            ", parentCode='" + getParentCode() + "'" +
            "}";
    }
}
