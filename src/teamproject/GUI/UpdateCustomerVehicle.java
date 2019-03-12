
package teamproject.GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import teamproject.Customer_Account.Customer;
import teamproject.Customer_Account.Vehicle;
import teamproject.Databases.DB_ImplClass;


public class UpdateCustomerVehicle extends javax.swing.JPanel {
  
    private String username;  
        Statement statement;
        Connection connection = null;
        Customer c;
        DB_ImplClass db = new DB_ImplClass();
        ResultSet rs;
        String [] vehicleArray;
        Vehicle ve = new Vehicle();
        ArrayList<String> vehicles = new ArrayList<>();
        ArrayList<String> customers = new ArrayList<>();

    public UpdateCustomerVehicle(String username, Customer c) {
        this.username = username;
        this.ve = ve;
        this.c = c;
        initComponents();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.textFieldUsername.setText(username);
        connection = db.connect();
        statement = db.getStatement();
        
        Vehicles();
        selectVehicle();
        
    }
    
     private String[] CreateArray(ArrayList<String> vehicles){
        String[] newArray = new String[vehicles.size()];
        newArray = vehicles.toArray(newArray);
        return newArray;
    }
     
     private void NoList(){
        try {
      String sql = ("Select * from vehicle where ID = '" + null);  
       PreparedStatement ps = null;
      try {
            ps = connection.prepareStatement(sql);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        }  
         catch(SQLException e)
        {
          System.err.println(e.getMessage());
        }
     
     }
     
     private void Vehicles(){
        try {
           String sql = ("Select * from Vehicle where Customername = '" + c.getName() + "'");
            PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            rs = ps.executeQuery();
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        }
           listVehicles.removeAll();   
           ArrayList<String> vehicles = new ArrayList<>();
           
           try{
        while(rs.next())
          {
            // read the result set
            String v = rs.getString("registrationNumber")+", "+rs.getString("make")+", "+rs.getString("model")+", "+rs.getString("engineSerial")+", "+rs.getString("chassisNumber")+", "+rs.getString("colour");
            vehicles.add(v);
          } 
        }
        catch(SQLException e){
        }
           
          vehicleArray = CreateArray(vehicles); 
          
          listVehicles.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return vehicleArray.length; }
            public String getElementAt(int i) { return vehicleArray[i]; }
        });
     }
     
      private void selectVehicle(){
        try {
        String sql = ("Select * from vehicle");
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            }  catch (Exception e) {
                e.printStackTrace();
            }
        rs = ps.executeQuery();
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        }
        
        }
      
       private void WriteToDatabase(){
       String sql ;
       //for (String t : vehicles)
       try {
        sql = ("Insert into Vehicle values(( Select registrationNumber from Vehicle where registrationNumber = '" + textFieldRegistrationNo.getText()+") ,"
                + "(Select make from Vehicle where make = '" + textFieldMake.getText() + ") , "
                + "(Select model from Vehicle where model = '" + textFieldModel.getText() + ") , "
                + "(Select engineSerial from Vehicle where engineSerial = '" + textFieldEngineSerialNo.getText()+ ") , "
                + "(Select chassisNumber from Vehicle where chassisNumber = '" + textFieldChassisNo.getText()+ ") , "
                + "(Select colour from Vehicle where colour = '" + textFieldColour.getText() + "))");
                
        PreparedStatement ps = null;  
         try {
            ps = connection.prepareStatement(sql);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }  
         ps.executeUpdate();
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        } 
       
       try {
           String SQL = null;
           for (String cu : customers)
           SQL = ("Insert into Customer values(name, emailAddress, address, postCode, telephoneNumber, fax)" + cu);
           PreparedStatement ps = null;  
           try {
            ps = connection.prepareStatement(SQL);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }  
         ps.executeUpdate();
        }
       catch(SQLException e)
        {
          System.err.println(e.getMessage());
        } 
        
       }
     
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        buttonDone = new javax.swing.JButton();
        labelColour = new javax.swing.JLabel();
        labelLoggedIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        textFieldUsername = new javax.swing.JTextField();
        labelVehicles = new javax.swing.JLabel();
        labelRegistrationNo = new javax.swing.JLabel();
        labelMake = new javax.swing.JLabel();
        labelModel = new javax.swing.JLabel();
        labelChassisNo = new javax.swing.JLabel();
        labelLastMoTDate = new javax.swing.JLabel();
        labelEngineSerialNo = new javax.swing.JLabel();
        labelMoTDateFormat = new javax.swing.JLabel();
        textFieldEngineSerialNo = new javax.swing.JTextField();
        textFieldModel = new javax.swing.JTextField();
        textFieldMake = new javax.swing.JTextField();
        textFieldRegistrationNo = new javax.swing.JTextField();
        textFieldChassisNo = new javax.swing.JTextField();
        textFieldColour = new javax.swing.JTextField();
        buttonSaveVehicleChanges = new javax.swing.JButton();
        labelCustomerDetails = new javax.swing.JLabel();
        buttonBack = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        listVehicles = new javax.swing.JList<>();
        labelVariableDiscount = new javax.swing.JLabel();
        textFieldLastServiceDay = new javax.swing.JTextField();
        textFieldLastServiceMonth = new javax.swing.JTextField();
        textFieldLastServiceYear = new javax.swing.JTextField();
        textFieldLastMoTYear = new javax.swing.JTextField();
        textFieldLastMoTDay = new javax.swing.JTextField();
        textFieldLastMoTMonth = new javax.swing.JTextField();
        labelLastServiceDate = new javax.swing.JLabel();
        labelServiceDateFormat = new javax.swing.JLabel();
        buttonEditVehicle1 = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, -1, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, -1, -1));

        buttonDone.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonDone.setText("Done");
        buttonDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDoneActionPerformed(evt);
            }
        });
        jPanel1.add(buttonDone, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 660, -1, -1));

        labelColour.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelColour.setText("Colour:");
        jPanel1.add(labelColour, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 510, -1, -1));

        labelLoggedIn.setText("Logged In as:");
        jPanel1.add(labelLoggedIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

        buttonExit.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonExit.setText("Exit");
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });
        jPanel1.add(buttonExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 0, -1, -1));
        jPanel1.add(textFieldUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 220, 30));

        labelVehicles.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelVehicles.setText("Vehicles:");
        jPanel1.add(labelVehicles, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, -1, -1));

        labelRegistrationNo.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelRegistrationNo.setText("Registration No:");
        jPanel1.add(labelRegistrationNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 360, -1, -1));

        labelMake.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelMake.setText("Make:");
        jPanel1.add(labelMake, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, -1, -1));

        labelModel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelModel.setText("Model:");
        jPanel1.add(labelModel, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, -1, -1));

        labelChassisNo.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelChassisNo.setText("Chassis No:");
        jPanel1.add(labelChassisNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, -1, -1));

        labelLastMoTDate.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelLastMoTDate.setText("Next MoT Date: ");
        jPanel1.add(labelLastMoTDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 570, 140, -1));

        labelEngineSerialNo.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelEngineSerialNo.setText("Engine Serial No:");
        jPanel1.add(labelEngineSerialNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 450, -1, -1));

        labelMoTDateFormat.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelMoTDateFormat.setText("(dd/mm/yyyy)");
        jPanel1.add(labelMoTDateFormat, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 580, -1, -1));
        jPanel1.add(textFieldEngineSerialNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 250, -1));
        jPanel1.add(textFieldModel, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 420, 250, -1));
        jPanel1.add(textFieldMake, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 390, 250, -1));
        jPanel1.add(textFieldRegistrationNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 360, 250, -1));
        jPanel1.add(textFieldChassisNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 480, 250, -1));
        jPanel1.add(textFieldColour, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 510, 250, -1));

        buttonSaveVehicleChanges.setText("Save Changes");
        buttonSaveVehicleChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveVehicleChangesActionPerformed(evt);
            }
        });
        jPanel1.add(buttonSaveVehicleChanges, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 610, 130, -1));

        labelCustomerDetails.setFont(new java.awt.Font("Lucida Grande", 1, 72)); // NOI18N
        labelCustomerDetails.setText("Customer Vehicles");
        jPanel1.add(labelCustomerDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        buttonBack.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });
        jPanel1.add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, -1));

        jScrollPane5.setViewportView(listVehicles);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, 700, -1));

        labelVariableDiscount.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        jPanel1.add(labelVariableDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 540, -1, -1));

        textFieldLastServiceDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldLastServiceDayActionPerformed(evt);
            }
        });
        jPanel1.add(textFieldLastServiceDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 540, 30, 30));

        textFieldLastServiceMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldLastServiceMonthActionPerformed(evt);
            }
        });
        jPanel1.add(textFieldLastServiceMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 540, 30, 30));

        textFieldLastServiceYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldLastServiceYearActionPerformed(evt);
            }
        });
        jPanel1.add(textFieldLastServiceYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 540, 50, 30));

        textFieldLastMoTYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldLastMoTYearActionPerformed(evt);
            }
        });
        jPanel1.add(textFieldLastMoTYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 570, 50, 30));

        textFieldLastMoTDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldLastMoTDayActionPerformed(evt);
            }
        });
        jPanel1.add(textFieldLastMoTDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 570, 30, 30));

        textFieldLastMoTMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldLastMoTMonthActionPerformed(evt);
            }
        });
        jPanel1.add(textFieldLastMoTMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 570, 30, 30));

        labelLastServiceDate.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        labelLastServiceDate.setText("Next Service Date: ");
        jPanel1.add(labelLastServiceDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 540, -1, -1));

        labelServiceDateFormat.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelServiceDateFormat.setText("(dd/mm/yyyy)");
        jPanel1.add(labelServiceDateFormat, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 550, -1, -1));

        buttonEditVehicle1.setText("Edit");
        buttonEditVehicle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditVehicle1ActionPerformed(evt);
            }
        });
        jPanel1.add(buttonEditVehicle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, 100, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1280, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldLastServiceDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldLastServiceDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldLastServiceDayActionPerformed

    private void textFieldLastServiceMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldLastServiceMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldLastServiceMonthActionPerformed

    private void textFieldLastServiceYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldLastServiceYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldLastServiceYearActionPerformed

    private void textFieldLastMoTYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldLastMoTYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldLastMoTYearActionPerformed

    private void textFieldLastMoTDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldLastMoTDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldLastMoTDayActionPerformed

    private void textFieldLastMoTMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldLastMoTMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldLastMoTMonthActionPerformed

    private void buttonDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDoneActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        new MainMenu(username);
    }//GEN-LAST:event_buttonDoneActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        JFrame f = (JFrame) this.getParent().getParent().getParent().getParent();
        f.dispose();
        new CustomerList(username);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonEditVehicle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditVehicle1ActionPerformed
        if(listVehicles.getSelectedValue() == null){
        String mess = "Please choose vehicle record first!";   
        JOptionPane.showMessageDialog(new JFrame(), mess);
        } else {
        selectVehicle();
       
        try{
            ve.setRegistrationNumber(rs.getString("registrationNumber"));
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        }
       
        try{
            ve.setMake(rs.getString("make"));
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        }
          try{
            ve.setModel(rs.getString("model"));  
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        }
          try{
            ve.setEngineSerial(rs.getString("engineSerial"));
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        }
          try{
            ve.setChassisNumber(rs.getString("chassisNumber"));
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        }
       try{
            ve.setColour(rs.getString("colour"));
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        }
       
       textFieldRegistrationNo.setText(ve.getRegistrationNumber());
       textFieldMake.setText(ve.getMake());
       textFieldModel.setText(ve.getModel());
       textFieldEngineSerialNo.setText(ve.getEngineSerial());
       textFieldChassisNo.setText(ve.getChassisNumber());
       textFieldColour.setText(ve.getColour());
        }
    }//GEN-LAST:event_buttonEditVehicle1ActionPerformed

    private void buttonSaveVehicleChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveVehicleChangesActionPerformed
        WriteToDatabase();
    }//GEN-LAST:event_buttonSaveVehicleChangesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonDone;
    private javax.swing.JButton buttonEditVehicle1;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonSaveVehicleChanges;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelChassisNo;
    private javax.swing.JLabel labelColour;
    private javax.swing.JLabel labelCustomerDetails;
    private javax.swing.JLabel labelEngineSerialNo;
    private javax.swing.JLabel labelLastMoTDate;
    private javax.swing.JLabel labelLastServiceDate;
    private javax.swing.JLabel labelLoggedIn;
    private javax.swing.JLabel labelMake;
    private javax.swing.JLabel labelMoTDateFormat;
    private javax.swing.JLabel labelModel;
    private javax.swing.JLabel labelRegistrationNo;
    private javax.swing.JLabel labelServiceDateFormat;
    private javax.swing.JLabel labelVariableDiscount;
    private javax.swing.JLabel labelVehicles;
    private javax.swing.JList<String> listVehicles;
    private javax.swing.JTextField textFieldChassisNo;
    private javax.swing.JTextField textFieldColour;
    private javax.swing.JTextField textFieldEngineSerialNo;
    private javax.swing.JTextField textFieldLastMoTDay;
    private javax.swing.JTextField textFieldLastMoTMonth;
    private javax.swing.JTextField textFieldLastMoTYear;
    private javax.swing.JTextField textFieldLastServiceDay;
    private javax.swing.JTextField textFieldLastServiceMonth;
    private javax.swing.JTextField textFieldLastServiceYear;
    private javax.swing.JTextField textFieldMake;
    private javax.swing.JTextField textFieldModel;
    private javax.swing.JTextField textFieldRegistrationNo;
    private javax.swing.JTextField textFieldUsername;
    // End of variables declaration//GEN-END:variables
}
