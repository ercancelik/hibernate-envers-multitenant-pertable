package com.ercancelik.hibernate.envers.multitenant.table.service;

import javax.persistence.EntityManager;

public abstract class BaseService {
    private final EntityManager entityManager;

    public BaseService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
