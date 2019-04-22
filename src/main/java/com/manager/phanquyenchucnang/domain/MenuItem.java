package com.manager.phanquyenchucnang.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
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
