package com.ercancelik.hibernate.envers.multitenant.table.config;

import com.ercancelik.hibernate.envers.multitenant.table.domain.RevInfo;
import org.hibernate.envers.RevisionListener;

public class UetmRevisionListener implements RevisionListener {

    public final static String USERNAME = "ADMINISTRATOR";

    @Override
    public void newRevision(Object revisionEntity) {
        RevInfo exampleRevEntity = (RevInfo) revisionEntity;
        //should be retrieved from spring security
        exampleRevEntity.setUsername(USERNAME);
    }
}