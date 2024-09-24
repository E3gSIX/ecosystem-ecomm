package br.com.fiap.postech.adjt.cart.model.entity;

import jakarta.persistence.*;

@Entity(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long itemId;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public CartItem() {
    }

    public CartItem(Long itemId, String quantity) {
        setItemId(itemId);
        setQuantity(Integer.valueOf(quantity));
    }

    private void setItemId(Long itemId) {
        if (itemId == null || itemId < 0) {
            throw new IllegalArgumentException("Invalid itemId does not exist");
        }
        this.itemId = itemId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
