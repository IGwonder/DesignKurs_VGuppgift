package com.company.BO;

public class Customer {
    private int id;
    private String active = "active";
    private String name;
    private String address;

    public Customer() {
    }

    public Customer(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Customer(int id, String active, String name, String address) {
        this.id = id;
        this.active = active;
        this.name = name;
        this.address = address;
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

    @Override
    public boolean equals(Object obj) {

        // If the object is compared with itself then return true
        if (obj == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(obj instanceof Customer)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Customer c = (Customer) obj;
        // Compare the data members and return accordingly
        return name.equals(c.getName())
                && address.equals(c.getAddress());
    }

}
