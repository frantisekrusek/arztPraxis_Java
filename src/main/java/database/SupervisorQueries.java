package database;

import model.generator.Supervisor;

import java.sql.*;
import java.time.Instant;

public class SupervisorQueries {

    MySQL_Connector connector = new MySQL_Connector();

    public Instant fetchLastUpdate() {
        Connection conn = connector.connect();
        Statement stmt = null;
        ResultSet rs = null;
        Instant instant = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT lastUpdate FROM supervisor WHERE  id = 1");
            rs.next();/*first row is current row now*/
            Timestamp timestamp1 = rs.getTimestamp("lastUpdate");
            instant = timestamp1.toInstant();
            //Supervisor.getInstance().setLastUpdate(instant);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            connector.close(rs);
            connector.close(stmt);
            connector.close(conn);
        }
        System.out.println("LOG SupervisorQueries.fetchLastUpdate, Instant: " + instant);
        return instant;
    }//end fetchLastUpdate()

    public void initializeLastUpdate(){

    }//end initializeLastUpdate()


    public void update_LastUpdate(){

    }

    //GETTER
    public MySQL_Connector getConnector() {
        return connector;
    }
}
