package com.ercancelik.hibernate.envers.multitenant.table.config;

import com.ercancelik.hibernate.envers.multitenant.table.domain.BaseDomain;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class HibernateTenantInterceptor extends EmptyInterceptor {
    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        setTenant(entity);
        return false;
    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        setTenant(entity);
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        setTenant(entity);
        return false;
    }

    private void setTenant(Object entity) {
        if (entity instanceof BaseDomain) {
            ((BaseDomain) entity).setTenantId(TenantContext.getCurrentTenant());
        }
    }
}