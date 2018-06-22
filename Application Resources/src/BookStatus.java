/*
 * BookStatus.java
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author  Administrator
 */
public class BookStatus extends javax.swing.JFrame {
    
    /** Creates new form BookStatus */
    public BookStatus() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        CmdClose = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("e-Library : Book Status");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Refresh Status");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        CmdClose.setText("Close");
        CmdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmdCloseActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 255));
        jLabel1.setText("e Library - Book Status");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CmdClose, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CmdClose)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            MainClass mc=new MainClass();
            
            Connection connection;
            connection=DriverManager.getConnection(mc.StrUrl,mc.StrUid,mc.StrPwd);
            ResultSet rs;
            

            
            
            PreparedStatement stmt=connection.prepareStatement("select book_id, book_title, book_category, book_status from lib_book_master order by book_id");
            rs = stmt.executeQuery();
            
            
            
            jTable1.getColumnModel().getColumn(0).setHeaderValue("Book ID");
            jTable1.getColumnModel().getColumn(1).setHeaderValue("Title");
            jTable1.getColumnModel().getColumn(2).setHeaderValue("Category");     //sets the header of table columns
            jTable1.getColumnModel().getColumn(3).setHeaderValue("Status");
            jTable1.getTableHeader().resizeAndRepaint(); //displays or truncates the text depending upon window size selection in the respective columns//
            
            // Removing Previous Data
            while (jTable1.getRowCount() > 0) {
                ((DefaultTableModel) jTable1.getModel()).removeRow(0);
            }
            
            
            //Creating Object []rowData for jTable's Table Model
            
            int columns = rs.getMetaData().getColumnCount(); 
         // Retrieves the number, types and properties of this ResultSet object's columns.
            while (rs.next()) {
                Object[] row = new Object[columns];
                for (int i = 1; i <= columns; i++) {
                    row[i - 1] = rs.getObject(i); // sorts the data/values based on Primary Key i.e book id//
                }
                ((DefaultTableModel) jTable1.getModel()).insertRow(rs.getRow() - 1,row);
            }
            
        } catch (Exception e) {
            System.err.println(e);
            //System.exit(1);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void CmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmdCloseActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_CmdCloseActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

       
       this.setLocationRelativeTo(null);   //center alignment
       
       
        jTable1.getColumnModel().getColumn(0).setHeaderValue("Book ID");
        jTable1.getColumnModel().getColumn(1).setHeaderValue("Title");
        jTable1.getColumnModel().getColumn(2).setHeaderValue("Category");
        jTable1.getColumnModel().getColumn(3).setHeaderValue("Status");
        jTable1.getTableHeader().resizeAndRepaint(); //automatically resizes the length of column if data is lengthy

        // Removing Previous Data
        while (jTable1.getRowCount() > 0) {
            ((DefaultTableModel) jTable1.getModel()).removeRow(0);
        
        }
    }//GEN-LAST:event_formWindowOpened
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookStatus().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CmdClose;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    
}
