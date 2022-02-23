package com.company.DAO;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class SalesDAO implements DAO{

    @Override
    public Node locateObject(Element element, String elementTag, int id) {
        return null;
    }

    @Override
    public String locateObjectData(Node node, String elementName) {
        return null;
    }

    @Override
    public Object get(int id) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        return null;
    }

    @Override
    public ArrayList getAll() throws IOException, ParserConfigurationException, SAXException, TransformerException {
        return null;
    }

    @Override
    public Node save(Object o) throws Exception {
        return null;
    }

    @Override
    public void update(Object newObj, Object oldObj) throws IOException, ParserConfigurationException, SAXException, TransformerException {

    }

    @Override
    public void gdpaDelete(Object o) throws IOException, ParserConfigurationException, SAXException, TransformerException {

    }

    @Override
    public void delete(Object o) throws IOException, ParserConfigurationException, SAXException, TransformerException {

    }
}
