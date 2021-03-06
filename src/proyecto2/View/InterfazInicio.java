/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.View;

import proyecto2.Controller.Usuario;

/**
 *
 * @author HP
 */
public class InterfazInicio extends javax.swing.JFrame {
   
    /**
     * Creates new form InterfazInicio
     */
    public InterfazInicio() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nickname = new javax.swing.JTextField();
        unirse = new javax.swing.JButton();
        hola = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(nickname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 250, -1));

        unirse.setText("Login");
        unirse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unirseActionPerformed(evt);
            }
        });
        getContentPane().add(unirse, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, -1, -1));

        hola.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        hola.setText("WELCOME");
        getContentPane().add(hola, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 190, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void unirseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unirseActionPerformed
        String nick = nickname.getText();
       if(nick.equals("")){
          System.out.println("Esperando nick");
       }else{
           Usuario usuario = new Usuario(nick);
           this.setVisible(false);
           
       }
        
    }//GEN-LAST:event_unirseActionPerformed

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
            java.util.logging.Logger.getLogger(InterfazInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel hola;
    private javax.swing.JTextField nickname;
    private javax.swing.JButton unirse;
    // End of variables declaration//GEN-END:variables
}
