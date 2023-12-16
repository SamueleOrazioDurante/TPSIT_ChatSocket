package server;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class DBManager {
    
    //inizializzazione variabili 
    private static Document db;
    
    //TBE
    private final int UserNotFound = 0;
    private final int WrongPassword = 1;
    private final int PasswordChecked = 2;
    

    protected static final String XML_FILE_PATH = "projectFiles\\src\\server\\dbChat.xml";
    private DocumentBuilder builder;
    private File XMLFile;

    // costruttore
    public DBManager(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try{
            builder = factory.newDocumentBuilder();
            XMLFile = new File(XML_FILE_PATH);

            //FORSE NECESSITA SEMAFORO
            db = builder.parse(XMLFile);
            //IFD
            
            removeWhitespaces(db.getDocumentElement());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //metodo checkuser 
    public synchronized int CheckUser(String usr, String psw){
        int result = UserNotFound;
        Node usersNode = db.getElementById(usr);

        //controllo se il nodo esiste (result rimane come utente non trovato). Se esiste controllo se la password è uguale
        if(usersNode!=null){
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
        //metodo per raccogliore dati (messaggi) dal database

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
}
