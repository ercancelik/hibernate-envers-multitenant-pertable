package com.ercancelik.hibernate.envers.multitenant.table.controller;

import com.ercancelik.hibernate.envers.multitenant.table.domain.Customer;
import com.ercancelik.hibernate.envers.multitenant.table.domain.EntityWithRevision;
import com.ercancelik.hibernate.envers.multitenant.table.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customers")
@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(this.customerService.getAll());
    }

    @PostMapping
    public ResponseEntity<Customer> add(@RequestBody Customer customer) {
        return ResponseEntity.ok(this.customerService.add(customer));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> update(@PathVariable String customerId, @RequestBody Customer customer) {
        return ResponseEntity.ok(this.customerService.update(customerId, customer));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> delete(@PathVariable String customerId) {
        this.customerService.delete(customerId);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/{customerId}/history")
    public ResponseEntity<List<EntityWithRevision<Customer>>> getHistory(@PathVariable("customerId") String customerId) {
        List<EntityWithRevision<Customer>> list = customerService.getRevisions(customerId);

        return ResponseEntity.ok(list);
    }
}