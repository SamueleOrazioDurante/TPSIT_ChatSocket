package interfaces;

import java.util.ArrayList;
import client.Message;

public interface ClientBLInterface {
    //CAMBIARE IL NOME, CAPIRE IL FUNZIONAMENTO

    int LoadingChat(ArrayList<Message> messages);
    int GetSendMessage(Message m);
    int getSentMsgAck();

    int getLoadingContacts(ArrayList<String> contacts);
}
