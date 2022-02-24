package com.company.DAO;

import com.company.BO.Customer;
import com.company.BO.Sales;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class SalesDAO implements DAO<Sales>{
    private final Document database = DataTransfer.getInstance().database;

    public SalesDAO() throws IOException, ParserConfigurationException, SAXException {
    }

    @Override
    public Node locateObject(Element element, String elementTag, int salesId) {
        Node salesData = null;
        for (int j = 0; j < element.getElementsByTagName(elementTag).getLength(); j++) {
            salesData = element.getElementsByTagName(elementTag).item(j);

            if (salesData.getAttributes().getNamedItem("saleId").getTextContent().equals(String.valueOf(salesId))) {
                return salesData;
            }
        }

        return null;
    }

    @Override
    public String locateObjectData(Node salesNode, String elementName) {
        Node salesData = null;
        for (int i = 0; i < salesNode.getChildNodes().getLength(); i++) {
            salesData = salesNode.getChildNodes().item(i);

            if (salesData.getNodeName().equals(elementName)) {
                return salesData.getTextContent();
            }
        }

        return null;
    }

    @Override
    public Sales get(int saleId) throws IOException, ParserConfigurationException, SAXException {
        try {
            Element element = DataTransfer.getInstance().read("sales");
            Node saleNode = locateObject(element, "sale", saleId);
            String product = locateObjectData(saleNode, "product");
            String price = locateObjectData(saleNode, "price");
            String copies = locateObjectData(saleNode, "copies");
            String saleDate = locateObjectData(saleNode,"saleDate");
            String customerId = saleNode.getAttributes().getNamedItem("customerId").getTextContent();
            Sales sale = new Sales(saleId, product, Integer.parseInt(price), Integer.parseInt(copies), saleDate, Integer.parseInt(customerId));

            return sale;
        } catch (NullPointerException e) {
//            e.printStackTrace();
            System.out.println("Get error: This sale (saleId: " + saleId + ") doesn't exist");
            return null;
        }
    }

    @Override
    public ArrayList<Sales> getAll() throws IOException, ParserConfigurationException, SAXException {
        ArrayList<Sales> saleList = new ArrayList<>();
        Element element = DataTransfer.getInstance().read("sales");
        NodeList saleNodeList = element.getElementsByTagName("sale");
        for (int i = 0; i < saleNodeList.getLength(); i++) {
            int saleId = Integer.parseInt(saleNodeList.item(i).getAttributes().getNamedItem("saleId").getTextContent());
            Sales sale = get(saleId);
//            System.out.println("Got sale: " + "\n"
//                + "SaleId: " + sale.getSaleId() + "\n"
//                + "Product: " + sale.getProduct() + "\n"
//                + "Price: " + sale.getPrice() + "\n"
//                + "Copies: " + sale.getCopies() + "\n"
//                + "SaleDate: " + sale.getSalesDate() + "\n"
//                + "CustomerId: " + sale.getCustomerId() + "\n"
//                + "EmployeeId: " + sale.getEmployeeId() + "\n");
            saleList.add(sale);
        }

        return saleList;
    }

    @Override
    public Node save(Sales sale) {
        try {
            if (getAll().contains(sale)) {
                throw new Exception();
            } else {
                NodeList nodes = database.getElementsByTagName("sales");
                Element element = (Element) nodes.item(0);
                Element newSaleNode = database.createElement("sale");
                newSaleNode.setAttribute("saleId", String.valueOf(sale.getSaleId()));
                newSaleNode.setAttribute("customerId", String.valueOf(sale.getCustomerId()));
                newSaleNode.setAttribute("refunded", sale.getRefunded());
                Element product = database.createElement("product");
                product.appendChild(database.createTextNode(sale.getProduct()));
                Element price = database.createElement("price");
                price.appendChild(database.createTextNode(String.valueOf(sale.getPrice())));
                Element copies = database.createElement("copies");
                copies.appendChild(database.createTextNode(String.valueOf(sale.getCopies())));
                Element saleDate = database.createElement("saleDate");
                saleDate.appendChild(database.createTextNode(sale.getSalesDate()));

                newSaleNode.appendChild(product);
                newSaleNode.appendChild(price);
                newSaleNode.appendChild(copies);
                newSaleNode.appendChild(saleDate);
                element.appendChild(newSaleNode);
                DataTransfer.getInstance().write();

                return newSaleNode;
            }
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("Save error: sale already exist in database");
        }

        return null;
    }

    @Override
    public void update(Sales newSale, Sales oldSale)  {
        try {
            Element element = DataTransfer.getInstance().read("sales");
            Node oldSaleNode = locateObject(element, "sale", oldSale.getSaleId());
            element.replaceChild(save(newSale), oldSaleNode);
            DataTransfer.getInstance().write();
        } catch (Exception e) {
            System.out.println("Update error: This sale doesn't exist");
        }
    }

    @Override
    public void gdpaDelete(Sales sale) {
        try {
            Element element = DataTransfer.getInstance().read("sales");
            Node saleNode = locateObject(element, "sale", sale.getSaleId());
            element.removeChild(saleNode);
            DataTransfer.getInstance().write();
        } catch (Exception e) {
            System.out.println("gdpaDelete error:  This sale doesn't exist");
        }
    }

    @Override
    public void delete(Sales sale){
        try {
            Element element = DataTransfer.getInstance().read("sales");
            Node saleNode = locateObject(element, "sale", sale.getSaleId());
            saleNode.getAttributes().getNamedItem("refunded").setTextContent("yes");
            saleNode.getAttributes().getNamedItem("active").setTextContent("inactive");
            DataTransfer.getInstance().write();
        } catch (Exception e) {
            System.out.println("Delete error:  This employee doesn't exist");
        }
    }
}
