package com.company.BO;

import java.util.ArrayList;

public class Employee{
    private int id;
    private String active = "active";
    private String name;
    private String address;
    private ArrayList<Customer> customerList = new ArrayList<>();
    private ArrayList<Sales> salesList = new ArrayList<>();

    public Employee() {
    }

    public Employee(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Employee(int id, String active, String name, String address) {
        this.id = id;
        this.active = active;
        this.name = name;
        this.address = address;
    }

    public Employee(int id, String name, String address, ArrayList<Customer> customerList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.customerList = customerList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomers(Customer customer) {
        this.customerList.add(customer);
    }

    public ArrayList<Sales> getSalesList() {
        return salesList;
    }

    public void setSalesList(Sales sale) {
        this.salesList.add(sale);
    }

    @Override
    public boolean equals(Object obj) {

        // If the object is compared with itself then return true
        if (obj == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(obj instanceof Employee)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Employee employee = (Employee) obj;
        // Compare the data members and return accordingly
        return name.equals(employee.getName())
                && address.equals(employee.getAddress());
    }
}
