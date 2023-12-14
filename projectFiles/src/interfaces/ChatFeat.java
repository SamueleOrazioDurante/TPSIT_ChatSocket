package interfaces;



import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

//interfaccia utilizzata per definire uno standard ai metodi implementati dal server reale e dal proxy skeleton
public interface ChatFeat {
    public int Login(String usr, String psw);
    public NodeList LoadChat(String usr_a,String usr_b);
    public String SendMsg(Element msg);

    public NodeList LoadContacts(String usr);
}
