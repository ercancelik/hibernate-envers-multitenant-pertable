package com.ercancelik.hibernate.envers.multitenant.table.config;

import com.ercancelik.hibernate.envers.multitenant.table.service.BaseService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {
    @Before("execution(* com.ercancelik.hibernate.envers.multitenant.table.service.*.*(..)) && target(service)")
    public void aroundExecution(JoinPoint pjp, BaseService service) {
        org.hibernate.Filter filter = service.getEntityManager().unwrap(Session.class).enableFilter("tenantFilter");
        filter.setParameter("tenantId", TenantContext.getCurrentTenant());
        filter.validate();
    }
}
