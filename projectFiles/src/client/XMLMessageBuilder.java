package client;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import interfaces.XMLMsg;

public class XMLMessageBuilder {
    
    private XMLMsg msg;
    private static DocumentBuilderFactory docFactory;
    private static DocumentBuilder docBuilder;
    private static Boolean instanciated = false;

    public XMLMessageBuilder(String m, String s, String r)
    {
        //create a message (text+sender+receiver)
        msg = new XMLMsg(m, s, r);
        if(!instanciated)
        {
            try
            {
                docFactory = DocumentBuilderFactory.newInstance();
                docBuilder = docFactory.newDocumentBuilder();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            instanciated = true;
        }
        
    }

    public Document getXMLObject()
    {
        //Create an xml object (Document)
        Document doc = docBuilder.newDocument();
        //Create the root element
        Element rootElement = doc.createElement("ChatMessage");
        //Add the element to the xml document
        doc.appendChild(rootElement);

        //Add Content, Sender and Receiver to the ChatMessage element as children elements
        Element e = doc.createElement("Content");
        e.setTextContent(msg.getMessage());
        rootElement.appendChild(e);
        e = doc.createElement("Sender");
        e.setTextContent(msg.getSender());
        rootElement.appendChild(e);
        e = doc.createElement("Receiver");
        e.setTextContent(msg.getReceiver());
        rootElement.appendChild(e);

        //return the document object
        return doc;
    }

}
