package com.ercancelik.hibernate.envers.multitenant.table.service;

import com.ercancelik.hibernate.envers.multitenant.table.domain.Customer;
import com.ercancelik.hibernate.envers.multitenant.table.domain.EntityWithRevision;
import com.ercancelik.hibernate.envers.multitenant.table.domain.Order;
import com.ercancelik.hibernate.envers.multitenant.table.repository.CustomerRepository;
import com.ercancelik.hibernate.envers.multitenant.table.repository.OrderRepository;
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
public class OrderService extends BaseService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(EntityManager entityManager, OrderRepository orderRepository, CustomerRepository customerRepository) {
        super(entityManager);
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Order add(String customerId, Order order) {
        Optional<Customer> customer = this.customerRepository.findById(customerId);

        if (customer.isPresent()) {
            order.setCustomer(customer.get());
        }
        return this.orderRepository.save(order);
    }

    @Transactional
    public Order update(String customerId, String orderId, Order order) {
        Optional<Customer> customer = this.customerRepository.findById(customerId);

        if (customer.isPresent()) {
            Optional<Order> temp = orderRepository.findById(orderId);
            if (temp.isPresent()) {
                temp.get().setOrderDate(new Date());
                return temp.get();
            }
        }

        return null;
    }

    public List<Order> getAll(String customerId) {
        Optional<Customer> customer = this.customerRepository.findById(customerId);

        if (customer.isPresent()) {
            return this.orderRepository.findByCustomer(customer.get());
        }
        return null;
    }

    @Transactional
    public void delete(String customerId, String orderId) {
        Optional<Customer> customer = this.customerRepository.findById(customerId);

        if (customer.isPresent()) {
            Optional<Order> temp = this.orderRepository.findById(orderId);
            if (temp.isPresent()) {
                this.orderRepository.delete(temp.get());
            }
        }
    }

    public List<EntityWithRevision<Order>> getRevisions(final String customerId, final String orderId) {
        Optional<Customer> customer = this.customerRepository.findById(customerId);

        if (customer.isPresent()) {
            AuditReader auditReader = AuditReaderFactory.get(getEntityManager());
            Optional<Order> ord = this.orderRepository.findById(orderId);
            if (ord.isPresent()) {
                List<Number> revisions = auditReader.getRevisions(Order.class, orderId);

                List<EntityWithRevision<Order>> orderRevisions = new ArrayList<>();
                for (Number revision : revisions) {
                    Order o = auditReader.find(Order.class, orderId, revision);

                    Date revisionDate = auditReader.getRevisionDate(revision);
                    orderRevisions.add(new EntityWithRevision(revision.longValue(), revisionDate, o));
                }
                return orderRevisions;
            }
        }

        return null;
    }
}
