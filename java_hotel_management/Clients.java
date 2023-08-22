/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_hotel_management;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Windows
 */
public class Clients {
    My_Connection my_connection=new My_Connection();
    public boolean addClients(String FirstName,String LastName,String Phone_no,String Email){
        PreparedStatement st;
        String addQuery="INSERT INTO `clients`(`FirstName`, `LastName`, `Phone_no`, `Email`) VALUES (?,?,?,?)";
       try {
           st =my_connection.createConnection().prepareStatement(addQuery);
           st.setString(1,FirstName);
           st.setString(2,LastName);
           st.setString(3,Phone_no);
           st.setString(4,Email);
           
               return (st.executeUpdate() > 0);
           
       
           

       } catch (SQLException ex) {
           Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
    }
    public boolean editClients(int ID,String FirstName,String LastName,String Phone_no,String Email){
        PreparedStatement st;
        String editQuery="UPDATE `clients` SET `FirstName`=?,`LastName`=?,`Phone_no`=?,`Email`=? WHERE `ID`=?";
       try {
           st =my_connection.createConnection().prepareStatement(editQuery);
           st.setString(1,FirstName);
           st.setString(2,LastName);
           st.setString(3,Phone_no);
           st.setString(4,Email);
           st.setInt(5,ID);
           
           
           return (st.executeUpdate() > 0);
           
           } catch (SQLException ex) {
           Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
    }
    public boolean removeClients(int ID){
        PreparedStatement st;
        String deleteQuery="DELETE FROM `clients` WHERE `ID`=?";
       try {
           st =my_connection.createConnection().prepareStatement(deleteQuery);
           
           st.setInt(1, ID);
           
           return (st.executeUpdate() > 0);
           
           } catch (SQLException ex) {
           Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
    }

    public void fillClientsJTable(JTable table){
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery="SELECT * FROM `clients`";
       try {
           ps = my_connection.createConnection().prepareStatement(selectQuery);
           rs=ps.executeQuery();
           DefaultTableModel tableModel=(DefaultTableModel)table.getModel();
           Object[] row;
           while(rs.next()){
               row=new Object[5];
               row[0]=rs.getInt(1);
               row[1]=rs.getString(2);
               row[2]=rs.getString(3);
               row[3]=rs.getString(4);
               row[4]=rs.getString(5);
               tableModel.addRow(row);
                       
           }
               
       } catch (SQLException ex) {
           Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}
