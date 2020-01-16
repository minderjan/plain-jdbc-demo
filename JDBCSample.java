import java.sql.*;

public class JDBCSample {

   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3333/jdbc";

   //  Database credentials
   static final String USER = "jdcb";
   static final String PASS = "jdbc_pw";
   
   public static void main(String[] args) {

   Connection conn = null;
   Statement stmt = null;

   try{

      // Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      // Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      String sql;
      stmt = conn.createStatement();

      // Clear Database
      System.out.println("Clean Database Tables...");
      sql = "DROP TABLE IF EXISTS Employees";
      stmt.executeUpdate(sql);

      // Create Database Table
      System.out.println("Creating table in given database...");
      sql = "CREATE TABLE Employees (id INT NOT NULL AUTO_INCREMENT, age INT(5), first VARCHAR(100), last VARCHAR(100), PRIMARY KEY ( id ));";
      stmt.executeUpdate(sql);

      // Insert Data
      sql = "INSERT INTO Employees (age, first, last) VALUES (26, 'Jan', 'Minder')";
      stmt.executeUpdate(sql);

      sql = "INSERT INTO Employees (age, first, last) VALUES (26, 'Michi', 'Bertschi')";
      stmt.executeUpdate(sql);

      sql = "INSERT INTO Employees (age, first, last) VALUES (26, 'Dominik', 'Wüthrich')";
      stmt.executeUpdate(sql);
      
      // Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      sql = "SELECT id, first, last, age FROM Employees";
      ResultSet rs = stmt.executeQuery(sql);

      // Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         int id  = rs.getInt("id");
         int age = rs.getInt("age");
         String first = rs.getString("first");
         String last = rs.getString("last");

         //Display values
         System.out.print("ID: " + id);
         System.out.print(", Age: " + age);
         System.out.print(", First: " + first);
         System.out.println(", Last: " + last);
      }
      // Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
   System.out.println("Goodbye!");
}
}
