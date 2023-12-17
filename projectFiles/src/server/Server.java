package server;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import interfaces.ChatFeat;

public class Server implements ChatFeat{

    //inizializzazione variabili 
    private DBManager dbm;
    private serverGUI gui;

    //TBE
    private final int UserNotFound = 0;
    private final int WrongPassword = 1;
    private final int PasswordChecked = 2;

    private final int UserDuplicated = 0;
    private final int SignUpChecked = 1;

	public Server(DBManager db,serverGUI gui){
        this.dbm=db;
        this.gui=gui;
        gui.setVisible(true);
    }

    @Override
    //metodo per verificare che l'utente esista e che le credenziali siano corrette
    public int login(String usr,String psw){
        int result = UserNotFound;
        switch (dbm.CheckUser(usr, psw)){
            case 1:
                return WrongPassword;
            case 2:
                return PasswordChecked;
            default:
                break;
        };
        return result;
    }

    @Override
    //metodo per verificare che l'utente inserisca un nome utente unico e fare la registrazione
    public int signUp(String usr,String psw){
        int result = UserDuplicated;
        switch (dbm.addUserToDB(usr, psw)){
            case 1:
                return SignUpChecked;
            default:
                break;
        };
        return result;
    }

    @Override
    //metodo per caricare le chat
    public NodeList LoadChat(String usr_a,String usr_b){
        NodeList chatMsgs = dbm.GetDSInfo(usr_a, usr_b);
        return chatMsgs;
    }

    @Override
    //metodo per mandare il messaggio al ricevente
    public String SendMsg(Element msg){
        dbm.UpdateDB(msg);
        return msg.getElementsByTagName("receive").item(0).getTextContent();
    }

    @Override
    //metodo per caricare i contatti 
    public NodeList LoadContacts(String usr){
        return dbm.GetContacts(usr);
    }

    //metodo per utilizzo GUI
    public void AddMsgTerminal(String msg){
        gui.addConsoleTerminal(msg);
    }
}
