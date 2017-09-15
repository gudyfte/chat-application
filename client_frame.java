import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.awt.Color;

import java.awt.*;
import javax.swing.*;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class client_frame extends javax.swing.JFrame {
   String username,address="localhost";
   String attachment_path;
   String password;
   ArrayList<String> users=new ArrayList();
   int port=2222;
   Boolean isConnected=false;
 
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    BufferedReader ContentRead;
   private Desktop desktop;
            
    public void ListenThread(){
     Thread IncomingReader=new Thread(new IncomingReader());
     IncomingReader.start();
    }
    
    public void Disconnect(){
        try{
            //ta_chat.append("\n" + username + " Disconnected.\n");
            sock.close();
        }catch(Exception ex){
            ta_chat.append("Failed to disconnect. \n");
        }
        isConnected=false;
        tf_username.setEditable(true);
    }    
    /**
     * Creates new form client_frame
     */
    public client_frame() {
        initComponents();
       
        getContentPane().setBackground(Color.pink);
       
        b_connect.setBackground(Color.blue);
        b_connect.setForeground(Color.white);
        
        b_disconnect.setBackground(Color.blue);
        b_disconnect.setForeground(Color.white);
        
        b_send.setBackground(Color.blue);
        b_send.setForeground(Color.white);
        
        lb_users.setForeground(Color.blue);
        lb_username.setForeground(Color.blue);
        
        b_upload.setBackground(Color.blue);
        b_upload.setForeground(Color.white);
        
        b_open.setBackground(Color.blue);
        b_open.setForeground(Color.white);
        
    }

    public class IncomingReader implements Runnable{
        @Override
        public void run(){
           
            String[] data;
            String stream,done="Done",connect="-------------------------------------",disconnect="----------------------",chat=" ";
            String str;
            try{
                while((stream=reader.readLine())!=null)
                {
                    data=stream.split("`");
                      
                         if(data[1].equals("has comed.")){
                             ta_users.append(data[0] + "\n");
                         }else if(data[1].equals("is here.")){
                             ta_users.setText("");
                             ta_chat.setText("");
                         }else if(data[1].equals("here")){
                             ta_users.append(data[0] + "\n");
                         }
                         else if(data[1].equals("has leaved.")){
                             ta_users.setText("");
                             ta_chat.setText("");
                         }
                         else if(data[3].equals(" ")){
                             ta_chat.append("\n\tTime: " + data[1] + "\n");
            
                             ta_chat.append(data[0] + ":\n\n" + data[2]); 
                         }else if(data[3].equals("~")){
                            if(data[2].toLowerCase().endsWith(".txt") || data[2].toLowerCase().endsWith(".java") || data[2].toLowerCase().endsWith(".cpp") || data[2].toLowerCase().endsWith(".s")){
                             ContentRead = new BufferedReader(new FileReader(data[2]) );
                              data[2]="";
                              while((str = ContentRead.readLine()) !=  null) 
                              {
                                data[2]+="\n" + str;  
                              }
                              ta_chat.append("\n\tTime: " + data[1] + "\n");
            
                              ta_chat.append(data[0] + ":\n\n" + data[2]);
                            }
                            else if(data[2].toLowerCase().endsWith(".docx")){
                              XWPFDocument doc=new XWPFDocument(new FileInputStream(data[2]));
                              XWPFWordExtractor extract=new XWPFWordExtractor(doc);
                              ta_chat.append("\n\tTime: " + data[1] + "\n");
                              ta_chat.append(data[0] + ":\n\n");
                              ta_chat.append(extract.getText());
                            }
                         }
                }
            }catch(Exception ex){}
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        lb_username = new javax.swing.JLabel();
        tf_username = new javax.swing.JTextField();
        b_connect = new javax.swing.JButton();
        b_disconnect = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        tf_chat = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        ta_users = new javax.swing.JTextArea();
        b_upload = new javax.swing.JButton();
        lb_users = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        b_send = new javax.swing.JButton();
        b_open = new javax.swing.JButton();
        tf_download = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        jMenuItem1.setText("copy");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("paste");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        jMenuItem3.setText("cut");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem3);

        jMenuItem4.setText("selectAll");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem4);

        jMenuItem6.setText("copy");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItem6);

        jMenuItem5.setText("selectAll");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItem5);

        jMenuItem7.setText("copy");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jPopupMenu3.add(jMenuItem7);

        jMenuItem8.setText("selectAll");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jPopupMenu3.add(jMenuItem8);

        jMenuItem9.setText("copy");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jPopupMenu4.add(jMenuItem9);

        jMenuItem10.setText("selectAll");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jPopupMenu4.add(jMenuItem10);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Chat's Client");

        lb_username.setText("Enter your name");
        lb_username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_usernameMouseReleased(evt);
            }
        });

        tf_username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tf_usernameMouseReleased(evt);
            }
        });
        tf_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_usernameActionPerformed(evt);
            }
        });

        b_connect.setText("Connect");
        b_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_connectActionPerformed(evt);
            }
        });

        b_disconnect.setText("Disconnect");
        b_disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_disconnectActionPerformed(evt);
            }
        });

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        ta_chat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ta_chatMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ta_chatMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(ta_chat);

        tf_chat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tf_chatMouseReleased(evt);
            }
        });
        tf_chat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_chatActionPerformed(evt);
            }
        });

        ta_users.setColumns(20);
        ta_users.setRows(5);
        ta_users.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ta_usersMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(ta_users);

        b_upload.setText("Attach File");
        b_upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_uploadActionPerformed(evt);
            }
        });

        lb_users.setText("Online Users");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat_client/images.jpeg"))); // NOI18N
        jLabel1.setText("jLabel1");

        b_send.setText("SEND");
        b_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_sendActionPerformed(evt);
            }
        });

        b_open.setText("Send File");
        b_open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_openActionPerformed(evt);
            }
        });

        tf_download.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tf_downloadMouseReleased(evt);
            }
        });

        jLabel3.setText("The file type must be .docx, text document (.txt, Notepad), WordPad");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb_username)
                        .addGap(18, 18, 18)
                        .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(570, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tf_chat)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(b_send))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(b_upload)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(b_open)
                                        .addGap(18, 18, 18)
                                        .addComponent(tf_download, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(68, 68, 68)
                                        .addComponent(b_disconnect))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lb_users)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(b_connect)
                                .addGap(74, 74, 74)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_disconnect)
                        .addGap(170, 170, 170))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb_username))
                                .addGap(21, 21, 21)
                                .addComponent(b_connect)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_upload)
                    .addComponent(b_open)
                    .addComponent(tf_download, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_users))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_chat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b_send))))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>                        

    private void b_connectActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        if(isConnected==false){
            username=tf_username.getText();
            tf_username.setEditable(false);
            
            try{
               sock=new Socket(address,port);
               InputStreamReader streamreader=new InputStreamReader(sock.getInputStream());
               reader=new BufferedReader(streamreader);
               writer=new PrintWriter(sock.getOutputStream());
               writer.println(username + "`is on-line now.`-------------------------------------");
               writer.flush();
               isConnected=true;
               JOptionPane.showMessageDialog(null,"Welcome! " + username + ",You can begin to chat");
            }catch(Exception ex){
                ta_chat.append("Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
            }
            ListenThread();
        }else if(isConnected==true){
            ta_chat.append("You are already connected.\n");
        }
    }                                         

    private void b_disconnectActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        if(isConnected==true){
            username=tf_username.getText();
            tf_username.setEditable(false);
                       
            try{
               sock=new Socket(address,port);
               InputStreamReader streamreader=new InputStreamReader(sock.getInputStream());
               reader=new BufferedReader(streamreader);
               writer=new PrintWriter(sock.getOutputStream());
               writer.println(username + "`is off on-line`----------------------");
               writer.flush();
               isConnected=true;
               JOptionPane.showMessageDialog(null,username + ", you logout successfully!");
               System.exit(0);
            }catch(Exception ex){
                ta_chat.append("Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
            }
            ListenThread();
        }else if(isConnected==false){
            ta_chat.append("You are already connected.\n");
        }
        Disconnect();
    }                                            

    private void b_uploadActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        attachment_path=f.getAbsolutePath();
        tf_download.setText(attachment_path);
    }                                        

    private void b_sendActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
  
        String nothing="";
        if((tf_chat.getText()).equals(nothing)){
            tf_chat.setText("");
            tf_chat.requestFocus();
        }else{
            try{
                writer.println(username + "`" + new Date().toString() + "`" + tf_chat.getText() + "`" + " ");
                writer.flush();
            }catch(Exception ex){
                ta_chat.append("Message was not sent. \n");
            }
            tf_chat.setText("");
            tf_chat.requestFocus();
        }
        tf_chat.setText("");
        tf_chat.requestFocus();
        
    }                                      
                                  
    private void b_openActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
     if(tf_download.getText().endsWith(".docx") || tf_download.getText().endsWith(".txt") || tf_download.getText().endsWith(".s") || tf_download.getText().endsWith(".java") || tf_download.getText().endsWith(".cpp")){
      String nothing="";
        if((tf_download.getText()).equals(nothing)){
            tf_download.setText("");
            tf_download.requestFocus();
        }else{
            try{
                writer.println(username + "`" + new Date().toString() + "`" + tf_download.getText() + "`" + "~");
                writer.flush();
            }catch(Exception ex){
                ta_chat.append("Message was not sent. \n");
            }
            tf_download.setText("");
            tf_download.requestFocus();
        }
        tf_download.setText("");
        tf_download.requestFocus();
     }else{
        JOptionPane.showMessageDialog(null,"The file type you attach is not correct. The file type must be .docx,text document(.txt,Notepad),WordPad. Please attach a correct file type again."); 
        tf_download.setText("");
     } 
    }                                      

    private void ta_chatMouseReleased(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
        if(evt.isPopupTrigger()){
            jPopupMenu3.show(this,evt.getX(),evt.getY());
        }
    }                                     

    private void tf_chatMouseReleased(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
        if(evt.isPopupTrigger()){
            jPopupMenu1.show(this,evt.getX(),evt.getY());
        }
    }                                     

    private void ta_usersMouseReleased(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
        
        if(evt.isPopupTrigger()){
            jPopupMenu4.show(this,evt.getX(),evt.getY());
        }
        
    }                                      

    private void tf_downloadMouseReleased(java.awt.event.MouseEvent evt) {                                          
        // TODO add your handling code here:
        if(evt.isPopupTrigger()){
            jPopupMenu2.show(this,evt.getX(),evt.getY());
        }
    }                                         

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        tf_chat.copy();
    }                                          

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here: 
        tf_chat.paste();
    }                                          

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        tf_chat.cut();
    }                                          

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:    
        tf_chat.selectAll();
    }                                          

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        tf_download.copy();
    }                                                                         

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        tf_download.selectAll();
    }                                          

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        ta_chat.copy();
    }                                          

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        ta_chat.selectAll();
    }                                          

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        ta_users.copy();
    }                                          

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        ta_users.selectAll();
    }                                           
/**/
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
            java.util.logging.Logger.getLogger(client_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(client_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(client_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(client_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new client_frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton b_connect;
    private javax.swing.JButton b_disconnect;
    private javax.swing.JButton b_open;
    private javax.swing.JButton b_send;
    private javax.swing.JButton b_upload;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_username;
    private javax.swing.JLabel lb_users;
    private javax.swing.JTextArea ta_chat;
    private javax.swing.JTextArea ta_users;
    private javax.swing.JTextField tf_chat;
    private javax.swing.JTextField tf_download;
    private javax.swing.JTextField tf_username;
    // End of variables declaration                   
}
