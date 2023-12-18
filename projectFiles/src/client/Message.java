/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

/**
 *
 * @author SamOraDur
 */
public class Message {
    private String msg;
    private String sender;
    private String receiver;
    
    public Message(String mess, String send)
    {
        msg = mess;
        sender = send;
    }
    public Message(String mess, String send,String receive)
    {
        msg = mess;
        sender = send;
        receiver = receive;
    }

    public Message()
    {
    }
    
    public void setReceiver(String receive)
    {
        receiver = receive;
    }
    
    public String getReceiver()
    {
        return receiver;
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
