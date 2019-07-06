package com.ercancelik.hibernate.envers.multitenant.table.repository;

import com.ercancelik.hibernate.envers.multitenant.table.domain.Customer;
import com.ercancelik.hibernate.envers.multitenant.table.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByCustomer(Customer customer);
}
