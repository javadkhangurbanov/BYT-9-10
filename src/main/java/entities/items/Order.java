package main.java.entities.items;

import main.java.entities.shipment.Shipment;
import main.java.entities.users.Client;

import java.util.*;

public class Order {

    /** LATER ADDED: many-to-one association with Client.
     *               requirement for client not to be equal null.
     *
     *               many-to-one association with Shipment.
     *               requirement for Shipment not to be equal null.
     *
     *               many-to-many association with products.
     * **/
    private Integer _id;
    private List<OrderProduct> orderProducts;

    private Client client;

    private static int idCount = 0;

    public Order(Shipment shipment, Client client) {
        _id = idCount++;

        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        } else {
            this.client = client;
            this.client.addOrder(this);
        }

        if (shipment == null) {
            throw new IllegalArgumentException("Every order must have shipment method.");
        }

        this.setShipment(shipment);
        this.orderProducts = new ArrayList<>();
    }

    /** Later added: shipment association, set/remove methods **/

    private Shipment shipment;

    public boolean verifyPrescription() {
        return false;
    }


    public void setShipment(Shipment newShipment) {
        if (shipment == null) {
            shipment = newShipment;
            newShipment.addOrder(this);
        } else if(!shipment.equals(newShipment)) {

            shipment.removeOrder(this, newShipment);
            shipment = newShipment;
            newShipment.addOrder(this);
        }
    }

    /**
     * This method changes the quantity between this order and product passed as a parameter.
     * If the association did not exist before, it creates a new one and gives the reference to the same association object
     * to the other party.
     * If the association already exists, we know for sure that the reference to the same association is already
     * on the list of the other party, so the only thing we need is to change the quantity.
     * **/
    public void setOrderProductAssociation(Product product, int newAmount) {
        int index = contains(product);
        OrderProduct orderProduct;

        if (index == -1) {
            orderProduct = new OrderProduct(this, product, newAmount);
            addOrderProductAssociation(orderProduct);
        } else {
            orderProduct = orderProducts.get(index);
            orderProduct.setQuantity(newAmount);
        }
    }

    /**
     * Adds an association if it did not exist before.
     * (This method does not depend on the quantity.
     * If an association between the same order and product passed as parameter is found on the list,
     * new association won't be created, even if quantities of those two orderProducts are different.)
     *
     * This method also sends the reference to the same association object to the other party.
     * **/
    public void addOrderProductAssociation(OrderProduct orderProduct) {
        if (!orderProducts.contains(orderProduct)) {
            orderProducts.add(orderProduct);

            Product product = orderProduct.getProduct();
            product.addOrderProductAssociation(orderProduct);
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

            Product product = orderProduct.getProduct();
            product.removeOrderProductAssociation(orderProduct);
        }
    }

    /**
     * Removes an association if one exists
     * (this method does not depend on the variable quantity.
     * Association will be deleted, even if orderProduct passed as a parameter has a different quantity from the one on the list)
     * **/
    public void removeOrderProductAssociation(Product product) {
        removeOrderProductAssociation(new OrderProduct(this, product, 0));
    }



    /**
     * If this Order object has product, the method will return the index of OrderProduct object.
     * Else, method will return  -1.
     * **/
    public int contains(Product product) {
        int index = -1;
        for (int i = 0; i< orderProducts.size(); i++) {
            if (orderProducts.get(i).getProduct().equals(product)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public Shipment getShipment() {
        return shipment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return _id.equals(order._id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "_id=" + _id +
                '}';
    }

    /**
     * I wrapped the usual getter with new ArrayList, so users would not be able to change the associations
     * in any way except through designated functions.
     */
    public List<OrderProduct> getOrderProducts() {
        return new ArrayList<>(orderProducts);
    }

    public Client getClient() {
        return client;
    }
}
