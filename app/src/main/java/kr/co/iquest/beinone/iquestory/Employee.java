package kr.co.iquest.beinone.iquestory;

/**
 * Created by BeINone on 2017-11-12.
 */

public class Employee {

    private int level = 1;
    private int salary;
    private int numFault;
    private int workMonth;
    private Product ownProduct;

    public Employee(Product product) {
        ownProduct = product;
    }

    public void increaseWorkMonth() {
        workMonth += 1;
    }

    public boolean isPayDay() {
        return workMonth % 12 == 0;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getNumFault() {
        return numFault;
    }

    public void setNumFault(int numFault) {
        this.numFault = numFault;
    }

    public int getWorkMonth() {
        return workMonth;
    }

    public void setWorkMonth(int workMonth) {
        this.workMonth = workMonth;
    }

    public Product getOwnProduct() {
        return ownProduct;
    }

    public void setOwnProduct(Product ownProduct) {
        this.ownProduct = ownProduct;
    }
}
