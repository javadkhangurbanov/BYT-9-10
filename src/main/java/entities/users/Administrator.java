package main.java.entities.users;

import java.util.ArrayList;
import java.util.List;

public class Administrator extends Employee {

    /**LATER ADDED: one-to-many association with employee. **/
    private List<Employee> managedEmployees;

    public boolean addEmployee(Employee employee) {
        if (!managedEmployees.contains(employee)) {
            managedEmployees.add(employee);
            employee.setAdministrator(this);
            return true;
        }
        return false;
    }

    public boolean removeEmployee(Employee employee) {
        if (managedEmployees.contains(employee)) {
            managedEmployees.remove(employee);
            employee.setAdministrator(null);
            return true;
        }
        return false;
    }

    public Administrator() {
        managedEmployees = new ArrayList<>();
    }

    // Commented this out - Why duplicate add remove employee methods?
//    public void addEmployee(Employee employee) {
//        if (!managedEmployees.contains(employee)) {
//            managedEmployees.add(employee);
//            employee.setAdministrator(this);
//        }
//    }
//
//    public void removeEmployee(Employee employee) {
//        if (managedEmployees.contains(employee)) {
//            managedEmployees.remove(employee);
//            employee.removeAdministrator(this);
//        }
//    }

    public List<Employee> getManagedEmployees() {
        return managedEmployees;
    }

    public void setManagedEmployees(List<Employee> managedEmployees) {
        this.managedEmployees = managedEmployees;
    }
}
