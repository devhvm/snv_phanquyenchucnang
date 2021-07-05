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
 * A MenuItem.
 */
@Entity
@Table(name = "menu_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MenuItem extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "menu_item_code", nullable = false)
    private String menuItemCode;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "icon")
    private String icon;

    @Column(name = "parrent_code")
    private String parrentCode;

    @Column(name = "ord_number")
    private Integer ordNumber;

    @Column(name = "jhi_link")
    private String link;

    @OneToMany(mappedBy = "menuItem")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AcessDeny> acessdenies = new HashSet<>();
    @OneToMany(mappedBy = "menuItem")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MenuRole> menuroles = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("menus")
    private Screen screen;

    @ManyToOne
    @JsonIgnoreProperties("menuItems")
    private Menu menu;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuItemCode() {
        return menuItemCode;
    }

    public MenuItem menuItemCode(String menuItemCode) {
        this.menuItemCode = menuItemCode;
        return this;
    }

    public void setMenuItemCode(String menuItemCode) {
        this.menuItemCode = menuItemCode;
    }

    public String getName() {
        return name;
    }

    public MenuItem name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public MenuItem icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getParrentCode() {
        return parrentCode;
    }

    public MenuItem parrentCode(String parrentCode) {
        this.parrentCode = parrentCode;
        return this;
    }

    public void setParrentCode(String parrentCode) {
        this.parrentCode = parrentCode;
    }

    public Integer getOrdNumber() {
        return ordNumber;
    }

    public MenuItem ordNumber(Integer ordNumber) {
        this.ordNumber = ordNumber;
        return this;
    }

    public void setOrdNumber(Integer ordNumber) {
        this.ordNumber = ordNumber;
    }

    public String getLink() {
        return link;
    }

    public MenuItem link(String link) {
        this.link = link;
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Set<AcessDeny> getAcessdenies() {
        return acessdenies;
    }

    public MenuItem acessdenies(Set<AcessDeny> acessDenies) {
        this.acessdenies = acessDenies;
        return this;
    }

    public MenuItem addAcessdeny(AcessDeny acessDeny) {
        this.acessdenies.add(acessDeny);
        acessDeny.setMenuItem(this);
        return this;
    }

    public MenuItem removeAcessdeny(AcessDeny acessDeny) {
        this.acessdenies.remove(acessDeny);
        acessDeny.setMenuItem(null);
        return this;
    }

    public void setAcessdenies(Set<AcessDeny> acessDenies) {
        this.acessdenies = acessDenies;
    }

    public Set<MenuRole> getMenuroles() {
        return menuroles;
    }

    public MenuItem menuroles(Set<MenuRole> menuRoles) {
        this.menuroles = menuRoles;
        return this;
    }

    public MenuItem addMenurole(MenuRole menuRole) {
        this.menuroles.add(menuRole);
        menuRole.setMenuItem(this);
        return this;
    }

    public MenuItem removeMenurole(MenuRole menuRole) {
        this.menuroles.remove(menuRole);
        menuRole.setMenuItem(null);
        return this;
    }

    public void setMenuroles(Set<MenuRole> menuRoles) {
        this.menuroles = menuRoles;
    }

    public Screen getScreen() {
        return screen;
    }

    public MenuItem screen(Screen screen) {
        this.screen = screen;
        return this;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Menu getMenu() {
        return menu;
    }

    public MenuItem menu(Menu menu) {
        this.menu = menu;
        return this;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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
        MenuItem menuItem = (MenuItem) o;
        if (menuItem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), menuItem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MenuItem{" +
            "id=" + getId() +
            ", menuItemCode='" + getMenuItemCode() + "'" +
            ", name='" + getName() + "'" +
            ", icon='" + getIcon() + "'" +
            ", parrentCode='" + getParrentCode() + "'" +
            ", ordNumber=" + getOrdNumber() +
            ", link='" + getLink() + "'" +
            "}";
    }
}
