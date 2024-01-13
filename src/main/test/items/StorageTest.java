package main.test.items;

import main.java.entities.items.Product;
import main.java.entities.items.Storage;
import main.java.entities.items.StorageProduct;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.fail;

public class StorageTest {

    Storage storage;
    Product product1, product2;
    @Before
    public void before() {
        product1 = new Product();
        product2 = new Product();
        storage = new Storage();
    }

    @Test
    public void testAssociation() {
        storage.setStorageProductAssociation(product1, 10);

        Assert.assertEquals(storage.getStorageProducts(), product1.getStorageProducts());
        Assert.assertEquals(storage.getStorageProducts(), List.of(new StorageProduct(storage, product1, 10)));

        storage.setStorageProductAssociation(product1, 15);
        storage.setStorageProductAssociation(product2, 50);

        Assert.assertEquals(storage.getStorageProducts(), List.of(new StorageProduct(storage, product1, 15),
                new StorageProduct(storage, product2, 50)));
        Assert.assertEquals(product1.getStorageProducts(), List.of(new StorageProduct(storage, product1, 15)));
        Assert.assertEquals(product2.getStorageProducts(), List.of(new StorageProduct(storage, product2, 50)));

        storage.removeStorageProductAssociation(product2);
        Assert.assertEquals(storage.getStorageProducts(), product1.getStorageProducts());
        Assert.assertEquals(storage.getStorageProducts(), List.of(new StorageProduct(storage, product1, 15)));
        Assert.assertEquals(storage.getStorageProducts().size(), 1);
        Assert.assertEquals(product1.getStorageProducts().size(), 1);
        Assert.assertEquals(product2.getStorageProducts().size(), 0);
    }
}
