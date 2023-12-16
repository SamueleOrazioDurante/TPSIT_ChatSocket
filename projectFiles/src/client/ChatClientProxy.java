package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import interfaces.ChatFeat;
import interfaces.XMLMsgBuilder;
import interfaces.ClientBLInterface;

public class ChatClientProxy implements ChatFeat{

    private Socket skt;
    private ClientBLInterface clientInterface;
    private XMLMsgBuilder msgBuilder;
    private ChatClientThread clientThread;
    
    //proxy si occupa solo della parte di comunicazione
    public ChatClientProxy(){
        try{
            //alloca il nuovo socket e message builder, e crea le base per avere l'interfaccia del client e il thread del client
            skt = new Socket("localhost",6969);
            clientInterface = null;
            msgBuilder = new XMLMsgBuilder();
            clientThread = null;
        }catch(UnknownHostException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    //metodo per
    public int login(String usr, String psw){
        int logResult = 0;
        try{
            //apertura canali di comunicazione in/out
            ObjectOutputStream oos = new ObjectOutputStream(skt.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(skt.getInputStream());

            //creazione pacchetto con metodi del message builder
            Document pkt = msgBuilder.createLoginXMLObj(usr, psw);
            oos.writeObject(pkt);

            //se il login ha successo chiudo la comunicazione
            if(logResult==2){
                skt.close();
                oos.close();
                ois.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        return logResult;
    }

    //metodo per creare il nuovo thread che gestirá
    public void newClientThread(ClientBLInterface clientInterface){
        //ci = clientInterface;
        clientThread = new ChatClientThread(skt,clientInterface);
        clientThread.start();
    }

    //metodo
    public void destroyClientThread(){
        clientThread.destroy();
    }

    @Override
    //metodo
    public NodeList LoadChat(String usr_a,String usr_b){
        //TDB
        NodeList TDB = null;
        return TDB;
    }

    @Override
    //metodo
    public String SendMsg(Element msg){
        //TDB
        String TDB = null;
        return TDB;
    }

    @Override
    //metodo
    public NodeList LoadContacts(String usr){
        NodeList s = null;
        return s;
    }
}