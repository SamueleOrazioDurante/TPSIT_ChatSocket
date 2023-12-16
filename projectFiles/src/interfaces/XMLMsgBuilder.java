package interfaces;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLMsgBuilder {

    DocumentBuilder docBuild;
    
    public XMLMsgBuilder(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuild = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    

    //metodo trasportmagione NodeList in Document
    public Document createChatXMLObj(NodeList chat){

        //creazione documento e elemento radice
        Document doc = docBuild.newDocument();
        Element rootElement = doc.createElement("XMLPkt");
        doc.appendChild(rootElement);

        //crezione dell'elemento Operation che contiene l'operazione che si sta svolgendo
        Element e = doc.createElement("Operation");
        e.setTextContent("ChatLoading");
        rootElement.appendChild(e);

        //si crea infine l'elemento chat vero e proprio
        Element eChat = doc.createElement("chat");

        for(int i = 0; i<chat.getLength();i++){
            Node msg = doc.importNode(chat.item(i),true);
            eChat.appendChild(msg);
        }

        rootElement.appendChild(eChat);
        
        return doc;
    }

    //metodo per invio messaggio tramite pacchetto XML
    public Document createMsgXMLObj(Element msg){
        Document doc = docBuild.newDocument();
        Element rootElement = doc.createElement("XMLPkt");
        doc.appendChild(rootElement);

        //vengono creati un elemento operation e un nodo messaggio che conterrà il contenuto del messaggio stesso
        Element e = doc.createElement("Operation");
        e.setTextContent("MsgFwd");
        rootElement.appendChild(e);
        Node msgXML = doc.importNode(msg, true);
        rootElement.appendChild(msgXML);

        return doc;
    }

    //metodo per creazione ACK (dare conferma al mittente che il messaggio è arrivato)
    public Document createAckXMLObj(){
        //creazione di un documento semplice con un solo elemento che definisce la sua funzionalità di conferma di invio del messaggio
        Document doc = docBuild.newDocument();
        Element rootElement = doc.createElement("XMLPkt");
        doc.appendChild(rootElement);

        Element e = doc.createElement("Operation");
        e.setTextContent("AckSentMessage");
        rootElement.appendChild(e);

        return doc;
    }

    
}

