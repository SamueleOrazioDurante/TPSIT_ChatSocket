package client;

import java.net.*;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.*;
import client.XMLMessageBuilder;
public class XMLClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try{
			Socket s = new Socket ("127.0.0.1", 6969 );
			//Creating Input and output buffer for the comunication with server side application
			//(Output stream buffer is ObjectOutputStream type in order to send an xml object) 
			DataInputStream fromServer = new DataInputStream ( s.getInputStream() );
			ObjectOutputStream  toServer = new ObjectOutputStream(s.getOutputStream());

			System.out.println ("[Client]: socket creata." );
			Boolean ended = false;

			//An input message from keyboard is required by user until the string "fine" is written
			while(!ended)
			{
				//get a string from keyboard
				BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("scrivi un messaggio da inviare: ");
				String SendingString = input.readLine();

				//Build an XML Message to send
				XMLMessageBuilder mb = new XMLMessageBuilder(SendingString, "LDORATO", "SDORATO");
				Document documento = mb.getXMLObject();
				
				
				/*
				* creazione degli stream per la comunicazione con la socket lato 
				* server
				*/
				
								
				
				// Send an XML Message
				toServer.writeObject(documento);
				
				// wait for server response
				System.out.println ("[Client]: attesa risposta..." );
				String resp = fromServer.readUTF();
				System.out.println ("[Client]: risposta server: " + resp );

				//check if stop comunication
				if(resp.equals("fine"))
				{
					ended = true;
				}
				
				
			}
			// stream e socket closing
			toServer.close();
			fromServer.close();
			s.close();
		
		}catch ( IOException e ){
			e.printStackTrace();
		}
		

	}

}
