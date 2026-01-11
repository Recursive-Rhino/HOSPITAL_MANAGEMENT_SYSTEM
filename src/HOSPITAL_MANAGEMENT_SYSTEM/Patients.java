package HOSPITAL_MANAGEMENT_SYSTEM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patients {
    private Connection connection;
    private Scanner scanner;

    public Patients(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }
    public void addPatient() {
        System.out.println("ENTER PATIENT NAME");
        String name=scanner.next();
        System.out.println("ENTER THE PATIENT AGE : ");
        int age =scanner.nextInt();
        System.out.println("ENTER THE GENDER : ");
        String gender=scanner.next();
        try{
            String query="INSERT INTO PATIENTS(NAME,AGE,GENDER) VALUES(?,?,?)";
            PreparedStatement ps =connection.prepareStatement(query);
            ps.setString(1,name);
            ps.setInt(2,age);
            ps.setString(3,gender);
            int RowsAffected=ps.executeUpdate();
            if(RowsAffected>0) {
                System.out.println("PATIENT DATA INSERTED SUCCESSFULLY");
            } else {
                System.out.println("SORRY , TRY AGAIN LATER.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void viewPatients(){
        String query = "select * from patients";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patients: ");
            System.out.println("+------------+--------------------+----------+------------+");
            System.out.println("| Patient Id | Name               | Age      | Gender     |");
            System.out.println("+------------+--------------------+----------+------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("pid");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.printf("| %-10s | %-18s | %-8s | %-10s |\n", id, name, age, gender);
                System.out.println("+------------+--------------------+----------+------------+");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean getPatientData(int id){
        String query="SELECT * FROM patients WHERE pid=?";
        try {
            PreparedStatement ps= connection.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }
}
