package kr.co.iquest.beinone.iquestory.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BeINone on 2017-11-12.
 */

public class Company {

    private int asset;
    private int dept;
    private List<Product> products;
    private List<Employee> employees;
    private OnAssetChangedListener onAssetChangedListener;

    public Company(OnAssetChangedListener listener) {
        products = new ArrayList<>();
        employees = new ArrayList<>();
        onAssetChangedListener = listener;
    }

    public void employ(Employee employee) {
        employees.add(employee);
    }

    public void paySalary() {
        int totalSalary = 0;
        if (employees != null) {
            for (Employee employee : employees) {
                if (employee.isPayDay()) {
                    totalSalary -= employee.getSalary();
                }
            }
        }

        setAsset(asset - totalSalary);
    }

    public void receiveSales() {
        int totalSales = 0;
        if (products != null) {
            for (Product product : products) {
                totalSales += product.getPrice() * product.getNumCustomer();
            }
        }

        setAsset(asset + totalSales);
    }

    public int getAsset() {
        return asset;
    }

    public void setAsset(int asset) {
        this.asset = asset;
        onAssetChangedListener.onAssetChanged(this.asset);
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

    public interface OnAssetChangedListener {
        void onAssetChanged(int asset);
    }
}
