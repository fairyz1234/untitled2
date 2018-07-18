import java.sql.*;
import java.util.Scanner;

/**
 * Created by Administrator on 2018/7/17.
 */
public class BOOK {
    private Connection getconnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String URL = "jdbc:mysql://127.0.0.1:3306/work";
            try {

                Connection connection = DriverManager.getConnection(URL,
                        "root", "root");
                return connection;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
          return null;
    }
    private void insertData(int id,String name,String publishers,String author){
        Connection connection = null;
        Statement statement = null;

        try {
        connection=getconnection();
        //2. 构建添加数据的sql语句
        String sql = "insert into work " +
                "values(" + id + ",'" + name + "','" + publishers + "', '"+ author +"' )";
        //System.out.println(sql);
        //3. 执行sql语句
        statement = connection.createStatement();
        //4. 得到执行后的结果，确定是否添加成功
        int rows = statement.executeUpdate(sql);
        System.out.println("添加的行数为：" + rows);
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
            close(connection,statement,null);
        }
}
    private void deleteData(int id) {
        Connection connection = null;
        Statement statement = null;
        try {

             connection = getconnection();
            //2. 构建删除的sql语句
            String sql = "delete from name where id=" + id;
            //3. 执行删除语句
             statement = connection.createStatement();
            //4. 获取执行所影响的行数，判断是否执行成功
            int rows = statement.executeUpdate(sql);
            System.out.println("有" + rows + "行被删除成功！");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection,statement,null);
        }
    }



    private void updateData(int id,String publishers,String author) {


        try {

           Connection connection = getconnection();
            //2. 创建update sql 语句
            String sql = "update publishers set book_publishers='" + publishers +
                    "',book_author='" + author + "' where id=" + id;
            //3. 执行update 语句
           Statement statement = connection.createStatement();
            //4. 得到执行结果，确定是否成功
            int rows = statement.executeUpdate(sql);
            System.out.println("修改结果为：" + (rows > 0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void findAccountDataLikeKeyWord(String keyWord) {
        //1. 获取数据库连接
        Connection connection = getconnection();
        Statement statement = null;

        //2. 构建查询的sql语句
        String sql = "select book_name,book_publishers,book_author,id from name " +
                "where book_name like '%" + keyWord + "%' or book_publishers like '%" + keyWord + "%' or book_author like '%" +"%'";
        try {
            //3. 执行sql语句，并获得结果集
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            //4. 遍历结果集，输出每条记录的信息。
            StringBuffer buffer = new StringBuffer();
            buffer.append("------------------------------------------------------------------------------------------------" + System.lineSeparator());
            buffer.append("id\t\t\tname\t\t\tpublishers\t\t\tauthor\t\t\t" + System.lineSeparator());
            buffer.append("------------------------------------------------------------------------------------------------" + System.lineSeparator());
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("book_name");
                String publishers= resultSet.getString("book_publishers");
                String author = resultSet.getString("book_author");
                buffer.append(id + "\t| " + name + "| \t" + publishers + "|\t"+author+"|" + System.lineSeparator());
            }

            System.out.println(buffer.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void close(Connection connection,
                       Statement statement,
                       ResultSet resultSet) {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
            if(statement != null) {
                statement.close();
            }
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
public static void main(String [] args){
    Scanner scanner=new Scanner(System.in);
    JDBCDemo jdbcDemo=new JDBCDemo();
    while (true) {
        System.out.println("                    欢迎使用真图书系统                    ");
        System.out.println("1.添加数据   2.修改数据   3.删除数据    4.退出系统");
        int select=0;
        select=scanner.nextInt();
        while (select < 1 || select > 4) {
            System.out.println("选择的操作不能识别，请重新选择：");
            select = scanner.nextInt();
        }
        String value=null;

        if(select==1){
           System.out.println();
            value = scanner.next();
            String[] values = value.split(",");


        }else if(select==2){}
        }

    }

}

