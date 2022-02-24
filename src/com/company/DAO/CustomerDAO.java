package com.company.DAO;

import com.company.BO.Customer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class CustomerDAO implements DAO<Customer> {
    private final Document database = DataTransfer.getInstance().database;

    public CustomerDAO() throws IOException, ParserConfigurationException, SAXException {
    }


    @Override
    public Node locateObject(Element element, String elementTag, int id) {
        Node employeeData = null;
        for (int j = 0; j < element.getElementsByTagName(elementTag).getLength(); j++) {
            employeeData = element.getElementsByTagName(elementTag).item(j);

            if (employeeData.getAttributes().getNamedItem("id").getTextContent().equals(String.valueOf(id))) {
                return employeeData;
            }
        }

        return null;
    }

    @Override
    public String locateObjectData(Node node, String elementName) {
        Node employeeData = null;
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            employeeData = node.getChildNodes().item(i);

            if (employeeData.getNodeName().equals(elementName)) {
                return employeeData.getTextContent();
            }
        }

        return null;
    }

    @Override
    public Customer get(int id) throws IOException, ParserConfigurationException, SAXException {
        try {
            Element element = DataTransfer.getInstance().read("customers");
            Node customerNode = locateObject(element,"customer", id);
            String customerActive = customerNode.getAttributes().getNamedItem("active").getTextContent();
            String customerName = locateObjectData(customerNode, "name");
            String customerAddress = locateObjectData(customerNode, "address");
            Customer customer = new Customer(id, customerActive, customerName, customerAddress);

            return customer;
        } catch (NullPointerException e) {
            System.out.println("Get error: This customer doesn't exist");
            return null;
        }

    }

    @Override
    public ArrayList<Customer> getAll() throws IOException, ParserConfigurationException, SAXException {
        ArrayList<Customer> customerList = new ArrayList<>();
        Element element = DataTransfer.getInstance().read("customers");
        NodeList customerNodeList = element.getElementsByTagName("customer");
        for (int i = 0; i < customerNodeList.getLength(); i++) {
            int customerId = Integer.parseInt(customerNodeList.item(i).getAttributes().getNamedItem("id").getTextContent());
            Customer customer = get(customerId);
            if (customer.getActive().equals("active")){
                customerList.add(customer);
            }
        }

        return customerList;
    }

    @Override
    public Node save(Customer customer) {
        try {
            if (getAll().contains(customer)) {
                throw new Exception();
            } else {
                NodeList nodes = database.getElementsByTagName("customers");
                Element element = (Element) nodes.item(0);
                Element newCustomerNode = database.createElement("customer");
                newCustomerNode.setAttribute("id", String.valueOf(customer.getId()));
                newCustomerNode.setAttribute("active",customer.getActive());
                Element name = database.createElement("name");
                name.appendChild(database.createTextNode(customer.getName()));
                Element address = database.createElement("address");
                address.appendChild(database.createTextNode(customer.getAddress()));

                newCustomerNode.appendChild(name);
                newCustomerNode.appendChild(address);
                element.appendChild(newCustomerNode);
                DataTransfer.getInstance().write();

                return newCustomerNode;
            }
        } catch (Exception e) {
            System.out.println("Save error: Customer already exist in database");
        }

        return null;
    }

    @Override
    public void update(Customer newCustomer, Customer oldCustomer) {
        try {
            Element element = DataTransfer.getInstance().read("customers");
            Node oldCustomerNode = locateObject(element, "customer",oldCustomer.getId());
            element.replaceChild(save(newCustomer), oldCustomerNode);
            DataTransfer.getInstance().write();
        } catch (Exception e) {
            System.out.println("Update error: This customer doesn't exist");
        }
    }

    @Override
    public void gdpaDelete(Customer customer) {
        try {
            Element element = DataTransfer.getInstance().read("customers");
            Node customerNode = locateObject(element,"customer", customer.getId());
            element.removeChild(customerNode);
            DataTransfer.getInstance().write();
        } catch (Exception e) {
            System.out.println("gdpaDelete error:  This customer doesn't exist");
        }
    }

    @Override
    public void delete(Customer customer) {
        try {
            Element element = DataTransfer.getInstance().read("customers");
            Node customerNode = locateObject(element, "customer", customer.getId());
            customerNode.getAttributes().getNamedItem("active").setTextContent("inactive");
            DataTransfer.getInstance().write();
        } catch (Exception e) {
            System.out.println("Delete error:  This customer doesn't exist");
        }
    }
}
