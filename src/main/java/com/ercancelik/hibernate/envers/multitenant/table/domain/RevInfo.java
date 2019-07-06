package com.ercancelik.hibernate.envers.multitenant.table.domain;

import com.ercancelik.hibernate.envers.multitenant.table.config.UetmRevisionListener;
import lombok.Data;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@RevisionEntity(UetmRevisionListener.class)
@Table(name = "rev_info")
public class RevInfo extends DefaultRevisionEntity {
    private String username;
}