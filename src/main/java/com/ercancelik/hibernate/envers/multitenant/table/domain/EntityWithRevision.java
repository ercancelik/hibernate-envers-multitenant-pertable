package com.ercancelik.hibernate.envers.multitenant.table.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntityWithRevision<T> {
    private Long revisionId;

    private Date revisionDate;

    private T entity;
}
