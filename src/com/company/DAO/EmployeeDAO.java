package com.company.DAO;

import com.company.BO.Customer;
import com.company.BO.Employee;
import com.sun.media.jfxmediaimpl.HostUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.print.PrintException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeDAO implements DAO<Employee> {
    private final Document database = DataTransfer.getInstance().database;

    public EmployeeDAO() throws IOException, ParserConfigurationException, SAXException {
    }

    @Override
    public Node locateObject(Element element, String elementTag, int employeeId) {
        Node employeeData = null;
        for (int j = 0; j < element.getElementsByTagName(elementTag).getLength(); j++) {
            employeeData = element.getElementsByTagName(elementTag).item(j);

            if (employeeData.getAttributes().getNamedItem("id").getTextContent().equals(String.valueOf(employeeId))) {
                return employeeData;
            }
        }

        return null;
    }

    @Override
    public String locateObjectData(Node employeeNode, String elementName) {
        Node employeeData = null;
        for (int i = 0; i < employeeNode.getChildNodes().getLength(); i++) {
            employeeData = employeeNode.getChildNodes().item(i);

            if (employeeData.getNodeName().equals(elementName)) {
                return employeeData.getTextContent();
            }
        }

        return null;
    }

    @Override
    public Employee get(int id) throws IOException, ParserConfigurationException, SAXException {
        try {
            Element element = DataTransfer.getInstance().read("employees");
            Node employeeNode = locateObject(element, "employee", id);
            String employeeActive = employeeNode.getAttributes().getNamedItem("active").getTextContent();
            String employeeName = locateObjectData(employeeNode, "name");
            String employeeAddress = locateObjectData(employeeNode, "address");
            Employee employee = new Employee(id, employeeActive, employeeName, employeeAddress);

            return employee;
        } catch (NullPointerException e) {
//            e.printStackTrace();
            System.out.println("Get error: This employee doesn't exist");
            return null;
        }
    }

    @Override
    public ArrayList<Employee> getAll() throws IOException, ParserConfigurationException, SAXException {
        ArrayList<Employee> employeeList = new ArrayList<>();
        Element element = DataTransfer.getInstance().read("employees");
        NodeList employeeNodeList = element.getElementsByTagName("employee");
        for (int i = 0; i < employeeNodeList.getLength(); i++) {
            int employeeId = Integer.parseInt(employeeNodeList.item(i).getAttributes().getNamedItem("id").getTextContent());
            Employee employee = get(employeeId);
            if (employee.getActive().equals("active")){
                employeeList.add(employee);
            }
        }

        return employeeList;
    }

    @Override
    public Node save(Employee employee) {
        try {
            if (getAll().contains(employee)) {
                throw new Exception();
            } else {
                NodeList nodes = database.getElementsByTagName("employees");
                Element element = (Element) nodes.item(0);
                Element newEmployeeNode = database.createElement("employee");
                newEmployeeNode.setAttribute("id", String.valueOf(employee.getId()));
                newEmployeeNode.setAttribute("active",employee.getActive());
                Element name = database.createElement("name");
                name.appendChild(database.createTextNode(employee.getName()));
                Element address = database.createElement("address");
                address.appendChild(database.createTextNode(employee.getAddress()));
                Element customers = database.createElement("customers");
                for (Customer customer : employee.getCustomerList()){
                    Element customerId = database.createElement("id");
                    customerId.setTextContent(String.valueOf(customer.getId()));
                    customers.appendChild(customerId);
                }

                newEmployeeNode.appendChild(name);
                newEmployeeNode.appendChild(address);
                newEmployeeNode.appendChild(customers);
                element.appendChild(newEmployeeNode);
                DataTransfer.getInstance().write();

                return newEmployeeNode;
            }
        } catch (Exception e) {
            System.out.println("Save error: employee already exist in database");
        }

        return null;
    }

    @Override
    public void update(Employee newEmployee, Employee oldEmployee) {
        try {
            Element element = DataTransfer.getInstance().read("customers");
            Node oldEmployeeNode = locateObject(element, "employee", oldEmployee.getId());
            element.replaceChild(save(newEmployee), oldEmployeeNode);
            DataTransfer.getInstance().write();
        } catch (Exception e) {
            System.out.println("Update error: This employee doesn't exist");
        }
    }

    @Override
    public void gdpaDelete(Employee employee)  {
        try {
            Element element = DataTransfer.getInstance().read("employees");
            Node customerNode = locateObject(element, "employee", employee.getId());
            element.removeChild(customerNode);
            DataTransfer.getInstance().write();
        } catch (Exception e) {
            System.out.println("gdpaDelete error:  This employee doesn't exist");
        }
    }

    @Override
    public void delete(Employee employee) {
        try {
            Element element = DataTransfer.getInstance().read("employees");
            Node customerNode = locateObject(element, "employee", employee.getId());
            customerNode.getAttributes().getNamedItem("active").setTextContent("inactive");
            DataTransfer.getInstance().write();
        } catch (Exception e) {
            System.out.println("Delete error:  This employee doesn't exist");
        }
    }
}
