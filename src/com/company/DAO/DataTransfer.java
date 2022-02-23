package com.company.DAO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class DataTransfer<T> {
    protected final Document database;
    private static DataTransfer instance;

    public DataTransfer() throws IOException, SAXException, ParserConfigurationException {
        String databasePath = "database.xml";
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        database = documentBuilder.parse(new File(databasePath));
    }

    public static DataTransfer getInstance() throws IOException, ParserConfigurationException, SAXException {
        if (instance == null){
            instance = new DataTransfer();
        }
        return instance;
    }

    public Element read(String elementTag){
        NodeList list = database.getElementsByTagName(elementTag);

        for (int temp = 0; temp < list.getLength(); temp++) {
            Node node = list.item(temp);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                return (Element) node;
            }
        }
        return null;
    }


    public void write() throws TransformerException {
        DOMSource source = new DOMSource(database);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult("database.xml");
        transformer.transform(source, result);
    }

}
