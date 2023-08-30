package database;

import model.appointment.Template;

import java.sql.*;
import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

public class OfficeQueries {
    public Set<Template>[] getAllTemplates(){
        //todo
        Set<Template>[] templates = new Set[]{};
        return templates;
    }//end getAllTemplates

    public Set<Template> getTemplates_ByWeekday(DayOfWeek dayOfWeek){
        MySQL_Connector connector = new MySQL_Connector();
        Connection conn = connector.connect();
        Statement stmt;
        ResultSet rs;
        PreparedStatement ps;

        DayOfWeek.valueOf()

        //String query1 = "select * from templates where weekday = '?'";
        String query2 = "select * from templates where weekday = ?";

        Template template;
        Set<Template> templates = new HashSet<>();
        try {
            //conn.prepareStatement("select * from templates where weekday = ?");
            conn.prepareStatement(query2);


        }catch (SQLException e){
            e.printStackTrace();
        }


        return templates;
    }
}
