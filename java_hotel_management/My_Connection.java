package java_hotel_management;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class My_Connection {
    public Connection createConnection(){
        Connection connection=null;
        MysqlDataSource d=new MysqlDataSource();
       d.setServerName("localhost");
       d.setPortNumber(3306);
       d.setUser("root");
       d.setPassword("");
       d.setDatabaseName("userlogin");
        try {
            connection=d.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(My_Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
