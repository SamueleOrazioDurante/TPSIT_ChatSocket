package server;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.concurrent.Semaphore;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.transform.OutputKeys;

import client.XMLMessageBuilder;
import interfaces.XMLMsgBuilder;

public class ServerThread extends Thread {
	
	private Socket clientSocket;
	private ServerSkeleton sk;
	private HashMap<String,ObjectOutputStream> map;
	private Semaphore map_sem;

	private final int SendMessage = 1;

	private ObjectInputStream inBuffer;
	private ObjectOutputStream outBuffer; 
	private XMLMsgBuilder msgBuilder;

	
	public ServerThread (Socket clientSocket, ServerSkeleton sk, HashMap<String,ObjectOutputStream> addrMap, Semaphore MapSem ){
		this.clientSocket=clientSocket;
		this.sk = sk;
		map = addrMap;
		map_sem = MapSem;
		msgBuilder = new XMLMsgBuilder();
	}
	
	public void run (){
		try{
			Boolean end = false;

			//istanza dei buffer di input e output per la comunicazione client/server
				outBuffer = new ObjectOutputStream (clientSocket.getOutputStream());	
				inBuffer = new ObjectInputStream(clientSocket.getInputStream());
			
			while(!end)
			{
				
				//in attesa di un messaggio dal client (con stampa su server GUI)
				sk.AddMsgTerminal("	[Server-Th]: attesa messaggio dal client..." );
				Document msgPkt = (Document)inBuffer.readObject();

				//switch case per ricerca tipologia di operazione
				String op = msgPkt.getElementsByTagName("Operation").item(0).getTextContent();
				switch (op) {
					case "login":{
						String usr = msgPkt.getElementsByTagName("nickname").item(0).getTextContent(); //da cambiare
						String psw = msgPkt.getElementsByTagName("password").item(0).getTextContent(); //da cambiare
						int result = sk.login(usr, psw);

						outBuffer.writeObject(result);
						
						//se il login ha successo allora viene registrato l'utente nell'hash map
						if (result==2) {
							map_sem.acquire();
							map.put(usr,outBuffer);
							map_sem.release();
						//altrimenti viene chiusa la connessione con il client
						}else{
							clientSocket.close();
							inBuffer.close();
							outBuffer.close();
							end=true;
						}
						break;
					}
					case "loadChat":{
						String reQUser = msgPkt.getElementsByTagName("localUser").item(0).getTextContent();
						String toSearchUser = msgPkt.getElementsByTagName("remoteUser").item(0).getTextContent();
						NodeList msgs = sk.LoadChat(reQUser, toSearchUser);
						Document chat = msgBuilder.createChatXMLObj(msgs); //TO BE FOUND
						outBuffer.writeObject(chat);
						break;
					}	
					case "sendMessage":{
						Element msg = (Element) msgPkt.getElementsByTagName("Chat").item(0); //CHAT È DA CAMBIARE
						String receive = sk.SendMsg(msg);
						
						map_sem.acquire();
						ObjectOutputStream oos = map.get(receive);
						map_sem.release();

						//invio messaggio al client receiver
						Document msgPktOut = msgBuilder.createMsgXMLObj(msg); //TO BE FOUND
						oos.writeObject(msgPktOut);
						
						//pacchetto per informare il mittente che il messaggio è arrivato al destinatario
						Document ackPacket = msgBuilder.createAckXMLObj();
						outBuffer.writeObject(ackPacket);

						break;
					}	
					case "ContactRequest":{
						
						//invio dei contatti al client che li richiede 	
						String usr = msgPkt.getElementsByTagName("user").item(0).getTextContent();
						NodeList contacts = sk.LoadContacts(usr);
						Document contactsPkt = msgBuilder.createContactsXMLObj(contacts);
						outBuffer.writeObject(contactsPkt);

						break;
					}	
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}