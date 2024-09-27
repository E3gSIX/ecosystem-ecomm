package br.com.fiap.postech.adjt.checkout.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.fiap.postech.adjt.checkout.model.OrderEntity;

@DataJpaTest
class OrderRepositoryTest {

    @Mock
    private OrderRepository orderRepository;

    private UUID consumerId;
    private UUID orderId;
    private OrderEntity orderEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        consumerId = UUID.randomUUID();
        orderId = UUID.randomUUID();
        orderEntity = new OrderEntity(orderId, consumerId, "credit_card", 100, "pending", List.of(), null);
    }

    @Test
    void testFindByConsumerId() {
        when(orderRepository.findByConsumerId(consumerId)).thenReturn(List.of(orderEntity));
        List<OrderEntity> orders = orderRepository.findByConsumerId(consumerId);
        assertNotNull(orders);
        assertEquals(1, orders.size());
        assertEquals(consumerId, orders.get(0).getConsumerId());
        verify(orderRepository, times(1)).findByConsumerId(consumerId);
    }

    @Test
    void testFindByPaymentStatus() {
        when(orderRepository.findByPaymentStatus("pending")).thenReturn(List.of(orderEntity));
        List<OrderEntity> orders = orderRepository.findByPaymentStatus("pending");
        assertNotNull(orders);
        assertEquals(1, orders.size());
        assertEquals("pending", orders.get(0).getPaymentStatus());
        verify(orderRepository, times(1)).findByPaymentStatus("pending");
    }

    @Test
    void testFindById() {
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(orderEntity));
        Optional<OrderEntity> foundOrder = orderRepository.findById(orderId);
        assertTrue(foundOrder.isPresent());
        assertEquals(orderId, foundOrder.get().getOrderId());
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    void testSaveOrder() {
        when(orderRepository.save(orderEntity)).thenReturn(orderEntity);
        OrderEntity savedOrder = orderRepository.save(orderEntity);
        assertNotNull(savedOrder);
        assertEquals(orderId, savedOrder.getOrderId());
        verify(orderRepository, times(1)).save(orderEntity);
    }

    @Test
    void testDeleteOrder() {
        orderRepository.delete(orderEntity);
        verify(orderRepository, times(1)).delete(orderEntity);
    }
}