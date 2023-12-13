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
    /* 
    private final int UserNotFound = 0;
    private final int WrongPassword = 1;
    private final int PasswordChecked = 2;
    */

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
            Node newMsg = db.importNode(msg);

            //ricerca se la chat esiste o no
            if(chat != null){
                chat.appendChild(newMsg);
            }else{
                chat = db.getElementById(send+"-"+receive);
                //TBD
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
        //metodo
}
