package com.ercancelik.hibernate.envers.multitenant.table.repository;

import com.ercancelik.hibernate.envers.multitenant.table.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
