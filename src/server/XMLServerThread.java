package server;

import java.io.*;
import java.net.*;
import java.util.concurrent.Semaphore;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;

import org.w3c.dom.*;

public class XMLServerThread extends Thread {
	
	private Socket client;
	private Semaphore Sem;
	private ObjectInputStream inBuffer;
	private DataOutputStream outBuffer; 
	private DocumentBuilder builder;
	private File xmlFile;
	private Document db;

	protected static final String XML_FILE_NAME = "JavaSocket/XMLObjectTransfer/server/dbChat.xml";

	
	public XMLServerThread ( Socket skt, Semaphore SemBin ){
		client=skt;
		Sem=SemBin;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            
            //DOM Builder(Parser) creation 
            builder = factory.newDocumentBuilder();

            //File Object Creation
            xmlFile = new File(XML_FILE_NAME);
            
			//Critical Section: A thread at the time can read the xml file because of it is a shared resource
			Sem.acquire();
            db = builder.parse(xmlFile);
			Sem.release();
			//remove white spaces from xml document (it is needed to indent correctly during the writing phase)
			removeWhitespaces(db.getDocumentElement());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void run (){
		
		try{
			
			//Input and Output stream creation 
			
			outBuffer = new DataOutputStream ( client.getOutputStream());	
			inBuffer = new ObjectInputStream(client.getInputStream());
			Boolean ended = false;
			while(!ended)
			{
				// waiting for client xml message
				System.out.println ("	[Server-Worker]: attesa messaggio dal client..." );
				Document msgDoc = (Document)inBuffer.readObject();
				//get the message content
				String Message = msgDoc.getElementsByTagName("Content").item(0).getTextContent();
				System.out.println ("	[Server-Worker]: Messaggio ricevuto < " + Message + ">. Invio risposta." );
				
				//Update XML DB with the new message
				UpdateXMLDB(msgDoc);
				// send response
				outBuffer.writeUTF(Message);
				if(Message.equals("fine"))
				{
					ended = true;
				}
			}
			
			//Write the updated DB
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer;
			transformer = tFactory.newTransformer();
						DOMSource source = new DOMSource(db);
						StreamResult result = new StreamResult(xmlFile);
			//It is neede to indent the xml file
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DocumentType doctype = db.getDoctype();
			//it is needed to include the dtd declaration in xml file
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
			
			//Critical section to write in xml file
			Sem.acquire();
			transformer.transform(source, result);
			Sem.release();
			
			//buffer and socket closing
			inBuffer.close();
			outBuffer.close();
			client.close();
	

			
		}catch (Exception e ){
			e.printStackTrace();
		}
	}

	public void UpdateXMLDB(Document m)
	{
		      

        try {
            //get sender and receiver by xml message object
			String sender = m.getElementsByTagName("Sender").item(0).getTextContent();
			String receiver = m.getElementsByTagName("Receiver").item(0).getTextContent();
			//search the chat element with id sender_receiver
			Element chat = db.getElementById(sender+"_"+receiver);
			//import chat message node from xml message object to xml db object and get it in newMessage
			Node newMessage = db.importNode(m.getDocumentElement() /*m.getElementsByTagName("ChatMessage").item(0)*/, isAlive());
			
			if(chat != null)
			{
				//if id sender_receiver not found try to found receiver_sender
				chat.appendChild(newMessage);
			}
			else
			{
				
				chat = db.getElementById(receiver+"_"+sender);
				if(chat != null)
				{
					chat.appendChild(newMessage);
				}
				else
				{
					//if id not found add a new chat with id sender_receiver
					Element chatNew = db.createElement("chat");
					chatNew.setAttribute("id", sender+"_"+receiver);
					chatNew.appendChild(newMessage);
					Element chatList = (Element)db.getElementsByTagName("ChatList").item(0);
					chatList.appendChild(chatNew);
				}
			}

            
            

        } catch (Throwable t) {
            t.printStackTrace ();
        }
        
    }

	public static void removeWhitespaces(Element element) {
		NodeList children = element.getChildNodes();
		for (int i = children.getLength() - 1; i >= 0; i--) {
			Node child = children.item(i);
			if (child instanceof Text
				&& ((Text) child).getData().trim().isEmpty()) {
				element.removeChild(child);
			} else if (child instanceof Element) {
				removeWhitespaces((Element) child);
			}
		}
	}

}
