package old;

import java.sql.*;

public class Db {
    public static void main(String[] args) throws SQLException {
        Connection cn = null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            //  p.load(new FileReader("config.properties"));
            String url = "jdbc:postgresql://itcitydb.c6eliwtv3p57.eu-central-1.rds.amazonaws.com/azexdb";
            String driver = "org.postgresql.Driver";
            String un = "itcityadmin";
            String pw = "A3$c_xEkQNA6R";
            Class.forName(driver);
            cn = DriverManager.getConnection(url, un, pw);
            if (!cn.isClosed()) {
                cn.setAutoCommit(false);
                System.out.println("DB connection is succes!");
            }
            String sql="select id,name,surname,status from person" ;
             ps=cn.prepareStatement(sql);
             rs=ps.executeQuery();
            while(rs.next()){
                System.out.printf(rs.getBigDecimal("id")+" ");
                System.out.printf(rs.getString("name")+" ");
                System.out.printf(rs.getString("surname")+" ");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(rs!=null){
                rs.close();
            }
            if(ps!=null){
                ps.close();
            }
            if(cn!=null){
                cn.close();
            }
        }
    }
}