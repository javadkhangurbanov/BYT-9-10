package main.test.users;

import main.java.entities.users.Administrator;
import main.java.entities.users.Employee;
import main.java.entities.users.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.fail;

public class AdministratorTest extends EmployeeTest {

    Administrator administrator;
    Employee employee1, employee2;
    @Before
    public void before() {
        administrator = new Administrator();
        employee1 = new Employee();
        employee2 = new Employee();
    }

    @Test
    public void testEmployeeAssociation() {
        administrator.addEmployee(employee1);
        administrator.addEmployee(employee2);

        Assert.assertEquals(administrator.getManagedEmployees(), List.of(employee1, employee2));
        Assert.assertEquals(employee1.getAdministrator(), administrator);
        Assert.assertEquals(employee2.getAdministrator(), administrator);

        administrator.removeEmployee(employee2);

        Assert.assertEquals(administrator.getManagedEmployees(), List.of(employee1));
        Assert.assertEquals(employee1.getAdministrator(), administrator);
        Assert.assertNull(employee2.getAdministrator());


    }

    @Test
    public void addEmployee() {
        // Testing addition of a single employee
        administrator.addEmployee(employee1);
        Assert.assertTrue(administrator.getManagedEmployees().contains(employee1));
        Assert.assertEquals(employee1.getAdministrator(), administrator);

        // Testing addition of a second employee
        administrator.addEmployee(employee2);
        Assert.assertTrue(administrator.getManagedEmployees().contains(employee2));
        Assert.assertEquals(employee2.getAdministrator(), administrator);
    }

    @Test
    public void removeEmployee() {
        // First, add employees
        administrator.addEmployee(employee1);
        administrator.addEmployee(employee2);

        // Now, remove one of them
        administrator.removeEmployee(employee1);
        Assert.assertFalse(administrator.getManagedEmployees().contains(employee1));
        Assert.assertNull(employee1.getAdministrator());

        // Check if the second employee is still managed by the administrator
        Assert.assertTrue(administrator.getManagedEmployees().contains(employee2));
        Assert.assertEquals(employee2.getAdministrator(), administrator);
    }

}
