/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.sql.ConnectionPoolDataSource;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import client.style.ChatMessageReceiver;
import client.style.ChatMessageSender;
import client.style.ItemPeople;
import client.style.ScrollPaneWin11;

/**
 *
 * @author matt3
 */
public class LoggedIndex extends javax.swing.JFrame {

    //variabili della classe LoggedIndex
    String localuser = "UserNotFound";  //utilizzata nel caso il programma venga aperto in modo non corretto 
    String remoteuser = "Sam";

    ChatClientProxy proxy;
    
    //arraylist -> database locale
    ArrayList<String> Contacts; //arraylist di contatti
    ArrayList<ArrayList<Message>> MsgsContacts; // arraylist di arraylist di messaggi (uno per utente)
    ArrayList<ItemPeople> ContactsBtn; //arraylist dei vari bottoni che permette di accedere alle varie chat

    //altre variabili per la gestione della stampa a video di contatti e messaggi
    private int fixedMessageYOffset = 25;
    private int YLastMessage = 0;
    private final int MaxMessageWidth = 170;
    private final int RemoteSenderStartOffsetMessage = 200;
    private final int LocalSenderStartOffsetMessage = 0;
    private int SingleLineMessageHeight = 18;
    private final int ContactHeight = 50;
    private final int ContactWidth = 170;
    
    
    /**
     * Creates new form Login2
     */
    public LoggedIndex(String usr,ChatClientProxy prx) {
        initComponents();

        //componenti grafiche
        centrareJFrame();
        setIconaImage();
        titleBar1.init(this);

        initVar(usr,prx);    

    }

    private void initVar(String usr,ChatClientProxy prx){
        this.localuser=usr;
        ciao_user.setText("Ciao "+localuser);

        this.proxy = prx;

        Contacts = new ArrayList<String>();
        MsgsContacts = new ArrayList<ArrayList<Message>>();
        ContactsBtn = new ArrayList<ItemPeople>();

        proxy.LoadContacts(localuser);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        titleBar1 = new client.style.TitleBar();
        panel_user = new javax.swing.JPanel();
        ketu_logo = new javax.swing.JLabel();
        icon_user = new javax.swing.JLabel();
        ciao_user = new javax.swing.JLabel();
        messaggio_field = new javax.swing.JTextField();
        invia_icon = new javax.swing.JLabel();
        scrollPane = new client.style.ScrollPaneWin11();
        userList = new javax.swing.JPanel();
        allChat = new javax.swing.JPanel();
        scrollPaneWin111 = new client.style.ScrollPaneWin11();
        chat = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);
        setUndecorated(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(18, 18, 18));
        jPanel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N

        titleBar1.setBackground(new java.awt.Color(18, 18, 18));

        panel_user.setBackground(new java.awt.Color(43, 43, 43));

        ketu_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/logo/logo-nobg-insidebg-128x128.png"))); // NOI18N

        icon_user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/icon/login/human.png"))); // NOI18N

        ciao_user.setForeground(new java.awt.Color(241, 241, 241));
        ciao_user.setText("Ciao UserNotFound");

