package main.java.entities.users;

import main.java.entities.items.Order;
import main.java.entities.items.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client extends User {
    private HashMap<Product, Integer> cart;

    /** LATER ADDED: one-to-many association with orders. **/
    private List<Order> orders;

    public Client() {
        orders = new ArrayList<>();
        cart = new HashMap<>(); // Initialize the cart HashMap
    }

    public boolean addToCart(Product product, int quantity) {
        cart.put(product, cart.getOrDefault(product, 0) + quantity);
        return true;
    }

    public boolean removeFromCart(Product product) {
        cart.remove(product);
        return true;
    }

    public boolean makeAPurchase() {
        return false; // FINISH THIS LATER
    }

    public HashMap<Product, Integer> getCart() {
        return cart;
    }

    public void setCart(HashMap<Product, Integer> cart) {
        this.cart = cart;
    }


    public void addOrder(Order order) {
        if(!orders.contains(order)) {
            orders.add(order);
        }
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public List<Order> getOrders() {
        return orders;
    }
}
