package main.java.entities.items;

import java.util.Objects;

public class OrderProduct {
    private Order order;
    private Product product;

    private int quantity;
    public OrderProduct(Order order, Product product, int quantity) {
        if (order == null) {
            throw new IllegalArgumentException("Project or order cannot be null.");
        } else {
            this.order = order;
        }

        if (product == null) {
            throw new IllegalArgumentException("Project or order cannot be null.");
        } else {
            this.product = product;
        }

        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Order getOrder() {
        return order;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProduct that)) return false;
        return getOrder().equals(that.getOrder()) && getProduct().equals(that.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrder(), getProduct());
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
