
package project;

import com.bulenkov.darcula.DarculaLaf;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aditya
 */
public class StudentLoginPage extends javax.swing.JFrame {

    /**
     * Creates new form studentLoginPage
     */
    public StudentLoginPage() {
        initComponents();
    }

    public static User loggedInStudent = null;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        studentLogin = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        passwordlabel = new javax.swing.JLabel();
        usernameInput = new javax.swing.JTextField();
        passwordInput = new javax.swing.JPasswordField();
        exitButton = new javax.swing.JButton();
        loginButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        studentLogin.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        studentLogin.setText("Student  Login");

        idLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        idLabel.setText("Student ID");

        passwordlabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        passwordlabel.setText("Password");


        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(114, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(studentLogin)
                        .addGap(112, 112, 112))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(idLabel)
                    .addComponent(passwordlabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loginButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(resetButton))
                    .addComponent(usernameInput)
                    .addComponent(passwordInput))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(studentLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabel)
                    .addComponent(usernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordlabel)
                    .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton)
                    .addComponent(resetButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(exitButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        usernameInput.setText("");
        passwordInput.setText("");
    }//GEN-LAST:event_resetButtonActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        String username = usernameInput.getText();
        String password = new String(passwordInput.getPassword());
        if (username.equals("") || password.equals("")) {
            Utils.showMessage(this, "Please enter proper details");
            return;
        }

        if (username.length() > 20 || password.length() > 20) {
            Utils.showMessage(this, "Credentials cannot be longer than 20 characters");
            return;
        }

        SQLutils sql = new SQLutils(this);
        List<Map<String, Object>> resultSet = sql.selectQueryWhere("studentID as username, password", "students", String.format("username=\'%s\'", username), "");
        sql.close();
        if (resultSet.isEmpty()) {
            Utils.showMessage(this, String.format("Invalid username %s!", username));
            resetButton.doClick();
            return;
        }

        User user1 = new User(resultSet.get(0));
        User user2 = new User(username, password);
        if (user1.verify(user2)) {
            loggedInStudent = user2;
            //new StudentPage().setVisible(true);
            this.dispose();
        } else {
            Utils.showMessage(this, "Invalid password for user " + username);
            passwordInput.setText("");
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        new HomePage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_exitButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Darcula look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">

        try {
            System.err.close();
            System.setErr(new PrintStream(new FileOutputStream("err.log")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // set laf to darcula
        try {
            UIManager.setLookAndFeel(new DarculaLaf());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentLoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentLoginPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel idLabel;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordInput;
    private javax.swing.JLabel passwordlabel;
    private javax.swing.JButton resetButton;
    private javax.swing.JLabel studentLogin;
    private javax.swing.JTextField usernameInput;
    // End of variables declaration//GEN-END:variables
}
