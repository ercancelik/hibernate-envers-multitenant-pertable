package com.ercancelik.hibernate.envers.multitenant.table.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Audited
@Entity
@Table(name = "customers")
public class Customer extends BaseDomain{
    private String name;
    private String surname;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
