package kr.co.iquest.beinone.iquestory;

import java.util.List;

/**
 * Created by BeINone on 2017-11-12.
 */

public class Company {

    private int asset;
    private int dept;
    private List<Product> products;
    private List<Employee> employees;

    public void paySalary() {
        int totalSalary = 0;
        if (employees != null) {
            for (Employee employee : employees) {
                if (employee.isPayDay()) {
                    totalSalary -= employee.getSalary();
                }
            }
        }

        asset -= totalSalary;
    }

    public void receiveSales() {
        int totalSales = 0;
        if (products != null) {
            for (Product product : products) {
                totalSales += product.getPrice() * product.getNumCustomer();
            }
        }

        asset += totalSales;
    }

    public int getAsset() {
        return asset;
    }

    public void setAsset(int asset) {
        this.asset = asset;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
