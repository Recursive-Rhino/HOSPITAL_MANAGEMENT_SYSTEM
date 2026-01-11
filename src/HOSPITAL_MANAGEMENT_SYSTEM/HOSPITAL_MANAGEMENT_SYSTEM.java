package HOSPITAL_MANAGEMENT_SYSTEM;

import java.sql.*;
import java.util.Scanner;

import static java.lang.System.exit;

public class HOSPITAL_MANAGEMENT_SYSTEM {
    private static  final String url="jdbc:mysql://localhost:3306/hospital";
    private static  final String username="root";
    private static  final String password="DBMS@#2965&Smruti";

    static void main() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner input=new Scanner(System.in);
        try{
            Connection con=DriverManager.getConnection(url,username,password);
            Doctor d1=new Doctor(con);
            Patients p1=new Patients(con,input);
            while(true) {
                System.out.println("WELCOME TO HOSPITAL MANAGEMENT SYSTEM : ");
                System.out.println("1. ADD PATIENT");
                System.out.println("2. VIEW PATIENT");
                System.out.println("3. VIEW DOCTOR");
                System.out.println("4. BOOK APPOINTMENT");
                System.out.println("5. EXIT");
                System.out.println("===========================================/n");
                System.out.print("ENTER YOUR CHOICE : ");
                int choice=input.nextInt();
                switch(choice) {
                    case 1 :
                        p1.addPatient();
                        System.out.println();
                        break;
                    case 2 :
                        p1.viewPatients();
                        System.out.println();
                        break;
                    case 3 :
                        d1.viewDoctors();
                        System.out.println();
                        break;
                    case 4:
                        bookAppointment(p1,d1,con,input);
                        System.out.println();
                        break;
                    case 5:
                        System.out.println("THANK YOU FOR VISITING");
                        exit(0);
                        break;
                    default:
                        System.out.println("ENTER A VALID NUMBER ....");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void bookAppointment(Patients patient, Doctor doctor, Connection connection, Scanner scanner){
        System.out.print("Enter Patient Id: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Doctor Id: ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String appointmentDate = scanner.next();
        if(patient.getPatientData(patientId) && doctor.getDocById(doctorId)){
            if(checkDoctorAvailability(doctorId, appointmentDate, connection)){
                String appointmentQuery = "INSERT INTO appointments(p_id, doctor_id, appo_date) VALUES(?, ?, ?)";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1, patientId);
                    preparedStatement.setInt(2, doctorId);
                    preparedStatement.setString(3, appointmentDate);
                    int rowsAffected = preparedStatement.executeUpdate();
                    if(rowsAffected>0){
                        System.out.println("Appointment Booked!");
                    }else{
                        System.out.println("Failed to Book Appointment!");
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("Doctor not available on this date!!");
            }
        }else{
            System.out.println("Either doctor or patient doesn't exist!!!");
        }
    }

    public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection){
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appo_date = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointmentDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt(1);
                if(count==0){
                    return true;
                }else{
                    return false;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}

