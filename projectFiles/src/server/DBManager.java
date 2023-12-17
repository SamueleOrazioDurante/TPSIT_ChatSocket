package server;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DBManager {
    
    //inizializzazione variabili 
    private static Document db;
    
    //TBE
    private final int UserNotFound = 0;
    private final int WrongPassword = 1;
    private final int PasswordChecked = 2;
    
    private final int UserDuplicated = 0;
    private final int SignUpChecked = 1;

    protected static final String XML_FILE_PATH = "server\\resources\\dbChat.xml";
    protected static final String DTD_FILE_PATH = "server\\resources\\dbChat.dtd";
    private DocumentBuilder builder;
    private File XMLFile;

     // costruttore
    public DBManager(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    
        try{
            builder = factory.newDocumentBuilder();
            XMLFile = new File(XML_FILE_PATH);

            db = builder.parse(XMLFile);
            
            removeWhitespaces(db.getDocumentElement());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //metodo checkuser 
    public synchronized int CheckUser(String usr, String psw){
        int result = UserNotFound;
        Node usersNode = db.getElementById(usr);
        
        System.out.println("sdadas1");
        //controllo se il nodo esiste (result rimane come utente non trovato). Se esiste controllo se la password è uguale
        if(usersNode!=null){
            System.out.println("sdadas2");
            if(usersNode.getFirstChild().getTextContent().equals(psw)){
                result = PasswordChecked; 
                //result diventa utente trovato e verificato
            }else{
                result = WrongPassword;
                //result diventa utente trovato ma non verificato
            }
        }
        return result;
    }

    //metodo addUserToDB 
    public synchronized int addUserToDB(String usr,String psw){
        int result = UserDuplicated;

        Node userNode = db.getElementById(usr);

        NodeList usersList = db.getElementsByTagName("UserList");

        //controllo se il nodo esiste. Se esiste controllo l'utente esiste già
        if(userNode==null){
            Element userList = (Element)usersList.item(0);
            
            Element user = db.createElement("user");
                    user.setAttribute("username",usr);
            Element password = db.createElement("password");
                    password.setTextContent(psw);
                    user.appendChild(password);
                    userList.appendChild(user);
                    
                    user.setIdAttribute("username", true);

                    try {
                        saveXmlDocument(db,XML_FILE_PATH);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            result=SignUpChecked;
        }

        return result;
    }

    //metodo UpdateDB, per aggiungere messaggi
    public synchronized boolean UpdateDB(Element msg){
        Boolean result = false;
        
        try {

            //estrapolazione di sender e receiver dal pacchetto messagio
            String send = msg.getElementsByTagName("Sender").item(0).getTextContent();
            String receive = msg.getElementsByTagName("Receiver").item(0).getTextContent();
            
            //creazione di un elemento chat con tutti i messaggi della chat tra i due usr e un nodo con il messaggio da aggiungere
            Element chat = db.getElementById(send+"-"+receive);
            Node newMsg = db.importNode(msg,true);

            //ricerca se la chat esiste o no
            if(chat != null){
                chat.appendChild(newMsg);
            }else{
                //ricerca se la chat esiste con un nome diverso
                chat = db.getElementById(receive+"-"+send);
                if(chat!= null){
                    chat.appendChild(newMsg);
                }else{
                    //se non esiste ne crea una nuova (creo l'elemento chat, metto come attributo id il nome, inserisco dentro il messaggio e la inserisco dentro la lista di chat)
                    Element chatN = db.createElement("chat");
                    chatN.setAttribute("id",send+"-"+receive);
                    chatN.appendChild(newMsg);
                    Element chatListN = (Element)db.getElementsByTagName("ChatList");
                    chatListN.appendChild(chatN);
                }
            }

            

            //operazioni svolte con successo
            result = true;

        } catch (Throwable t) {
            t.printStackTrace();
        }

        return result;
    }
        //metodo per raccogliere dati (messaggi) dal database

        public synchronized NodeList GetDSInfo(String usr_a,String usr_b){
            NodeList chatMsg = null;
            //uso il metodo per ritrovare l'elemento chat
            Element chat = GetChatFromDB(usr_a,usr_b);
            //uso quell'elemento per ricavare la NodeList dei messaggi
            if(chat!=null){
                chatMsg = chat.getChildNodes();
            }
            return chatMsg;
        }

        //metodo per cercare la chat nel database con input gli user

        public synchronized Element GetChatFromDB(String usr_a,String usr_b){
            //cerco elemento con id i due user
            Element chat = db.getElementById(usr_a+"-"+usr_b);
            if (chat == null) {
                //se non lo trovo,inverso gli user
                chat = db.getElementById(usr_b+"-"+usr_a);
            }
            return chat;
        }

        //metodo per rimuovere gli spazi bianchi (NOT EDITED)

        public synchronized static void removeWhitespaces(Element element){
            NodeList children = element.getChildNodes();
            for(int i = children.getLength()-1;i>=0;i--){
                Node child = children.item(i);
                if (child instanceof Text && ((Text) child).getData().trim().isEmpty()) {
                    element.removeChild(child);
                }else if(child instanceof Element){
                    removeWhitespaces((Element)child);
                }
            }
        }

        //metodo per ottenere la lista di tutti i contatti

        public synchronized NodeList GetContacts(String usr){
            NodeList contacts = db.getElementsByTagName("user");
            return contacts;
        }

        private synchronized static void saveXmlDocument(Document document, String filePath) throws Exception {
            // Salva il documento XML aggiornato
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            //modifica proprietà per aggiungere dtd e indentazione automatica
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, DTD_FILE_PATH);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        }
}