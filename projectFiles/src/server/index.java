package server;

public class index {
    public static void main(String[] args) {
		//inizializazione di istanze di database, server reale e server proxy
		DBManager dbManager = new DBManager();
		Server serverReale = new Server(dbManager);
		indexGUITEST GUI = new indexGUITEST();
		ServerSkeleton serverSkeleton = new ServerSkeleton(serverReale,GUI);
		
		//start del server proxy
		serverSkeleton.skeleton();
	}
}
