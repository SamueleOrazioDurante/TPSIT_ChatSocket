/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author matt3
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login2
     */
    public Login() {
        initComponents();
        centrareJFrame();
        setIconaImage();
        titleBar.init(this);
        enable_eye.setVisible(false);
        enable_eye.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootElement = new javax.swing.JPanel();
        loginForm = new javax.swing.JPanel();
        usrTxt = new javax.swing.JLabel();
        loginBtn = new client.style.LoginButton();
        pswTxt = new javax.swing.JLabel();
        loginButton3 = new client.style.LoginButton();
        notRegTxt = new javax.swing.JLabel();
        usrField = new javax.swing.JTextField();
        pswField = new javax.swing.JPasswordField();
        humanIcon = new javax.swing.JLabel();
        enable_eye = new javax.swing.JLabel();
        disabile_eye = new javax.swing.JLabel();
        imageLogo = new javax.swing.JPanel();
        imgLogo = new javax.swing.JLabel();
        titleBar = new client.style.TitleBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);
        setUndecorated(true);

        rootElement.setBackground(new java.awt.Color(18, 18, 18));
        rootElement.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N

        loginForm.setBackground(new java.awt.Color(18, 18, 18));

        usrTxt.setForeground(new java.awt.Color(227, 227, 227));
        usrTxt.setText("Username");

        loginBtn.setText("LOGIN");
        loginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginBtnMouseClicked(evt);
            }
        });

        pswTxt.setForeground(new java.awt.Color(227, 227, 227));
        pswTxt.setText("Password");

        loginButton3.setText("REGISTRATI");

        notRegTxt.setForeground(new java.awt.Color(227, 227, 227));
        notRegTxt.setText("Non sei ancora registrato?");

        usrField.setBackground(new java.awt.Color(18, 18, 18));
        usrField.setForeground(new java.awt.Color(225, 225, 225));
        usrField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(187, 134, 252)));

        pswField.setBackground(new java.awt.Color(18, 18, 18));
        pswField.setForeground(new java.awt.Color(225, 225, 225));
        pswField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(187, 134, 252)));
        pswField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pswFieldActionPerformed(evt);
            }
        });

        humanIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/icon/login/human.png"))); // NOI18N

        enable_eye.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enable_eye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/icon/login/open_eye.png"))); // NOI18N
        enable_eye.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        enable_eye.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enable_eyeMouseClicked(evt);
            }
        });

        disabile_eye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/icon/login/closed_eye.png"))); // NOI18N
        disabile_eye.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        disabile_eye.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                disabile_eyeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout loginFormLayout = new javax.swing.GroupLayout(loginForm);
        loginForm.setLayout(loginFormLayout);
        loginFormLayout.setHorizontalGroup(
            loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginFormLayout.createSequentialGroup()
                        .addComponent(usrField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(humanIcon))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginFormLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loginButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(enable_eye)
                                .addComponent(disabile_eye))))
                    .addGroup(loginFormLayout.createSequentialGroup()
                        .addGroup(loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(notRegTxt)
                            .addComponent(usrTxt)
                            .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(loginFormLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pswTxt)
                        .addComponent(pswField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(49, Short.MAX_VALUE)))
        );
        loginFormLayout.setVerticalGroup(
            loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginFormLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(usrTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(usrField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(humanIcon))
                .addGap(49, 49, 49)
                .addGroup(loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enable_eye)
                    .addComponent(disabile_eye))
                .addGap(18, 18, 18)
                .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(notRegTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loginButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(loginFormLayout.createSequentialGroup()
                    .addGap(85, 85, 85)
                    .addComponent(pswTxt)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(pswField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(167, Short.MAX_VALUE)))
        );

        imageLogo.setBackground(new java.awt.Color(18, 18, 18));

        imgLogo.setBackground(new java.awt.Color(18, 18, 18));
        imgLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/logo/logo-nobg.png"))); // NOI18N

        javax.swing.GroupLayout imageLogoLayout = new javax.swing.GroupLayout(imageLogo);
        imageLogo.setLayout(imageLogoLayout);
        imageLogoLayout.setHorizontalGroup(
            imageLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        imageLogoLayout.setVerticalGroup(
            imageLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        titleBar.setBackground(new java.awt.Color(18, 18, 18));

        javax.swing.GroupLayout rootElementLayout = new javax.swing.GroupLayout(rootElement);
        rootElement.setLayout(rootElementLayout);
        rootElementLayout.setHorizontalGroup(
            rootElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootElementLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
            .addComponent(titleBar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        rootElementLayout.setVerticalGroup(
            rootElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootElementLayout.createSequentialGroup()
                .addComponent(titleBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(rootElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rootElementLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(imageLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rootElementLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loginForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(rootElement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootElement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pswFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pswFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pswFieldActionPerformed

    private void enable_eyeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enable_eyeMouseClicked
        pswField.setEchoChar((char)8226);
        disabile_eye.setVisible(true);
        disabile_eye.setEnabled(true);
        enable_eye.setVisible(false);
        enable_eye.setEnabled(false);

    }//GEN-LAST:event_enable_eyeMouseClicked

    private void disabile_eyeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disabile_eyeMouseClicked
        pswField.setEchoChar((char)0);
        
        
        disabile_eye.setVisible(false);
        disabile_eye.setEnabled(false);
        enable_eye.setVisible(true);
        enable_eye.setEnabled(true);
    }//GEN-LAST:event_disabile_eyeMouseClicked

    private void loginBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_loginBtnMouseClicked

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel disabile_eye;
    private javax.swing.JLabel enable_eye;
    private javax.swing.JLabel humanIcon;
    private javax.swing.JPanel imageLogo;
    private javax.swing.JLabel imgLogo;
    private client.style.LoginButton loginBtn;
    private client.style.LoginButton loginButton3;
    private javax.swing.JPanel loginForm;
    private javax.swing.JLabel notRegTxt;
    private javax.swing.JPasswordField pswField;
    private javax.swing.JLabel pswTxt;
    private javax.swing.JPanel rootElement;
    private client.style.TitleBar titleBar;
    private javax.swing.JTextField usrField;
    private javax.swing.JLabel usrTxt;
    // End of variables declaration//GEN-END:variables
}
