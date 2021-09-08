package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public abstract class Store {
    private String storeName;

    //Dućan se brine o tome da nabavi podatke o svojim produktima iz xml datoteke
    //Kad se stvori objekt store spremati će se njegovi produkti u arraylist sa store.findProducts();
    //npr array = konzumStore.findProducts()
    public ArrayList<Product> createProducts()
    {
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            File fXmlFile = new File("Files/" + storeName + ".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            //root element = doc.getDocumentElement().getNodeName()

            NodeList nList = doc.getElementsByTagName("Product");

            //----------------------------

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                //Current element = nNode.getNodeName()

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    products.add(new KonzumProduct( eElement.getElementsByTagName("Name").item(0).getTextContent(),
                            Double.parseDouble(eElement.getElementsByTagName("Price").item(0).getTextContent()),
                            eElement.getElementsByTagName("Origin").item(0).getTextContent(),
                            eElement.getElementsByTagName("Expiry_date").item(0).getTextContent()));
                }
            }
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return products;
    }

    public ArrayList<Product> findProducts()
    {
        return createProducts();
    }

    public String getStoreName() {
        return storeName;
    }

    protected void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
