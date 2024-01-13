package main.java.entities.shipment;

import main.java.entities.items.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Shipment {
    private Integer _id;
    private ShipmentCompany company;

    private boolean shipToAddress;
    private Address address;
    private String ParcelLockerId;

    /** LATER ADDED: one-to-many association with order **/

    private List<Order> orders;

    private Date shipmentDate;

    private static int idCount = 0;
    public Shipment() {
        _id = idCount++;
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order newOrder) {
            // Check if we already have the info
        if(!orders.contains(newOrder)) {
            orders.add(newOrder);
            // Add the reverse connection
            newOrder.setShipment(this);
        }
    }


    public void removeOrder(Order order, Shipment newShipment) {
        if (orders.contains(order)) {
            orders.remove(order);

            order.setShipment(newShipment);
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shipment shipment)) return false;
        return _id.equals(shipment._id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id);
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "_id=" + _id +
                '}';
    }

    public Integer get_id() {
        return _id;
    }

    public ShipmentCompany getCompany() {
        return company;
    }

    public void setCompany(ShipmentCompany company) {
        this.company = company;
    }

    public boolean isShipToAddress() {
        return shipToAddress;
    }

    public void setShipToAddress(boolean shipToAddress) {
        this.shipToAddress = shipToAddress;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getParcelLockerId() {
        return ParcelLockerId;
    }

    public void setParcelLockerId(String parcelLockerId) {
        ParcelLockerId = parcelLockerId;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }
}
