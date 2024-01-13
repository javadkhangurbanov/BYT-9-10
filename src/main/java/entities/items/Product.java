package main.java.entities.items;

import main.java.entities.users.Employee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product {

    /** LATER ADDED: many-to-many association with storages.
     *               many-to-many association with employees.
     *               many-to-many association with orders.**/
    private Integer _id;
    private boolean requiresPrescription;
    private String name;
    private String description;
    private BigDecimal price;

    private List<Storage> storages;
    private List<OrderProduct> orderProducts;

    private List<StorageProduct> storageProducts;

    private List<Employee> managingEmployees;

    public Product() {
        storages = new ArrayList<>();
        managingEmployees = new ArrayList<>();
        orderProducts = new ArrayList<>();
        storageProducts = new ArrayList<>();
    }

    // Newly added stuff below
    public boolean providePrescriptionCode() {
        return requiresPrescription;
    }

    // Getters and setters for properties
    public boolean requiresPrescription() {
        return requiresPrescription;
    }

    public void setRequiresPrescription(boolean requiresPrescription) {
        this.requiresPrescription = requiresPrescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public List<Storage> getStorages() {
        return storages;
    }

    public void addManagingEmployee(Employee employee) {
        if(!managingEmployees.contains(employee)) {
            managingEmployees.add(employee);

            employee.addManagedProduct(this);
        }
    }

    public void removeManagingEmployee(Employee employee) {
        if(managingEmployees.contains(employee)) {
            managingEmployees.remove(employee);

            employee.removeManagedProduct(this);
        }
    }

    public List<Employee> getManagingEmployees() {
        return managingEmployees;
    }


    public int contains(Order order) {
        int index = -1;
        for (int i = 0; i< orderProducts.size(); i++) {
            if (orderProducts.get(i).getOrder().equals(order)) {
                index = i;
                break;
            }
        }
        return index;
    }
    /**
     * I wrapped the usual getter with new ArrayList, so users would not be able to change the associations
     * in any way except through designated functions.
     */
    public List<OrderProduct> getOrderProducts() {
        return new ArrayList<>(orderProducts);
    }

    public void addOrderProductAssociation(OrderProduct orderProduct) {
        if (!orderProducts.contains(orderProduct)) {
           orderProducts.add(orderProduct);

           Order order = orderProduct.getOrder();
           order.addOrderProductAssociation(orderProduct);
        }
    }

    public void setOrderProductAssociation(Order order, int newAmount) {
        int index = contains(order);
        OrderProduct orderProduct;

        if (index == -1) {
            orderProduct = new OrderProduct(order, this, newAmount);
            this.addOrderProductAssociation(orderProduct);
        } else {
            orderProduct = orderProducts.get(index);
            orderProduct.setQuantity(newAmount);
        }
    }

    /**
     * Removes an association if one exists
     * (this method does not depend on the variable quantity.
     * Association will be deleted, even if orderProduct passed as a parameter has a different quantity from the one on the list)
     * **/
    public void removeOrderProductAssociation(OrderProduct orderProduct) {
        if (orderProducts.contains(orderProduct)) {
            orderProducts.remove(orderProduct);

            Order order = orderProduct.getOrder();
            order.removeOrderProductAssociation(orderProduct);
        }
    }
    /**
     * Removes an association if one exists
     * (this method does not depend on the variable quantity.
     * Association will be deleted, even if orderProduct passed as a parameter has a different quantity from the one on the list)
     * **/
    public void removeOrderProductAssociation(Order order) {
        removeOrderProductAssociation(new OrderProduct(order, this, 0));
    }

    /** Refer to the same method for OrderProduct association, the logic is the same **/
    public void addStorageProductAssociation(StorageProduct storageProduct) {
        if (!storageProducts.contains(storageProduct)) {
            storageProducts.add(storageProduct);

            Storage storage = storageProduct.getStorage();
            storage.addStorageProductAssociation(storageProduct);
        }
    }

    /** Refer to the same method for OrderProduct association, the logic is the same **/
    public void setStorageProductAssociation(Storage storage, int newAmount) {
        int index = contains(storage);
        StorageProduct storageProduct;

        if (index == -1) {
            storageProduct = new StorageProduct(storage, this, newAmount);
            this.addStorageProductAssociation(storageProduct);
        } else {
            storageProduct = storageProducts.get(index);
            storageProduct.setQuantity(newAmount);
        }
    }


    /** Refer to the same method for OrderProduct association, the logic is the same **/
    public void removeStorageProductAssociation(StorageProduct storageProduct) {
        if (storageProducts.contains(storageProduct)) {
            storageProducts.remove(storageProduct);

            Storage storage = storageProduct.getStorage();
            storage.removeStorageProductAssociation(storageProduct);
        }
    }

    /** Refer to the same method for OrderProduct association, the logic is the same **/
    public void removeStorageProductAssociation(Storage storage) {
        removeStorageProductAssociation(new StorageProduct(storage, this, 0));
    }

    public int contains(Storage storage) {
        int index = -1;
        for (int i = 0; i< storageProducts.size(); i++) {
            if (storageProducts.get(i).getStorage().equals(storage)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public List<StorageProduct> getStorageProducts() {
        return new ArrayList<>(storageProducts);
    }
}
