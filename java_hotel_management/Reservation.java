/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_hotel_management;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Windows
 */
public class Reservation {
    //ALTER TABLE reservation ADD CONSTRAINT fk_room_number FOREIGN KEY (room_number)REFERENCES rooms(r_number) on DELETE CASCADE
    My_Connection my_connection = new My_Connection();
    Rooms room=new Rooms();
    public boolean addReservation(int clients_id,int room_number,String date_In,String date_out){
        PreparedStatement st;
        String addQuery="INSERT INTO `reservation`( `clients_id`, `room_number`, `date_In`, `date_out`) VALUES (?,?,?,?)";
       try {
           st =my_connection.createConnection().prepareStatement(addQuery);
           st.setInt(1,clients_id);
           st.setInt(2,room_number);
           st.setString(3,date_In);
           st.setString(4,date_out);
           if(room.isRoomReserved(room_number).equals("No")){
               if(st.executeUpdate() >0){
                   room.setRoomToReserved(room_number, "Yes");
                   return true;
               }else{
                   return false;
               }
           }else{
               JOptionPane.showMessageDialog(null,"This Room is already reserved","Room Reserved",JOptionPane.WARNING_MESSAGE);
               return false;
           }
           
       } catch (SQLException ex) {
           Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
    }
     public boolean editReservation(int reservation_id,int clients_id,int room_number,String date_In,String date_out ){
        PreparedStatement st;
        String editQuery="UPDATE `reservation` SET `clients_id`=?,`room_number`=?,`date_In`=?,`date_out`=? WHERE `id`=?";
       try {
           st =my_connection.createConnection().prepareStatement(editQuery);
           
           st.setInt(1,clients_id);
           st.setInt(2,room_number);
           st.setString(3,date_In);
           st.setString(4,date_out);
           st.setInt(5,reservation_id);
           
           
           return (st.executeUpdate() > 0);
           
           } catch (SQLException ex) {
           Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
    }
    public boolean removeReservation(int reservation_id){
        PreparedStatement st;
        String deleteQuery="DELETE FROM `reservation` WHERE `id`=?";
       try {
           st =my_connection.createConnection().prepareStatement(deleteQuery);
           st.setInt(1, reservation_id);
           int room_number=getRoomNumberFormatReservation(reservation_id);
           if(st.executeUpdate() > 0){
                   room.setRoomToReserved(room_number,"No");
                   return true;
               }
               else {
                   
                   return false;
               }  
           
            
           
           } catch (SQLException ex) {
           Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
    }
    public void fillReservationJTable(JTable table) {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `reservation`";
        try {
            ps = my_connection.createConnection().prepareStatement(selectQuery);
            rs = ps.executeQuery();
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[5];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getInt(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);              
                tableModel.addRow(row);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public  int getRoomNumberFormatReservation(int reservationID){
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT  `room_number` FROM `reservation` WHERE `id`=?";
        try {
            ps = my_connection.createConnection().prepareStatement(selectQuery);
            ps.setInt(1, reservationID);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
            else{
                return 0;
            }
            } catch (SQLException ex) {
            Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
    

    
    
    
    
    
    


