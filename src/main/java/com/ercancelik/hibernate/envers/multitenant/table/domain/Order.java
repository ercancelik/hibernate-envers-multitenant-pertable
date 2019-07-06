package com.ercancelik.hibernate.envers.multitenant.table.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Audited
@Entity
@Table(name = "orders")
public class Order extends BaseDomain{
    private Date orderDate;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="customer_id")
    private Customer customer;
}
