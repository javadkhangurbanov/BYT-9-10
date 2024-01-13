package main.java.entities.items;

import java.util.Objects;

public class StorageProduct {

    private Storage storage;
    private Product product;

    private int quantity;

    public StorageProduct(Storage storage, Product product, int quantity) {
        if (storage == null) {
            throw new IllegalArgumentException("Project or storage cannot be null.");
        } else {
            this.storage = storage;
        }

        if (product == null) {
            throw new IllegalArgumentException("Project or storage cannot be null.");
        } else {
            this.product = product;
        }

        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StorageProduct that)) return false;
        return storage.equals(that.storage) && product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storage, product);
    }

    public Product getProduct() {
        return product;
    }

    public Storage getStorage() {
        return storage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StorageProduct{" +
                "storage=" + storage +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
