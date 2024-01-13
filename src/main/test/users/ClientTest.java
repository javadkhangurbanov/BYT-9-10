package main.test.users;

import main.java.entities.items.Order;
import main.java.entities.items.Product;
import main.java.entities.shipment.Shipment;
import main.java.entities.users.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.fail;

public class ClientTest {
    Client client;
    Order order1, order2;
    @Before
    public void before() {
        client = new Client();
        order1 = new Order(new Shipment(), client);
        order2 = new Order(new Shipment(), client);
    }

    @Test
    public void addToCart() {
        Product product = new Product();
        client.addToCart(product, 5); // Assuming a quantity of 5

        HashMap<Product, Integer> cart = client.getCart();
        Assert.assertTrue(cart.containsKey(product));
        Assert.assertEquals((int) cart.get(product), 5);
    }

    @Test
    public void removeFromCart() {
        Product product = new Product();
        client.addToCart(product, 5);
        client.removeFromCart(product);

        HashMap<Product, Integer> cart = client.getCart();
        Assert.assertFalse(cart.containsKey(product));
    }

    @Test
    public void makeAPurchase() {
        fail("Write a test for this later");
    }

    @Test
    public void testOrderAssociation() {

        Assert.assertEquals(client.getOrders(), List.of(order1, order2));
        Assert.assertEquals(order1.getClient(), client);
        Assert.assertEquals(order2.getClient(), client);

        client.removeOrder(order2);

        Assert.assertEquals(client.getOrders(), List.of(order1));
        Assert.assertEquals(order1.getClient(), client);
        Assert.assertEquals(order2.getClient(), client); // even when the client removes the order from their history, the order object still exists with reference to them.
    }

}
