package interfaces;

public class XMLMsg {
    private String Message;
    private String Sender;
    private String Receiver;

    public XMLMsg(String m, String s, String r)
    {
        Message = m;
        Sender = s;
        Receiver = r;
    }

    public void setXMLMessage(String m, String s, String r)
    {
        Message = m;
        Sender = s;
        Receiver = r;
    }

    public String getMessage()
    {
        return Message;
    }

    public String getSender()
    {
        return Sender;
    }

    public String getReceiver()
    {
        return Receiver;
    }

    public void setMessage(String m)
    {
        Message = m;
    }

    public void setSender(String s)
    {
        Sender = s;
    }

    public void setReceiver(String r)
    {
        Receiver = r;
    }
}
