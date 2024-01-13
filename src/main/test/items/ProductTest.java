package main.test.items;

import main.java.entities.items.*;
import main.java.entities.shipment.Shipment;
import main.java.entities.users.Client;
import main.java.entities.users.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.fail;

public class ProductTest {

    Product product1, product2;
    Storage storage1, storage2;
    Employee employee;
    Order order1, order2;
    @Before
    public void before() {
        product1 = new Product();
        product2 = new Product();
        storage1 = new Storage();
        storage2 = new Storage();
        employee = new Employee();
        order1 = new Order(new Shipment(), new Client());
        order2 = new Order(new Shipment(), new Client());
    }

    @Test
    public void providePrescriptionCode() {
        product1.setRequiresPrescription(true);
        Assert.assertTrue(product1.providePrescriptionCode());

        product1.setRequiresPrescription(false);
        Assert.assertFalse(product1.providePrescriptionCode());
    }

    @Test
    public void testStorageAssociation() {
        product1.setStorageProductAssociation(storage1, 10);

        Assert.assertEquals(product1.getStorageProducts(), storage1.getStorageProducts());
        Assert.assertEquals(product1.getStorageProducts(), List.of(new StorageProduct(storage1, product1, 10)));

        product1.setStorageProductAssociation(storage1, 15);
        product1.setStorageProductAssociation(storage2, 50);

        Assert.assertEquals(product1.getStorageProducts(), List.of(new StorageProduct(storage1, product1, 15),
                                                                 new StorageProduct(storage2, product1, 50)));
        Assert.assertEquals(storage1.getStorageProducts(), List.of(new StorageProduct(storage1, product1, 15)));
        Assert.assertEquals(storage2.getStorageProducts(), List.of(new StorageProduct(storage2, product1, 50)));

        product1.removeStorageProductAssociation(storage2);
        Assert.assertEquals(product1.getStorageProducts(), storage1.getStorageProducts());
        Assert.assertEquals(product1.getStorageProducts(), List.of(new StorageProduct(storage1, product1, 15)));
        Assert.assertEquals(product1.getStorageProducts().size(), 1);
        Assert.assertEquals(storage1.getStorageProducts().size(), 1);
        Assert.assertEquals(storage2.getStorageProducts().size(), 0);
    }

    @Test
    public void testEmployeeAssociation() {
        product1.addManagingEmployee(employee);
        product2.addManagingEmployee(employee);

        Assert.assertEquals(product1.getManagingEmployees(), List.of(employee));
        Assert.assertEquals(product2.getManagingEmployees(), List.of(employee));
        Assert.assertEquals(employee.getManagedProducts(), List.of(product1, product2));

        product2.removeManagingEmployee(employee);

        Assert.assertEquals(product1.getManagingEmployees(), List.of(employee));
        Assert.assertEquals(product2.getManagingEmployees(), new ArrayList<>());
        Assert.assertEquals(employee.getManagedProducts(), List.of(product1));

    }

    @Test
    public void testOrderAssociation() {
        product1.setOrderProductAssociation(order1, 5);
        Assert.assertEquals(order1.getOrderProducts(), product1.getOrderProducts());

        product1.setOrderProductAssociation(order1, 9);
        Assert.assertEquals(order1.getOrderProducts(), product1.getOrderProducts());
        Assert.assertEquals(order1.getOrderProducts().size(), 1);
        Assert.assertEquals(product1.getOrderProducts().size(), 1);

        product2.setOrderProductAssociation(order1, 10);
        Assert.assertEquals(product1.getOrderProducts(), List.of(new OrderProduct(order1, product1, 9)));
        Assert.assertEquals(product2.getOrderProducts(), List.of(new OrderProduct(order1, product2, 10)));
        Assert.assertEquals(order1.getOrderProducts(), List.of(new OrderProduct(order1, product1, 9),
                                                                new OrderProduct(order1, product2, 10)));
        Assert.assertEquals(product1.getOrderProducts().size(), 1);
        Assert.assertEquals(product2.getOrderProducts().size(), 1);
        Assert.assertEquals(order1.getOrderProducts().size(), 2);

        product2.removeOrderProductAssociation(order1);
        Assert.assertEquals(order1.getOrderProducts(), product1.getOrderProducts());
        Assert.assertEquals(order1.getOrderProducts().size(), 1);
        Assert.assertEquals(product1.getOrderProducts().size(), 1);
        Assert.assertEquals(product2.getOrderProducts().size(), 0);


    }
}
