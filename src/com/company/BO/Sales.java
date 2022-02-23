package com.company.BO;

import java.util.Date;

public class Sales {

    private String product;
    private int price;
    private int copies;
    private Date salesDate;

    public Sales() {
    }

    public Sales(String product, int price, int copies, Date salesDate) {
        this.product = product;
        this.price = price;
        this.copies = copies;
        this.salesDate = salesDate;
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

    public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }
}
