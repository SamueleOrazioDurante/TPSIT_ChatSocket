/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

/**
 *
 * @author lucad
 */
public class Message {
    private String msg;
    private String sender;
    
    public Message(String mess, String send)
    {
        msg = mess;
        sender = send;
    }
    
    public Message()
    {
    }
    
    public void setMsg(String mess)
    {
        msg = mess;
    }
    
    public void setSender(String send)
    {
        sender = send;
    }
    
    public String getMsg()
    {
        return msg;
    }
    
    public String getSender()
    {
        return sender;
    }
}
