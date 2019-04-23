package com.manager.phanquyenchucnang.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MenuRole.
 */
@Entity
@Table(name = "menu_role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MenuRole implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "menu_item_role_code", nullable = false)
    private String menuItemRoleCode;

    @NotNull
    @Column(name = "jhi_role", nullable = false)
    private String role;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JsonIgnoreProperties("menuroles")
    private MenuItem menuItem;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuItemRoleCode() {
        return menuItemRoleCode;
    }

    public MenuRole menuItemRoleCode(String menuItemRoleCode) {
        this.menuItemRoleCode = menuItemRoleCode;
        return this;
    }

    public void setMenuItemRoleCode(String menuItemRoleCode) {
        this.menuItemRoleCode = menuItemRoleCode;
    }

    public String getRole() {
        return role;
    }

    public MenuRole role(String role) {
        this.role = role;
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public MenuRole name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public MenuRole menuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
        return this;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
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
        MenuRole menuRole = (MenuRole) o;
        if (menuRole.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), menuRole.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MenuRole{" +
            "id=" + getId() +
            ", menuItemRoleCode='" + getMenuItemRoleCode() + "'" +
            ", role='" + getRole() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
