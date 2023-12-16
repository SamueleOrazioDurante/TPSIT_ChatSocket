package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.w3c.dom.Document;

import interfaces.ClientBLInterface;

public class ChatClientThread extends Thread{
    private Socket skt;
    private ClientBLInterface clientInterface;
    private boolean destroy;

    //richiesta socket e interfaccia del client
    public ChatClientThread(Socket skt,ClientBLInterface clientInterface){
        this.skt = skt;
        this.clientInterface = clientInterface;
        this.destroy = false;
    }

    //distruzione del thread
    public void destroyClientThread(){
        destroy = true;
    }

    public void run(){
        try{
            //apertura canali di comunicazione in/out
            ObjectOutputStream oos = new ObjectOutputStream(skt.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(skt.getInputStream());
            
            //finchè non viene distrutto dal client
            while(!destroy){
                

                Document pkt;
                pkt=(Document) ois.readObject();

            }
        }
    }
}
