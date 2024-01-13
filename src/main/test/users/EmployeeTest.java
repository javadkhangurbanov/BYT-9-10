package main.test.users;

import main.java.entities.items.Product;
import main.java.entities.users.Administrator;
import main.java.entities.users.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class EmployeeTest extends UserTest{

    Employee employee;
    Administrator administrator1, administrator2;
    Product product1, product2;

    @Before
    public void before() {
        product1 = new Product();
        product2 = new Product();
        employee = new Employee();
        administrator1 = new Administrator();
        administrator2 = new Administrator();
    }

    @Test
    public void testProductAssociation() {
        employee.addManagedProduct(product1);
        employee.addManagedProduct(product2);

        Assert.assertEquals(employee.getManagedProducts(), List.of(product1, product2));
        Assert.assertEquals(product1.getManagingEmployees(), List.of(employee));
        Assert.assertEquals(product2.getManagingEmployees(), List.of(employee));

        employee.removeManagedProduct(product2);

        Assert.assertEquals(employee.getManagedProducts(), List.of(product1));
        Assert.assertEquals(product1.getManagingEmployees(), List.of(employee));
        Assert.assertEquals(product2.getManagingEmployees(), new ArrayList<>());
    }

    @Test
    public void testAdministratorAssociation() {
        employee.setAdministrator(administrator1);

        Assert.assertEquals(administrator1.getManagedEmployees(), List.of(employee));
        Assert.assertEquals(employee.getAdministrator(), administrator1);

        employee.setAdministrator(administrator2);

        Assert.assertEquals(administrator2.getManagedEmployees(), List.of(employee));
        Assert.assertEquals(administrator1.getManagedEmployees(), new ArrayList<>());
        Assert.assertEquals(employee.getAdministrator(), administrator2);

        employee.removeAdministrator(administrator2);

        Assert.assertEquals(administrator2.getManagedEmployees(), new ArrayList<>());
        Assert.assertNull(employee.getAdministrator());
    }

    @Test
    public void addProduct() {
        employee.addProduct(product1);

        List<Product> managedProducts = employee.getManagedProducts();
        Assert.assertTrue(managedProducts.contains(product1));
        Assert.assertEquals(product1.getManagingEmployees(), List.of(employee));
    }

    @Test
    public void removeProduct() {
        employee.addProduct(product1);
        employee.removeProduct(product1);

        List<Product> managedProducts = employee.getManagedProducts();
        Assert.assertFalse(managedProducts.contains(product1));
        Assert.assertEquals(product1.getManagingEmployees(), new ArrayList<>());
    }

    @Test
    public void editProduct() {
        employee.editProduct(product1, true, "New Name", "New Description", new BigDecimal("99.99"));

        Assert.assertTrue(product1.requiresPrescription());
        Assert.assertEquals(product1.getName(), "New Name");
        Assert.assertEquals(product1.getDescription(), "New Description");
        Assert.assertEquals(product1.getPrice(), new BigDecimal("99.99"));
    }

}
