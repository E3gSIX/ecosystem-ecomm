package br.com.fiap.postech.adjt.cart.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID customerId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items;

    public Cart() {
    }

    public Cart(UUID customerId) {
        setCustomerId(customerId);
        items = new ArrayList<>();
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public void addItem(CartItem item) {
        items.stream()
                .filter(it -> it.getItemId().equals(item.getItemId()))
                .findFirst()
                .ifPresentOrElse(
                        it -> it.setQuantity(it.getQuantity() + item.getQuantity()),
                        () -> items.add(item)
                );
    }

    public void removeItem(Long itemId) {
        items.stream()
                .filter(it -> it.getItemId().equals(itemId))
                .findFirst()
                .ifPresentOrElse(
                        it -> {
                            int newQuantity = it.getQuantity() - 1;
                            if (newQuantity > 0) {
                                it.setQuantity(newQuantity);
                            } else {
                                items.remove(it);
                            }
                        },
                        () -> {
                            throw new IllegalArgumentException("Invalid itemId, there is no itemId %s in the cart".formatted(itemId));
                        }
                );
    }

    public void incrementItem(Long itemId) {
        items.stream()
                .filter(it -> it.getItemId().equals(itemId))
                .findFirst()
                .ifPresentOrElse(
                        it -> {
                            int newQuantity = it.getQuantity() + 1;
                            it.setQuantity(newQuantity);
                        },
                        () -> {
                            throw new IllegalArgumentException("Invalid itemId");
                        }
                );
    }

    public void checkIfCartIsEmpty() {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Empty cart");
        }
    }

    public void clear() {
        items.clear();
    }
}
