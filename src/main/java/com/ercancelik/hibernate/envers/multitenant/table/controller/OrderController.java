package com.ercancelik.hibernate.envers.multitenant.table.controller;

import com.ercancelik.hibernate.envers.multitenant.table.domain.EntityWithRevision;
import com.ercancelik.hibernate.envers.multitenant.table.domain.Order;
import com.ercancelik.hibernate.envers.multitenant.table.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/customers")
@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<List<Order>> getAll(@PathVariable String customerId) {
        return ResponseEntity.ok(this.orderService.getAll(customerId));
    }

    @PostMapping("/{customerId}/orders")
    public ResponseEntity<Order> add(@PathVariable String customerId, @RequestBody Order order) {
        Order persistedOrder = this.orderService.add(customerId, order);
        Optional.ofNullable(persistedOrder).ifPresent(o -> Optional.ofNullable(o.getCustomer()).ifPresent(c -> c.setOrders(null)));

        return ResponseEntity.ok(persistedOrder);
    }

    @PutMapping("/{customerId}/orders/{orderId}")
    public ResponseEntity<Order> update(@PathVariable String customerId, @PathVariable String orderId, @RequestBody Order order) {
        return ResponseEntity.ok(this.orderService.update(customerId, orderId, order));
    }

    @DeleteMapping("/{customerId}/orders/{orderId}")
    public ResponseEntity<String> delete(@PathVariable String customerId, @PathVariable String orderId) {
        this.orderService.delete(customerId, orderId);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/{customerId}/orders/{orderId}history")
    public ResponseEntity<List<EntityWithRevision<Order>>> getHistory(@PathVariable String customerId, @PathVariable String orderId) {
        List<EntityWithRevision<Order>> list = this.orderService.getRevisions(customerId, orderId);

        return ResponseEntity.ok(list);
    }
}
