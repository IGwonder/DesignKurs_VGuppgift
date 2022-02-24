package com.company.BO;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Sales {

    private int saleId;
    private String product;
    private int price;
    private int copies;
    private String salesDate;
    private int customerId;
    private int employeeId;
    private String refunded;
    private PropertyChangeSupport propertyChangeSupport;


    public Sales(int saleId, String product, int price, int copies, String salesDate, int customerId) {
        this.saleId = saleId;
        this.product = product;
        this.price = price;
        this.copies = copies;
        this.salesDate = salesDate;
        this.customerId = customerId;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getRefunded() {
        return refunded;
    }

    public void setRefunded(String refunded) {
        this.refunded = refunded;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Sales)) {
            return false;
        }

        Sales s = (Sales) obj;

        return customerId == s.getCustomerId()
                && product.equals(s.getProduct())
                && price == s.getPrice()
                && copies == s.getCopies()
                && salesDate.equals(s.getSalesDate())
                && employeeId == s.getEmployeeId();
    }
}
