package com.manager.phanquyenchucnang.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
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

    @OneToMany(mappedBy = "menu")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AcessDeny> acessdenies = new HashSet<>();
    @OneToMany(mappedBy = "menu")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MenuRole> menuroles = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("menus")
    private Screen screen;

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

    public Set<AcessDeny> getAcessdenies() {
        return acessdenies;
    }

    public Menu acessdenies(Set<AcessDeny> acessDenies) {
        this.acessdenies = acessDenies;
        return this;
    }

    public Menu addAcessdeny(AcessDeny acessDeny) {
        this.acessdenies.add(acessDeny);
        acessDeny.setMenu(this);
        return this;
    }

    public Menu removeAcessdeny(AcessDeny acessDeny) {
        this.acessdenies.remove(acessDeny);
        acessDeny.setMenu(null);
        return this;
    }

    public void setAcessdenies(Set<AcessDeny> acessDenies) {
        this.acessdenies = acessDenies;
    }

    public Set<MenuRole> getMenuroles() {
        return menuroles;
    }

    public Menu menuroles(Set<MenuRole> menuRoles) {
        this.menuroles = menuRoles;
        return this;
    }

    public Menu addMenurole(MenuRole menuRole) {
        this.menuroles.add(menuRole);
        menuRole.setMenu(this);
        return this;
    }

    public Menu removeMenurole(MenuRole menuRole) {
        this.menuroles.remove(menuRole);
        menuRole.setMenu(null);
        return this;
    }

    public void setMenuroles(Set<MenuRole> menuRoles) {
        this.menuroles = menuRoles;
    }

    public Screen getScreen() {
        return screen;
    }

    public Menu screen(Screen screen) {
        this.screen = screen;
        return this;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
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
