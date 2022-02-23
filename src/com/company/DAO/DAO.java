package com.company.DAO;

import com.company.BO.Employee;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;


public interface DAO<T> {
    Node locateObject(Element element, String elementTag, int id);

    String locateObjectData(Node node, String elementName);

    T get(int id) throws IOException, ParserConfigurationException, SAXException, TransformerException;

    ArrayList getAll() throws IOException, ParserConfigurationException, SAXException, TransformerException;

    Node save(T t) throws Exception;

    void update(T newObj, T oldObj) throws IOException, ParserConfigurationException, SAXException, TransformerException;

    void gdpaDelete(T t) throws IOException, ParserConfigurationException, SAXException, TransformerException;

    void delete(T t) throws IOException, ParserConfigurationException, SAXException, TransformerException;

}
