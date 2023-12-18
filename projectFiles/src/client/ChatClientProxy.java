package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import client.LoggedIndex;

import interfaces.ChatFeat;
import interfaces.XMLMsgBuilder;

public class ChatClientProxy implements ChatFeat{

    private Socket skt;
    private LoggedIndex clientInterface;
    private XMLMsgBuilder msgBuilder;
    private ChatClientThread clientThread;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    
    //proxy si occupa solo della parte di comunicazione
    public ChatClientProxy(){
        try{
            //alloca il nuovo socket e message builder, e crea le base per avere l'interfaccia del client e il thread del client
            skt = new Socket("localhost",6969);
            clientInterface = null;
            msgBuilder = new XMLMsgBuilder();
            clientThread = null;
            
            //apertura canali di comunicazione in/out
            oos = new ObjectOutputStream(skt.getOutputStream());
            ois = new ObjectInputStream(skt.getInputStream());

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

            //creazione pacchetto con metodi del message builder
            Document pkt = msgBuilder.createLoginXMLObj(usr, psw);
            oos.writeObject(pkt);

            logResult = (int)ois.readObject();

            //se il login non ha successo chiudo la comunicazione
            if(logResult!=2){
                skt.close();
                oos.close();
                ois.close();
            }
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return logResult;
    }

    @Override
    //metodo per
    public int signUp(String usr, String psw){
        int logResult = 0;
        try{

            //creazione pacchetto con metodi del message builder
            Document pkt = msgBuilder.createSignUpXMLObj(usr, psw);
            oos.writeObject(pkt);

            logResult = (int)ois.readObject();
            //se la registrazione ha successo chiudo la comunicazione
            if(logResult==1){
                skt.close();
                oos.close();
                ois.close();
            }
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return logResult;
    }

    //metodo per creare il nuovo thread che gestirá
    public void newClientThread(LoggedIndex clientInterface){
        //ci = clientInterface;
        //passo ois perchè il thread deve solo leggere

        clientThread = new ChatClientThread(ois,clientInterface);
        clientThread.start();
    }

    //metodo
    public void destroyClientThread(){
        clientThread.destroy();
    }

    @Override
    //metodo
    public NodeList LoadChat(String usr_a,String usr_b){
        try{

            //creazione pacchetto con metodi del message builder
            Document pkt = msgBuilder.createLoadChatRequestXMLObj(usr_a, usr_b);
            oos.writeObject(pkt);

        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    //metodo
    public String SendMsg(Element msg){
        //TDB
        String TDB = null;
        return TDB;
    }

    public String SendMsgToServer(Message msg){
        try{
            //creazione pacchetto con metodi del message builder
            Document pkt = msgBuilder.createMsgXMLObj(msg);
            oos.writeObject(pkt);   

        }catch(IOException e){
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    //metodo
    public NodeList LoadContacts(String usr){
        
        try {

            //manda il pacchetto con la richiesta degli utenti al server
            Document pkt = msgBuilder.createLoadContactRequestXMLObj(usr);
            oos.writeObject(pkt);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
}