package main.test.shipment;

import main.java.entities.items.Order;
import main.java.entities.items.Product;
import main.java.entities.shipment.Shipment;
import main.java.entities.users.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ShipmentTest {

    Shipment shipment, shipment1;
    Order order1, order2;

    @Before
    public void before() {
        shipment = new Shipment();
        shipment1 = new Shipment();
        order1 = new Order(shipment, new Client());
        order2 = new Order(shipment, new Client());
        shipment = new Shipment();
    }

    @Test
    public void testAssociation() {
        shipment.addOrder(order1);
        shipment.addOrder(order2);


        Assert.assertEquals(shipment.getOrders(), List.of(order1, order2));
        Assert.assertEquals(order1.getShipment(), shipment);
        Assert.assertEquals(order2.getShipment(), shipment);

        shipment.removeOrder(order2, shipment1);

        Assert.assertEquals(shipment.getOrders(), List.of(order1));
        Assert.assertEquals(shipment1.getOrders(), List.of(order2));
        Assert.assertEquals(order2.getShipment(), shipment1);

    }
}
