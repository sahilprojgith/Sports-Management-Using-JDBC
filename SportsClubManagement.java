package SportsComitee;

import java.sql.*;
import java.util.Scanner;

public class SportsClubManagement {

    private static final String url = "jdbc:mysql://localhost:3306/sportsclub";
    private static final String username ="root";
    private static final String password = "shadowball";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);

        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            Athlete athlete = new Athlete(connection,scanner);
            Trainer trainer = new Trainer(connection,scanner);
            while (true){

                System.out.println(" -- Sports Management Club  --");
                System.out.println("1. Add Athlete ");
                System.out.println("2. View Athlete");
                System.out.println("3. View Trainers ");
                System.out.println("4. Book Appointments");

               // System.out.println("5. Exit : ");
                System.out.println("Enter your choice : ");

                int choice =scanner.nextInt();

                switch (choice){
                    case 1:
                        athlete.addAthlete();
                        System.out.println();
                        break;
                    case 2:
                        athlete.viewAthlete();
                        System.out.println();
                        break;
                    case 3:
                        trainer.viewTrainers();
                        System.out.println();
                        break;
                    case 4:
                        //appointments
                        bookAppointments(athlete,trainer,scanner,connection);
                        System.out.println();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        connection.close();  // Close connection here
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice, Retry again after some time.....");
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void bookAppointments(Athlete athlete,Trainer trainer,Scanner scanner,Connection connection){
        System.out.println("Enter Athlete id : ");
        int athleteId = scanner.nextInt();
        System.out.println("Enter Trainers id : ");
        int trainersId = scanner.nextInt();
        System.out.println("Enter Appointment Date (YYYY-MM-DD): ");
        String appointmentsDate = scanner.next();

        if(athlete.getAthleteByID(athleteId) && trainer.getTrainersById(trainersId)){
            if(checkTrainerByAvailability(trainersId,appointmentsDate,connection)){
                String appointmentQuery ="INSERT INTO appointments(athlete_id, trainers_id, appointments_date) VALUES(?,?,?)";

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1, athleteId);
                    preparedStatement.setInt(2, trainersId);
                    preparedStatement.setString(3, appointmentsDate);
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Appointment Booked!");
                    } else {
                        System.out.println("Failed to Book appointment");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }else {
            System.out.println("Either Athlete or Trainer doesn't exist ......");
        }
    }

    public static boolean checkTrainerByAvailability(int trainersID, String appointmentDate, Connection connection){

        String query = "SELECT COUNT(*) FROM appointments WHERE trainers_id = ? AND appointment_date = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,trainersID);
            preparedStatement.setString(2,appointmentDate);
            ResultSet resultSet =preparedStatement.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt(1);
                return count == 0;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


}
