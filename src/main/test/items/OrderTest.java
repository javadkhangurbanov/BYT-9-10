package main.test.items;

import main.java.entities.items.Order;
import main.java.entities.items.OrderProduct;
import main.java.entities.items.Product;
import main.java.entities.shipment.Shipment;
import main.java.entities.users.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.fail;

public class OrderTest {

    Order order1, order2;
    Product product1, product2;
    Shipment shipment, shipment1;
    Client client;
    @Before
    public void before() {
        client = new Client();
        shipment = new Shipment();
        shipment1 = new Shipment();
        order1 = new Order(shipment, client);
        order2 = new Order(shipment, client);
        product1 = new Product();
        product2 = new Product();

    }


    @Test
    public void testShipmentAssociation() {

        Assert.assertEquals(order1.getShipment(), shipment);
        Assert.assertEquals(order2.getShipment(), shipment);
        Assert.assertEquals(shipment.getOrders(), List.of(order1, order2));

        order1.setShipment(shipment1);
        Assert.assertEquals(order1.getShipment(), shipment1);
        Assert.assertEquals(shipment.getOrders(), List.of(order2));
        Assert.assertEquals(shipment1.getOrders(), List.of(order1));
    }

    @Test
    public void testProductAssociation() {
        order1.setOrderProductAssociation(product1, 5);
        Assert.assertEquals(order1.getOrderProducts(), product1.getOrderProducts());

        order1.setOrderProductAssociation(product1, 9);
        Assert.assertEquals(order1.getOrderProducts(), product1.getOrderProducts());
        Assert.assertEquals(order1.getOrderProducts().size(), 1);
        Assert.assertEquals(product1.getOrderProducts().size(), 1);

        order2.setOrderProductAssociation(product1, 10);
        Assert.assertEquals(order1.getOrderProducts(), List.of(new OrderProduct(order1, product1, 9)));
        Assert.assertEquals(order2.getOrderProducts(), List.of(new OrderProduct(order2, product1, 10)));
        Assert.assertEquals(product1.getOrderProducts(), List.of(new OrderProduct(order1, product1, 9),
                                                                new OrderProduct(order2, product1, 10)));
        Assert.assertEquals(order1.getOrderProducts().size(), 1);
        Assert.assertEquals(order2.getOrderProducts().size(), 1);
        Assert.assertEquals(product1.getOrderProducts().size(), 2);

        order2.removeOrderProductAssociation(product1);
        Assert.assertEquals(order1.getOrderProducts(), product1.getOrderProducts());
        Assert.assertEquals(order1.getOrderProducts().size(), 1);
        Assert.assertEquals(product1.getOrderProducts().size(), 1);

    }

    @Test
    public void testClientAssociation() {

        Assert.assertEquals(order1.getClient(), client);
        Assert.assertEquals(order2.getClient(), client);
        Assert.assertEquals(client.getOrders(), List.of(order1, order2));

        client.removeOrder(order2);

        Assert.assertEquals(client.getOrders(), List.of(order1));
        Assert.assertEquals(order2.getClient(), client); // even when the client removes the order from their history, the order object still exists with reference to them.
    }
}
