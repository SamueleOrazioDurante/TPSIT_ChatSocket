package interfaces;

import java.util.ArrayList;
import client.Message;

//identifica la logica dal lato del client implementato dal loggedIndex
public interface ClientBLInterface{
    //CAMBIARE IL NOME, CAPIRE IL FUNZIONAMENTO

    int LoadingChat(ArrayList<Message> messages);
    int GetSendMessage(Message m);
    int getSentMsgAck();

    int getLoadingContacts(ArrayList<String> contacts);
}
