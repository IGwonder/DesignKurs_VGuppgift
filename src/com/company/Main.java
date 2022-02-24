package com.company;

import com.company.BO.Customer;
import com.company.BO.Employee;
import com.company.BO.Sales;
import com.company.DAO.CustomerDAO;
import com.company.DAO.EmployeeDAO;
import com.company.DAO.SalesDAO;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;


public class Main {


    public static void main(String[] args) throws Exception {
/*        String databasePath = "database.xml";
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(new File(databasePath));

        NodeList list = doc.getElementsByTagName("customers");
        for (int temp = 0; temp < list.getLength(); temp++) {
            Node node = list.item(temp);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                for (int i = 0; i < element.getElementsByTagName("customer").getLength(); i++){
                    String customer = element.getElementsByTagName("customer").item(i).getTextContent();
                    if (element.getElementsByTagName("customer").item(i).getAttributes().getNamedItem("id").getTextContent().equals("2")){ //Söka efter kund id
                        System.out.println("Customer id:  " + element.getElementsByTagName("customer").item(i).getAttributes().getNamedItem("id").getTextContent() + customer);
                    }
                }
            }
        }*/

        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer1 = new Customer(1, "John", "Gata 1");
        Customer customer2 = new Customer(2, "Frida", "Gata 2");
        Customer customer3 = new Customer(3, "Tess", "Gata 3");
        Customer customer4 = new Customer(4, "Jack", "Gata 4");
        Customer customer3Update = new Customer(customer3.getId(), customer3.getName(), "Gata 5");

        SalesDAO salesDAO = new SalesDAO();
        SalesControl salesControl = new SalesControl();
        Sales sale1 = new Sales(1, "Product 1", 100, 4, "2022-01-15", customer1.getId());
        Sales sale2 = new Sales(2, "Product 2", 50, 2, "2022-02-15", customer2.getId());
        Sales sale3 = new Sales(3, "Product 3", 200, 1, "2022-02-20", customer1.getId());
        Sales sale4 = new Sales(4, "Product 4", 75, 1, "2022-02-21", customer3.getId());
        Sales sale5 = new Sales(5, "Product 3", 400, 2, "2022-02-22", customer3.getId());
        Sales sale6 = new Sales(6, "Product 1", 50, 2, "2022-02-22", customer4.getId());

        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee1 = new Employee(1, "Peter", "Gata A");
        employee1.setCustomers(customer1);
        employee1.setCustomers(customer2);
        employee1.setSalesList(sale1);
        employee1.setSalesList(sale2);

        Employee employee2 = new Employee(2, "Knox", "Gata B");
        employee2.setCustomers(customer2);
        employee2.setCustomers(customer3);
        employee2.setSalesList(sale3);
        employee2.setSalesList(sale5);

        Employee employee3 = new Employee(3, "Alan", "Gata C");
        employee3.setCustomers(customer3);
        employee3.setCustomers(customer4);
        employee3.setSalesList(sale4);
        employee3.setSalesList(sale6);


        salesDAO.save(sale1);
        salesDAO.save(sale2);
        salesDAO.save(sale3);
        salesDAO.save(sale4);
        salesDAO.save(sale5);
        salesDAO.save(sale6);


//        employeeDAO.gdpaDelete(employee1);
//        employeeDAO.gdpaDelete(employee2);
//        employeeDAO.gdpaDelete(employee3);

//        System.out.println(employeeDAO.getAll().contains(employee1));

//        employeeDAO.save(employee1);
//        employeeDAO.save(employee2);
//        employeeDAO.save(employee3);


//        customerDAO.gdpaDelete(customer1);
//        customerDAO.gdpaDelete(customer2);
//        customerDAO.gdpaDelete(customer3);
//        customerDAO.gdpaDelete(customer4);

//        customerDAO.save(customer1);
//        customerDAO.save(customer2);
//        customerDAO.save(customer3);
//        customerDAO.save(customer4);

//        System.out.println("Searched customer: " + "\n"
//                + "Id: " + customerDAO.get(1).getId() + "\n"
//                + "Active: " + customerDAO.get(1).getActive() + "\n"
//                + "Name: " + customerDAO.get(1).getName() + "\n"
//                + "Address: " + customerDAO.get(1).getAddress() + "\n");

//        for(Employee employee : employeeDAO.getAll()) {
//            System.out.println("Searched employee: " + "\n"
//                    + "Id: " + employee.getId() + "\n"
//                    + "Active: " + employee.getActive() + "\n"
//                    + "Name: " + employee.getName() + "\n"
//                    + "Address: " + employee.getAddress() + "\n");
//        }

    }

}

