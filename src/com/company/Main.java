package com.company;

import com.company.BO.Customer;
import com.company.BO.Employee;
import com.company.DAO.CustomerDAO;
import com.company.DAO.EmployeeDAO;


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

        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee1 = new Employee(1, "Peter", "Gata A");
        employee1.setCustomers(customer1);
        employee1.setCustomers(customer2);

        Employee employee2 = new Employee(2, "Knox", "Gata B");
        employee2.setCustomers(customer2);
        employee2.setCustomers(customer3);

        Employee employee3 = new Employee(3, "Alan", "Gata C");
        employee3.setCustomers(customer3);
        employee3.setCustomers(customer4);

        employeeDAO.gdpaDelete(employee1);
        employeeDAO.gdpaDelete(employee2);
        employeeDAO.gdpaDelete(employee3);

//        System.out.println(employeeDAO.getAll().contains(employee1));

        employeeDAO.save(employee1);
        employeeDAO.save(employee2);
        employeeDAO.save(employee3);

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

