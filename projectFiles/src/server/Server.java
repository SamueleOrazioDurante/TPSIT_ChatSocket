package server;

import java.io.*;

import java.net.*;
import java.util.concurrent.Semaphore;

public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try{

			// Welcome socket creation
			ServerSocket server = new ServerSocket ( 8000 );
			System.out.println ("[Server]: in attesa su porta 8000." );
			// Binary Semaphore creation
			Semaphore SemBin = new Semaphore(1);
			while(true)
			{
				
				//listening to accept a new client connection
				Socket clientsock = server.accept();
				System.out.println ("[Server]: nuovo client." );

				//new thread creation passing Connection Socket and Binary Semaphore as input
				ServerSkeleton sSkeleton = new ServerSkeleton();
				ServerThread sThread = new ServerThread(clientsock,SemBin,sSkeleton);
				sThread.start();
			}

			
		}catch ( IOException e ){
			e.printStackTrace();
		}
	}
}
