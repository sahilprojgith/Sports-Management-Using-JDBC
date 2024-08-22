package SportsComitee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Trainer {

    private Connection connection;
    private Scanner scanner;

    public Trainer(Connection connection,Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    public void viewTrainers(){
        String query = "SELECT * FROM trainers;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Trainers : ");
            System.out.println("+------------+---------------------+-----------++");
            System.out.println("| Trainer id |  Name               |  Specialization   ||");
            System.out.println("+------------+---------------------+-----------++");

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("%-12s|%-17s|%-11s|\n", id, name, specialization);
                System.out.println("+------------+---------------------+-----------++");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getTrainersById(int id) {
        String query = "SELECT * FROM trainers WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



}
