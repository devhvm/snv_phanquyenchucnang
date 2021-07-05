package com.manager.phanquyenchucnang.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A MenuRole.
 */
@Entity
@Table(name = "don_vi_nguoi_dung")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DonViNguoiDung extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "login", nullable = false)
    private String login;

    @NotNull
    @Column(name = "co_quan_hanh_chinh_id", nullable = false)
    private Long coQuanHanhChinhId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getCoQuanHanhChinhId() {
        return coQuanHanhChinhId;
    }

    public void setCoQuanHanhChinhId(Long coQuanHanhChinhId) {
        this.coQuanHanhChinhId = coQuanHanhChinhId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MenuRole{" +
            "id=" + getId() +
            ", login='" + getLogin() + "'" +
            ", coQuanHanhChinhId='" + getCoQuanHanhChinhId() + "'" +
            "}";
    }
}