        javax.swing.GroupLayout panel_userLayout = new javax.swing.GroupLayout(panel_user);
        panel_user.setLayout(panel_userLayout);
        panel_userLayout.setHorizontalGroup(
            panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_userLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_userLayout.createSequentialGroup()
                        .addComponent(icon_user)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ciao_user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(ketu_logo))
                .addContainerGap())
        );
        panel_userLayout.setVerticalGroup(
            panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_userLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(ketu_logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(icon_user)
                    .addComponent(ciao_user))
                .addGap(17, 17, 17))
        );

        messaggio_field.setBackground(new java.awt.Color(18, 18, 18));
        messaggio_field.setForeground(new java.awt.Color(225, 225, 225));
        messaggio_field.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(187, 134, 252)));

        invia_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        invia_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/loggedindex/telegram-xxl-32x32.png"))); // NOI18N
        invia_icon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        invia_icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                invia_iconMouseClicked(evt);
            }
        });

        userList.setBackground(new java.awt.Color(43, 43, 43));

        javax.swing.GroupLayout userListLayout = new javax.swing.GroupLayout(userList);
        userList.setLayout(userListLayout);
        userListLayout.setHorizontalGroup(
            userListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 311, Short.MAX_VALUE)
        );
        userListLayout.setVerticalGroup(
            userListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );

        scrollPane.setViewportView(userList);

        allChat.setBackground(new java.awt.Color(43, 43, 43));

        javax.swing.GroupLayout chatLayout = new javax.swing.GroupLayout(chat);
        chat.setLayout(chatLayout);
        chatLayout.setHorizontalGroup(
            chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );
        chatLayout.setVerticalGroup(
            chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 855, Short.MAX_VALUE)
        );

        scrollPaneWin111.setViewportView(chat);

        javax.swing.GroupLayout allChatLayout = new javax.swing.GroupLayout(allChat);
        allChat.setLayout(allChatLayout);
        allChatLayout.setHorizontalGroup(
            allChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
        );
        allChatLayout.setVerticalGroup(
            allChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(messaggio_field)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(invia_icon)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(allChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(23, Short.MAX_VALUE))))
            .addComponent(titleBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(titleBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panel_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(allChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(messaggio_field, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(invia_icon, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void invia_iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_invia_iconMouseClicked
        sendMsg();
    }//GEN-LAST:event_invia_iconMouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            sendMsg();
        }
    }//GEN-LAST:event_formKeyPressed

    public void sendMsg(){
        String msg = messaggio_field.getText();
            if(msg.length()!=0){
            messaggio_field.setText("");
            Message mMsg = new Message(msg,remoteuser,localuser);

            proxy.SendMsgToServer(mMsg);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoggedIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoggedIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoggedIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoggedIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoggedIndex("UserNotFound",null).setVisible(true);

            }
        });

    }

    public void setIconaImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(("src/png/logo/logo-nobg-insidebg-64x64.png")));
        setTitle("Ketu");
    }

    

    // Metodo per centrare il JFrame
    public void centrareJFrame() {
        // Ottieni le dimensioni dello schermo
        Dimension dimensioneSchermo = Toolkit.getDefaultToolkit().getScreenSize();

        // Calcola le coordinate per centrare il JFrame
        int x = (dimensioneSchermo.width - getWidth()) / 2;
        int y = (dimensioneSchermo.height - getHeight()) / 2;

        // Imposta la posizione del JFrame
        setLocation(x, y);
    }

    //metodo per ottenere la lista di tutti i contatti dal thread
    public void getLoadContacts(ArrayList<String> c){
        Contacts = c;
        //cerco e rimuovo l'utente locale
        for(int i=0;i<Contacts.size();i++){
            if(Contacts.get(i).equals(localuser)){
                Contacts.remove(i);
                break;
            }
        }
        this.startLoadChat();
    }

    //metodo per richiedere tutte le chat e salvarle in locale
    public void startLoadChat(){
        for(int i=0;i<Contacts.size();i++){
            proxy.LoadChat(localuser,Contacts.get(i));
            this.addContact(Contacts.get(i),i);
        }
    }

    //metodo che aggiunge un ItemPeople e un Panel (che contiene la chat) per ogni contatto (viene inizializzato nascosto)
    public void addContact(String contact,int index){
        //creo un nuovo item contenitore del contatto
        ItemPeople jCont = new ItemPeople(contact);
        ContactsBtn.add(jCont);
        //imposto le sue proprietà
        ContactsBtn.get(index).setSize(290,80);
        ContactsBtn.get(index).setLocation(10, 10+(90*index));
        ContactsBtn.get(index).setName(""+index);
        ContactsBtn.get(index).setVisible(true);
        
        //aggiungo il panel all'array di contattibtn
        ContactsBtn.get(index).addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jContactMouseListener(evt);
            }
        });
        
        //aggiungo il panel all'interfaccia grafica e lo imposto come visibile
        userList.add(ContactsBtn.get(index));
        userList.getComponent(index).setVisible(true);

        userList.repaint();
        userList.revalidate();
        
        scrollPane.repaint();
        scrollPane.revalidate();
    }
    
    private void jContactMouseListener(java.awt.event.MouseEvent evt) {    
        messaggio_field.setEditable(true);
        YLastMessage = 0;  // reset the Last Message y coordinate
        //remove all message in Jpanel Chat screen and refresh it
        allChat.removeAll(); 
        allChat.revalidate();
        allChat.repaint();
        ////set Layout null to the JPanel contact in order to manage messages position using x,y coordinates
        allChat.setLayout(null);
        allChat.setBackground(Color.LIGHT_GRAY);
        allChat.setAutoscrolls(true);

        ItemPeople name = (ItemPeople)evt.getSource();
        //per capire chi ha azionato l'evento
        int JLIdx = Integer.parseInt(name.getName());
        remoteuser = Contacts.get(JLIdx);
        System.out.println(remoteuser);
        //crea tutti gli elementi messaggio e li aggiunge alle chat
        for(int i = 0; i<MsgsContacts.get(JLIdx).size();i++)
        {
            AddChatText(MsgsContacts.get(JLIdx).get(i).getMsg(), MsgsContacts.get(JLIdx).get(i).getSender());
        }
    }           

    private void AddChatText(String text, String sender)
    {

        ChatMessageReceiver MessTextAreaR;
        ChatMessageSender MessTextAreaS;

        if(sender.equals(localuser)){
            MessTextAreaR = new ChatMessageReceiver(text);
            //MessTextAreaR.setBounds(RemoteSenderStartOffsetMessage, YLastMessage, MaxMessageWidth, (SingleLineMessageHeight*((MessTextAreaR.getPreferredSize().width/MaxMessageWidth)+1)));
            
            MessTextAreaR.setSize(400,100);
            MessTextAreaR.setLocation(10, 10);
            chat.add(MessTextAreaR);
            MessTextAreaR.setVisible(true);
        }  
        else{
            
            MessTextAreaS = new ChatMessageSender(text);
            MessTextAreaS.setSize(400,100);
            MessTextAreaS.setLocation(10, 10);
            //MessTextAreaS.setBounds(LocalSenderStartOffsetMessage, YLastMessage, MaxMessageWidth, (SingleLineMessageHeight*((MessTextAreaS.getPreferredSize().width/MaxMessageWidth)+1)));
            chat.add(MessTextAreaS);
            MessTextAreaS.setVisible(true);
        }
            
        //update the last y coordinate of the message
        //update the y size of the scroll bar
        chat.setPreferredSize(new Dimension(319, YLastMessage+SingleLineMessageHeight));
        chat.revalidate();
        chat.repaint();
        //clear the input text area
        messaggio_field.selectAll();
        messaggio_field.replaceSelection("");
        chat.setVisible(true);
    }

    //metodo per ottenere la lista dei messaggi di un determinato contatto dal thread
    public void getLoadChat(ArrayList<Message> c){
        MsgsContacts.add(c);
    }

    //metodo per aggiungere un nuovo messaggio al db di messaggi locale
    public void getSendMsg(Message msg){
        //ottieni chi manda il messaggio, ricerca a che posizione dell'array si trova la chat e aggiungi un elemento alla chat
        System.out.println(msg.getMsg() + " " + msg.getSender());
        int index = this.getIndexList(msg.getSender());
        MsgsContacts.get(index).add(msg);   

        //PARTE CHE AGGIUNGE A GUI CHE NON SO COME FARE AIUTAMI TI PREGO DIO SANTO
    }

    //metodo per mandare l'ack
    public void getSentMsgAck(){
        // ?TBD
    }

    public int getIndexList(String usr){
        //ottiene l'index del contatto presente nell'arraylist. Se non esiste torna -1
        int index = -1;
        for(int i=0;i<Contacts.size();i++){
            if(Contacts.get(i).equals(usr))
                index = i;
        }
        return index;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel allChat;
    private javax.swing.JPanel chat;
    private javax.swing.JLabel ciao_user;
    private javax.swing.JLabel icon_user;
    private javax.swing.JLabel invia_icon;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel ketu_logo;
    private javax.swing.JTextField messaggio_field;
    private javax.swing.JPanel panel_user;
    private client.style.ScrollPaneWin11 scrollPane;
    private client.style.ScrollPaneWin11 scrollPaneWin111;
    private client.style.TitleBar titleBar1;
    private javax.swing.JPanel userList;
    // End of variables declaration//GEN-END:variables
}
