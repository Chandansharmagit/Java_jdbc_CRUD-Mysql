import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class databaseconnectionCRUD {

  private static final String username = "root";
  private static final String password = "C@nt3rbur";
  private static final String url = "jdbc:mysql://localhost:3306/newdb";

  public static void main(String[] args) {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection = DriverManager.getConnection(url, username, password);

      Scanner sc = new Scanner(System.in);
      String name = sc.nextLine();
      String email = sc.nextLine();

      // Update statement usng the batch process
      String update = "UPDATE newcollection SET id = 8 WHERE id = 11";
      PreparedStatement updateStatement = connection.prepareStatement(update);

      // Execute update and check result
      int updatedRows = updateStatement.executeUpdate();
      if (updatedRows > 0) {
        System.out.println("Record(s) updated successfully!");
      } else {
        System.out.println("No records updated!");
      }
      updateStatement.close();

      // Select statement to verify the update
      String select = "SELECT name, email FROM newcollection WHERE id = 8";
      PreparedStatement selectStatement = connection.prepareStatement(select);
      ResultSet resultSet = selectStatement.executeQuery();

      while (resultSet.next()) {
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
      }
      
      resultSet.close();
      selectStatement.close();
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
