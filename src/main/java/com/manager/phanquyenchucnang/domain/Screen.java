package com.manager.phanquyenchucnang.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Screen.
 */
@Entity
@Table(name = "screen")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Screen extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "screen_code", nullable = false)
    private String screenCode;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "screen")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MenuItem> menus = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScreenCode() {
        return screenCode;
    }

    public Screen screenCode(String screenCode) {
        this.screenCode = screenCode;
        return this;
    }

    public void setScreenCode(String screenCode) {
        this.screenCode = screenCode;
    }

    public String getName() {
        return name;
    }

    public Screen name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MenuItem> getMenus() {
        return menus;
    }

    public Screen menus(Set<MenuItem> menuItems) {
        this.menus = menuItems;
        return this;
    }

    public Screen addMenu(MenuItem menuItem) {
        this.menus.add(menuItem);
        menuItem.setScreen(this);
        return this;
    }

    public Screen removeMenu(MenuItem menuItem) {
        this.menus.remove(menuItem);
        menuItem.setScreen(null);
        return this;
    }

    public void setMenus(Set<MenuItem> menuItems) {
        this.menus = menuItems;
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
        Screen screen = (Screen) o;
        if (screen.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), screen.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Screen{" +
            "id=" + getId() +
            ", screenCode='" + getScreenCode() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
