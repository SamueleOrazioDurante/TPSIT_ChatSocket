package server;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.concurrent.Semaphore;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;

import org.w3c.dom.*;

public class ServerThread extends Thread {
	
	private Socket client;
	private ServerSkeleton sk;
	HashMap<String,Socket> map;
	Semaphore map_sem;

	private ObjectInputStream inBuffer;
	private DataOutputStream outBuffer; 
	private DocumentBuilder builder;
	private File xmlFile;
	private Document db;

	protected static final String XML_FILE_NAME = "src\\server\\dbChat.xml";

	
	public ServerThread ( Socket C_skt, ServerSkeleton sk, HashMap<String,Socket> addrMap, Semaphore MapSem ){
		client=C_skt;
		this.sk = sk;
		map = addrMap;
		map_sem = MapSem;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();

            xmlFile = new File(XML_FILE_NAME);

			//FORSE SEMAFORO
            db = builder.parse(xmlFile);
			//FORSE SEMAFORO

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
