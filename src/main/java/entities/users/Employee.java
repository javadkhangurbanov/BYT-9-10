package main.java.entities.users;

import main.java.entities.items.Product;
import main.java.entities.shipment.Shipment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Employee extends User{

    /**LATER ADDED: many-to-many association with products.
     *              many-to-one association with administrator. **/

    private List<Product> managedProducts;

    private Administrator administrator;

    public Employee() {
        managedProducts = new ArrayList<>();
    }

    public boolean addProduct(Product product) {
        addManagedProduct(product);
        return true;
    }

    public boolean removeProduct(Product product) {
        removeManagedProduct(product);
        return true;
    }

    public boolean editProduct(Product product, boolean presc_edit, String name_edit, String desc_edit, BigDecimal price_edit) {
        product.setRequiresPrescription(presc_edit);
        product.setName(name_edit);
        product.setDescription(desc_edit);
        product.setPrice(price_edit);
        return true;
    }

    public void addManagedProduct(Product product) {
        if (!managedProducts.contains(product)) {
            managedProducts.add(product);

            product.addManagingEmployee(this);
        }
    }

    public void removeManagedProduct(Product product) {
        if (managedProducts.contains(product)) {
            managedProducts.remove(product);

            product.removeManagingEmployee(this);
        }
    }

    public List<Product> getManagedProducts() {
        return managedProducts;
    }

    // Changed the setAdministrator method to the one below to avoid null cases
//    public void setAdministrator(Administrator newAdministrator) {
//        if(this.administrator == null || !administrator.equals(newAdministrator)) {
//            if (administrator != null)
//                administrator.removeEmployee(this);
//
//            administrator = newAdministrator;
//            newAdministrator.addEmployee(this);
//        }
//    }

    public void setAdministrator(Administrator newAdministrator) {
        // Check if the new administrator is different from the current one
        if (this.administrator != null && !this.administrator.equals(newAdministrator)) {
            // Remove this employee from the old administrator's list
            this.administrator.removeEmployee(this);
        }
        // Set the new administrator
        this.administrator = newAdministrator;
        if (newAdministrator != null) {
            newAdministrator.addEmployee(this);
        }
    }

    public void removeAdministrator(Administrator administrator) {
        if(this.administrator != null && this.administrator.equals(administrator)) {
            this.administrator = null;

            administrator.removeEmployee(this);
        }
    }

    public Administrator getAdministrator() {
        return administrator;
    }
}

