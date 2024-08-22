package SportsComitee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Athlete {
    private Connection connection;
    private Scanner scanner;

    public  Athlete(Connection connection,Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addAthlete(){
        System.out.println("Name of the Athlete : ");
        String name = scanner.next();
        System.out.println("Gender of the Athlete : ");
        String gender = scanner.next();

        try{
        String query = "INSERT INTO athlete(name,gender) VALUES(?,?)";
        PreparedStatement preparedStatement =connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,gender);

        int AffectedRows = preparedStatement.executeUpdate();
        if(AffectedRows > 0){
            System.out.println("Athelete can join the oraganization");
        }else {
            System.out.println("Failed -- Try again Later");
        }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void viewAthlete(){
        String query = "SELECT * FROM athlete;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("AThletes: ");
            System.out.println("+------------+---------------------+-----------++");
            System.out.println("| Athlete id |  Name               |  Gender   ||");
            System.out.println("+------------+---------------------+-----------++");

            while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name  = resultSet.getString("name");
            String gender = resultSet.getString("gender");
            System.out.printf("%-12s|%-17s|%-11s|\n", id, name, gender);
            System.out.println("+------------+---------------------+-----------++");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getAthleteByID(int id){
        String query = "SELECT * FROM athlete WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

            
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;


    }


}
