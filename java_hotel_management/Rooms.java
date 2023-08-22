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
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Windows
 */
public class Rooms {
    My_Connection my_connection = new My_Connection();
    public void fillRooms_TYPE_JTable(JTable table) {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `type`";
        try {
            ps = my_connection.createConnection().prepareStatement(selectQuery);
            rs = ps.executeQuery();
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[3];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                
                tableModel.addRow(row);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fillRoomsJTable(JTable table) {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `rooms`";
        try {
            ps = my_connection.createConnection().prepareStatement(selectQuery);
            rs = ps.executeQuery();
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[5];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                ;
                
                tableModel.addRow(row);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void fillRooms_TYPE_JComboBox(JComboBox combobox) {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `type`";
        try {
            ps = my_connection.createConnection().prepareStatement(selectQuery);
            rs = ps.executeQuery();
            
      
            while (rs.next()) {
               
               combobox.addItem(rs.getInt(1));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean addRooms(int No_Room,int Room_Type,String Ac_Type,String Reserved ,String Class){
        PreparedStatement st;
        String addQuery="INSERT INTO `rooms`(`r_number`, `Room_Type`, `Ac_Type`, `Reserved`, `Class`) VALUES (?,?,?,?,?)";
       try {
           st =my_connection.createConnection().prepareStatement(addQuery);
           st.setInt(1,No_Room);
           st.setInt(2,Room_Type);
           st.setString(3,Ac_Type);
           st.setString(5,Class);
           st.setString(4,Reserved);
           
               return (st.executeUpdate() > 0);
           
       
           

       } catch (SQLException ex) {
           Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
    }
    public boolean editRooms(int No_Room,int Room_Type,String Ac_Type,String Reserved,String Class ){
        PreparedStatement st;
        String editQuery="UPDATE `rooms` SET `Room_Type`=?,`Ac_Type`=?,`Reserved`=?,`Class`=? WHERE `r_number`=?";
       try {
           st =my_connection.createConnection().prepareStatement(editQuery);
           st.setInt(1,Room_Type);
           st.setString(2,Ac_Type);
           st.setString(3,Reserved);
           st.setString(4,Class);
           st.setInt(5,No_Room);
           
           
           return (st.executeUpdate() > 0);
           
           } catch (SQLException ex) {
           Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
    }
    public boolean removeRooms(int roomNumber){
        PreparedStatement st;
        String deleteQuery="DELETE FROM `rooms` WHERE `r_number`=?";
       try {
           st =my_connection.createConnection().prepareStatement(deleteQuery);
           
           st.setInt(1,roomNumber );
           
           return (st.executeUpdate() > 0);
           
           } catch (SQLException ex) {
           Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
    }
    public boolean setRoomToReserved(int No_Room , String reserved){
        PreparedStatement st;
        String editQuery="UPDATE `rooms` SET `reserved`=? WHERE `r_number`=?";
       try {
           st =my_connection.createConnection().prepareStatement(editQuery);
           st.setString(1, reserved);
           st.setInt(2,No_Room);
           
           
           return (st.executeUpdate()>0);
           
           } catch (SQLException ex) {
           Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
    }
    public String  isRoomReserved(int No_Room){
        PreparedStatement st;
        ResultSet rs;
        String editQuery="SELECT  `Reserved` FROM `rooms` WHERE `r_number`=?";
       try {
           st =my_connection.createConnection().prepareStatement(editQuery);
          
           st.setInt(1,No_Room);
           rs=st.executeQuery();
           if(rs.next()){
               return rs.getString(1);
           }
           else{
               return"";
           }
           } catch (SQLException ex) {
           Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
           return "";
       }
    }
 
}
    
    
    
    
    

