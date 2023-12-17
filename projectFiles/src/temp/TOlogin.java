/* 

NELLE VARIABILI DELLA ClassNotFoundException

    ChatClientProxy proxy;


IN BUTTON CLICKED FUNCTION

    // creazione nuovo proxy
    proxy = new ChatClientProxy();
    //utilizzo funzione login
    int logResult = proxy.login(JTextField.getText(),JTextField2.getText());

    //se il login è eseguito con successo, passo username
    if(logResult == 2){

        LoggedIndex li = new LoggedIndex(JTextField.getText);
        //metodo per istanziare un nuovo thread a cui passo un istanza del logged index
        proxy.newClientThread(li);
        //mostro la pagina della chat vera e propria
        li.setVisible(true);
        //nascondo la pagina di login
        Login.this.setVisible(false);

    }else{

        JOptionPane.showMessageDialog(null,"User o password errati");

    }
    */