package interfaces;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import client.Message;

public class XMLMsgBuilder {

    private static DocumentBuilderFactory factory;
    private static DocumentBuilder docBuild;
    private static Boolean exist = false;

    public XMLMsgBuilder(){

        //costruttore parte solo una volta
        if(!exist){
            factory = DocumentBuilderFactory.newInstance();
            try {
                docBuild = factory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            exist = true;
        }
    }

    //metodo creazione pacchetto login
    public Document createLoginXMLObj(String usr,String psw){
        //creazione documento e elemento radice
        Document doc = docBuild.newDocument();
        Element rootElement = doc.createElement("XMLPkt");
        doc.appendChild(rootElement);

        //crezione dell'elemento Operation che contiene l'operazione che si sta svolgendo
        Element e = doc.createElement("Operation");
        e.setTextContent("login");
        rootElement.appendChild(e);

       //elemento username 
        e = doc.createElement("username");
        e.setTextContent(usr);
        rootElement.appendChild(e);

        //elemento password
        e = doc.createElement("password");
        e.setTextContent(psw);
        rootElement.appendChild(e);
        
        return doc;
    }

    //metodo creazione pacchetto signup
    public Document createSignUpXMLObj(String usr,String psw){
        //creazione documento e elemento radice
        Document doc = docBuild.newDocument();
        Element rootElement = doc.createElement("XMLPkt");
        doc.appendChild(rootElement);

        //crezione dell'elemento Operation che contiene l'operazione che si sta svolgendo
        Element e = doc.createElement("Operation");
        e.setTextContent("signUp");
        rootElement.appendChild(e);

       //elemento username 
        e = doc.createElement("username");
        e.setTextContent(usr);
        rootElement.appendChild(e);

        //elemento password
        e = doc.createElement("password");
        e.setTextContent(psw);
        rootElement.appendChild(e);
        
        return doc;
    }
    

    //metodo trasformazione NodeList in Document
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
        Element eChat = doc.createElement("Chat");
        if(chat==null)
            return doc;
        for(int i = 0; i<chat.getLength();i++){
            Node msg = doc.importNode(chat.item(i),true);
            eChat.appendChild(msg);
        }

        rootElement.appendChild(eChat);
        
        return doc;
    }

     //metodo per invio messaggio tramite pacchetto XML
    public Document createMsgCXMLObj(Message msg){
        Document doc = docBuild.newDocument();
        Element rootElement = doc.createElement("XMLPkt");
        doc.appendChild(rootElement);

        //vengono creati un elemento operation e un nodo messaggio che conterrà il contenuto del messaggio stesso
        Element e = doc.createElement("Operation");
        e.setTextContent("SendMsg"); //ex MsgFwd
        rootElement.appendChild(e);
        Element eChatList = doc.createElement("ChatMessage");
        rootElement.appendChild(eChatList);

        e = doc.createElement("Content");
        e.setTextContent(msg.getMsg());
        eChatList.appendChild(e);

         e = doc.createElement("Sender");
        e.setTextContent(msg.getReceiver());
        eChatList.appendChild(e);

        e = doc.createElement("Receiver");
        e.setTextContent(msg.getSender());
        eChatList.appendChild(e);

        Node msgXML = doc.importNode(eChatList, true);
        rootElement.appendChild(msgXML);

        return doc;
    }

    //metodo per invio messaggio tramite pacchetto XML
    public Document createMsgXMLObj(Element msg){
        Document doc = docBuild.newDocument();
        Element rootElement = doc.createElement("XMLPkt");
        doc.appendChild(rootElement);

        //vengono creati un elemento operation e un nodo messaggio che conterrà il contenuto del messaggio stesso
        Element e = doc.createElement("Operation");
        e.setTextContent("SendMsg"); //ex MsgFwd
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

    //metodo per caricare i contatti dal db al client
    public Document createLoadContactRequestXMLObj(String usr){

        Document doc = docBuild.newDocument();
        Element rootElement = doc.createElement("XMLPkt");
        doc.appendChild(rootElement);

        //vengono creati un elemento operation e un nodo che contiene l'utente che richiede tutti gli altri utenti
        Element e = doc.createElement("Operation");
        e.setTextContent("ContactRequest");
        rootElement.appendChild(e);

        e = doc.createElement("user");
        e.setTextContent(usr);
        rootElement.appendChild(e);

        return doc;
    }

    //metodo per caricare le chat dal db al client
    public Document createLoadChatRequestXMLObj(String usr_a,String usr_b){

        Document doc = docBuild.newDocument();
        Element rootElement = doc.createElement("XMLPkt");
        doc.appendChild(rootElement);

        //vengono creati un elemento operation e un nodo che contiene i due utenti
        Element e = doc.createElement("Operation");
        e.setTextContent("loadChat");
        rootElement.appendChild(e);

        e = doc.createElement("localUser");
        e.setTextContent(usr_a);
        rootElement.appendChild(e);

        e = doc.createElement("remoteUser");
        e.setTextContent(usr_b);
        rootElement.appendChild(e);

        return doc;
    }

    //metodo per creazione pacchetto XML con tutti i contatti all'interno
    public Document createContactsXMLObj(NodeList contacts){

        //creazione documento e elemento radice
        Document doc = docBuild.newDocument();
        Element rootElement = doc.createElement("XMLPkt");
        doc.appendChild(rootElement);

        //crezione dell'elemento Operation che contiene l'operazione che si sta svolgendo
        Element e = doc.createElement("Operation");
        e.setTextContent("ContactsLoading");
        rootElement.appendChild(e);

        //si crea infine l'elemento chat vero e proprio contenente ogni user
        Element eUser = doc.createElement("userList");

        for(int i = 0; i<contacts.getLength();i++){
            Node contact = doc.importNode(contacts.item(i),true);
            eUser.appendChild(contact);
        }

        rootElement.appendChild(eUser);
        
        return doc;
    }

}

