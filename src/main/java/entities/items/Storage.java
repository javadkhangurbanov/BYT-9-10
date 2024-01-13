package main.java.entities.items;

import main.java.entities.shipment.Address;

import java.util.*;

public class Storage {
    private Integer _id;
    private static int idCount = 0;
    private Address address;
    private List<StorageProduct> storageProducts;

    /** Added later: many-to-many association between products and storages.
    **/

    public Storage() {
        _id = idCount++;
        storageProducts = new ArrayList<>();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getId() {
        return _id;
    }

    public void setId(Integer _id) {
        this._id = _id;
    }


    /** Refer to the same method for OrderProduct association, the logic is the same **/
    public void setStorageProductAssociation(Product product, int newAmount) {
        int index = contains(product);
        StorageProduct storageProduct;

        if (index == -1) {
            storageProduct = new StorageProduct(this, product, newAmount);
            addStorageProductAssociation(storageProduct);
        } else {
            storageProduct = storageProducts.get(index);
            storageProduct.setQuantity(newAmount);
        }
    }

    /** Refer to the same method for OrderProduct association, the logic is the same **/
    public void addStorageProductAssociation(StorageProduct storageProduct) {
        if (!storageProducts.contains(storageProduct)) {
            storageProducts.add(storageProduct);

            Product product = storageProduct.getProduct();
            product.addStorageProductAssociation(storageProduct);
        }
    }

    /** Refer to the same method for OrderProduct association, the logic is the same **/
    public void removeStorageProductAssociation(StorageProduct storageProduct) {
        if (storageProducts.contains(storageProduct)) {
            storageProducts.remove(storageProduct);

            Product product = storageProduct.getProduct();
            product.removeStorageProductAssociation(storageProduct);
        }
    }

    /** Refer to the same method for OrderProduct association, the logic is the same **/
    public void removeStorageProductAssociation(Product product) {
        removeStorageProductAssociation(new StorageProduct(this, product, 0));
    }


    public int contains(Product product) {
        int index = -1;
        for (int i = 0; i< storageProducts.size(); i++) {
            if (storageProducts.get(i).getProduct().equals(product)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public List<StorageProduct> getStorageProducts() {
        return new ArrayList<>(storageProducts);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Storage storage)) return false;
        return _id.equals(storage._id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id);
    }
}
