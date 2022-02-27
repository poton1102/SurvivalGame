//package database;
//
//import GUI.HUD;
//
//import java.sql.*;
////jdbc
//public class connect {
//    private Connection connection;
//    private Statement statement;
//
//    //thiết lập kết nối với sql server 2014, thầy phải chỉnh lại url, username, password để nó hoạt động
//    public connect()  {
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=java";
//        String userName = "sa";
//        String password = "123456";
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            connection = DriverManager.getConnection(url, userName, password);
//            statement = connection.createStatement();
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    //lấy điểm cao nhất từ sql server 2014
//    public void extractPoint(){
//        String query = "select * from max_point";
//        try {
//           ResultSet rs = statement.executeQuery(query);
//           rs.next();
//           HUD.highscore = rs.getInt(1);
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//    //kiểm tra xem điểm hiện tại có lớn hơn điểm cao nhất không, nếu có update lại điểm
//    public void checkScore(){
//        String query = "select * from max_point";
//        try {
//            ResultSet rs = statement.executeQuery(query);
//            rs.next();
//            double max_point = rs.getInt(1);
//            if (HUD.score > max_point) updatePoint(HUD.score);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//    //update lại điểm lớn nhất trong sql server
//    public void updatePoint(double point){
//        String query = "update max_point set point = "+point;
//        try {
//            int col = statement.executeUpdate(query);
//                System.out.println(col +" rows have affected");
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//}