package database;

import model.appointment.Template;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
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
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement ps;

        String query = "select * from templates where weekday = ?";

        Template template;
        Set<Template> templates = new HashSet<>();
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, dayOfWeek.name());
            rs = ps.executeQuery();
            while (rs.next()){

                template = new Template();
                DayOfWeek weekday = DayOfWeek.valueOf(rs.getString("weekday"));
                String time = rs.getString("starttime");
                LocalTime localTime = LocalTime.parse(time);
                int id = rs.getInt("id");
                template.setWeekday(weekday);
                template.setStartTime(localTime);
                template.setId(id);
                System.out.println(template);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connector.close(conn);
            connector.close(stmt);
            connector.close(rs);
        }
        return templates;
    }
}
