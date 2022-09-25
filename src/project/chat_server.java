/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author admin
 */
public class chat_server extends javax.swing.JFrame {
    static ServerSocket ss;
   static Socket s;
   static DataInputStream din;
   static DataOutputStream dout;
    /**
     * Creates new form chat_server
     */
    public chat_server() {
        initComponents();
       // runServer();
       this.retrive();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        msg_text = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        msg_area = new javax.swing.JTextArea();
        msgsend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        msg_area.setColumns(20);
        msg_area.setRows(5);
        jScrollPane1.setViewportView(msg_area);

        msgsend.setText("Send");
        msgsend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msgsendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(msgsend, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(msgsend, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void msgsendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msgsendActionPerformed
    try
    {
        String msg="";
        msg=msg_text.getText().trim();
        dout.writeUTF(msg);
    }
    catch(Exception e)
    {
        System.out.println(e);
        e.printStackTrace();
    }
    }//GEN-LAST:event_msgsendActionPerformed
    public void retrive()
    {
        try{
            String url="jdbc:mysql://127.0.0.1:3306/pranali";
            Connection con= DriverManager.getConnection(url,"root","system");
            String q="select * from chat";
            PreparedStatement ps=con.prepareStatement(q);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                 if((rs.getString("sender_msg")).equals(""))
                {
                    msg_area.setText(msg_area.getText()+"\n"+rs.getString("reciver")+":"+rs.getString("reciver_msg")+"\n");
                }
                else
                {
                    msg_area.setText(msg_area.getText()+"\n"+rs.getString("sender")+":"+rs.getString("sender_msg")+"\n");
                }
            }
        }catch(SQLException ex)
        {
            System.out.println(ex);
        }
    }
    /**
     * @param args the command l     */
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
            java.util.logging.Logger.getLogger(chat_server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chat_server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chat_server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chat_server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chat_server().setVisible(true);
            }
        });
      
    
            try {
                String url="jdbc:mysql://127.0.0.1:3306/pranali";
            Connection con= DriverManager.getConnection(url,"root","system");
            String q="Insert into chat values(?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(q);
            ps.setString(1,"Ruhaan");
            ps.setString(2,"Ruhi");
                       
                ss=new ServerSocket(5000);
                s=ss.accept();
                din= new DataInputStream(s.getInputStream());
                dout=new DataOutputStream(s.getOutputStream());
                String msginput="";
                while(!msginput.equals("Exit"))
                {
                    msginput=din.readUTF();
                    msg_area.setText(msg_area.getText().trim()+"\n\nRuhi:"+msg_text.getText()+"\n\nRuhaan:"+msginput);
                    msg_text.setText("");
                     ps.setString(3,msginput);
                    ps.setString(4,"");
                 ps.executeUpdate();
                }
            } 
            catch (Exception ex) 
            {
                //Logger.getLogger(chat_server.class.getName()).log(Level.SEVERE, null, ex);    
                System.out.println(ex);
                ex.printStackTrace();
            }   
            
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea msg_area;
    private static javax.swing.JTextField msg_text;
    private javax.swing.JButton msgsend;
    // End of variables declaration//GEN-END:variables
}
