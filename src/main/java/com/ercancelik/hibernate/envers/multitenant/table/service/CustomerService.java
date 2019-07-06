package com.ercancelik.hibernate.envers.multitenant.table.service;

import com.ercancelik.hibernate.envers.multitenant.table.domain.Customer;
import com.ercancelik.hibernate.envers.multitenant.table.domain.EntityWithRevision;
import com.ercancelik.hibernate.envers.multitenant.table.repository.CustomerRepository;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService extends BaseService {
    private final CustomerRepository customerRepository;

    public CustomerService(EntityManager entityManager, CustomerRepository customerRepository) {
        super(entityManager);
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Customer add(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Transactional
    public Customer update(String customerId, Customer customer) {
        Optional<Customer> temp = this.customerRepository.findById(customerId);
        if (temp.isPresent()) {
            temp.get().setName(customer.getName());
            temp.get().setSurname(customer.getSurname());
            return temp.get();
        }
        return null;
    }

    public List<Customer> getAll() {
        return this.customerRepository.findAll();
    }

    @Transactional
    public void delete(String customerId) {
        Optional<Customer> temp = this.customerRepository.findById(customerId);
        if (temp.isPresent()) {
            this.customerRepository.delete(temp.get());
        }
    }

    public List<EntityWithRevision<Customer>> getRevisions(final String id) {
        AuditReader auditReader = AuditReaderFactory.get(getEntityManager());
        Optional<Customer> cust = this.customerRepository.findById(id);
        if (cust.isPresent()) {
            List<Number> revisions = auditReader.getRevisions(Customer.class, id);

            List<EntityWithRevision<Customer>> customerRevisions = new ArrayList<>();
            for (Number revision : revisions) {
                Customer c = auditReader.find(Customer.class, id, revision);

                Date revisionDate = auditReader.getRevisionDate(revision);
                customerRevisions.add(new EntityWithRevision(revision.longValue(), revisionDate, c));
            }
            return customerRevisions;
        }
        return null;
    }
}
