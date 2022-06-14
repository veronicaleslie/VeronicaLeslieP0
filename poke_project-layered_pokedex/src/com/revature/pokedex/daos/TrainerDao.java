package com.revature.pokedex.daos;

import com.revature.pokedex.models.Trainer;
import com.revature.pokedex.util.ConnectionFactory;

import java.io.*;
import java.sql.*;

public class TrainerDao implements com.revature.pokedex.daos.Crudable<Trainer> {

    @Override
    public Trainer create(Trainer newTrainer) {
        System.out.println("Here is the newTrainer as it enters our DAO layer: "+ newTrainer); // What happens here? Java knows to invoke the toString() method when printing the object to the terminal

        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {

            // NEVER EVER EVER EVER EVER concatenate or directly use these strings inside of the sql statement
            // String sql = "insert into trainer value (" + newTrainer.getFname() + "," + newTrainer.getLname();

            // The commented out sql String is using default for auto-generating the ID ifyou used serial
            // String sql = "insert into trainer values (default, ?, ?, ?, ?, ?)"; // incomplete sql statement, with default if not specifiying columns
            String sql = "insert into trainer (fname, lname, email, password, dob) values (?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            System.out.println(newTrainer.getFname());
            System.out.println(newTrainer.getLname());

            // 1-indexed, so first ? starts are 1
            ps.setString(1, newTrainer.getFname());
            ps.setString(2, newTrainer.getLname());
            ps.setString(3, newTrainer.getEmail());
            ps.setString(4, newTrainer.getPassword());
            ps.setString(5, newTrainer.getDob());

            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0){
                throw new RuntimeException();
            }

        } catch (SQLException | RuntimeException e){
            e.printStackTrace();
            return null;
        }
        return newTrainer;
    }

    @Override
    public Trainer[] findAll() throws IOException {

        Trainer[] trainers = new Trainer[10];
        int index = 0; // we want to keep track of where we are placing each trainer from the file into the the array

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) { // try with resoruces, because Connection extends the interface Auto-Closeable

            String sql = "select * from trainer";
            Statement s = conn.createStatement();

        // conn.createStatement().executeQuery("select * from trainer"); fine but generally not used
            ResultSet rs =s.executeQuery(sql);

            while (rs.next()) { // the last line of the file is null
                Trainer trainer = new Trainer();

                trainer.setFname(rs.getString("fname")); // this column lable MUST MATCH THE DB
                trainer.setLname(rs.getString("lname"));
                trainer.setDob(rs.getString("dob"));
                trainer.setPassword(rs.getString("password"));
                trainer.setEmail(rs.getString("email"));

                System.out.println("Inserted trainer into index" + index);
                trainers[index] = trainer;
                index++; // increment the index by 1, must occur after the trainer[index] re-assignment
                System.out.println("Going to the next line for our following index.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }



        System.out.println("Returning trainers infomation to user.");
        return trainers;
    }

    @Override
    public Trainer findById(String id) {


        try(Connection conn = ConnectionFactory.getInstance().getConnection();){

            String sql = "select * from trainer where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id)); // Wrapper class example

            ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords

            Trainer trainer = new Trainer();

            trainer.setFname(rs.getString("fname")); // this column lable MUST MATCH THE DB
            trainer.setLname(rs.getString("lname"));
            trainer.setDob(rs.getString("dob"));
            trainer.setPassword(rs.getString("password"));
            trainer.setEmail(rs.getString("email"));

            return trainer;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean update(Trainer updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    public void checkEmail(String email) {
        String sql = "select email from trainer where email = " + email; // issues with SQL injection
    }
}
