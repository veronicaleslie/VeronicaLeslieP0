package com.revature.bankapp.daos;

import com.revature.bankapp.exceptions.ResourcePersistanceException;
import com.revature.bankapp.model.Users;
import com.revature.bankapp.util.ConnectionFactory;
import com.revature.bankapp.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.*;

public class UserDao implements com.revature.bankapp.daos.Crudable<Users> {
    private Logger logger = Logger.getLogger();

    @Override
    public Users create(Users newUser) {
        System.out.println("Here is the newUser as it enters our DAO layer: "+ newUser); // What happens here? Java knows to invoke the toString() method when printing the object to the terminal

        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {

            // NEVER EVER EVER EVER EVER concatenate or directly use these strings inside of the sql statement
            // String sql = "insert into trainer value (" + newTrainer.getFname() + "," + newTrainer.getLname();

            // The commented out sql String is using default for auto-generating the ID ifyou used serial
            // String sql = "insert into trainer values (default, ?, ?, ?, ?, ?)"; // incomplete sql statement, with default if not specifiying columns
            String sql = "insert into User (first_name,last_, email, password, username (?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            System.out.println(newUser.getFirst_name());
            System.out.println(newUser.getLast_name());

            // 1-indexed, so first ? starts are 1
            ps.setString(1, newUser.getFirst_name());
            ps.setString(2, newUser.getLast_name());
            ps.setString(3, newUser.getEmail());
            ps.setString(4, newUser.getPassword());
            ps.setString(5, newUser.getUsername());

            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0){
                throw new ResourcePersistanceException("User was not entered into database due to some issue.");
            }

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        } catch (ResourcePersistanceException e) {
            throw new RuntimeException(e);
        }
        return newUser;
    }

    //@Override
    @Override
    public Users[] findAll() throws IOException {

        // making an array of Trainer classes, and seetting it to a max size of 10
        Users[] users = new Users[10];
        // declaring index variable as an int and intiliazation witht he value of 0
        int index = 0; // we want to keep track of where we are placing each trainer from the file into the the array

        // TODO: we trying something here and passing an argumetn???
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) { // try with resoruces, because Connection extends the interface Auto-Closeable

            String sql = "select * from user_account";
            Statement s = conn.createStatement();

            // conn.createStatement().executeQuery("select * from trainer"); fine but generally not used
            // TODO: Hey why are we using the sql variable string here?
            ResultSet rs =s.executeQuery(sql);

            while (rs.next()) { // the last line of the file is null
                Users user = new Users();

                user.setFirst_name(rs.getString("first_name")); // this column lable MUST MATCH THE DB
                user.setLast_name(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));

                System.out.println("Inserted trainer into index" + index);
                users[index] = user;
                index++; // increment the index by 1, must occur after the trainer[index] re-assignment
                System.out.println("Going to the next line for our following index.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }



        System.out.println("Returning users infomation to user.");
        return users;
    }

    @Override
    public Object findById(String id) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection();){

            String sql = "select * from user_account where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id)); // Wrapper class example

            ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords

            if(!rs.next()) {
                Users user;
                throw new ResourcePersistanceException("User was not found in the database, please check ID entered was correct user.");
            }

            Users user = new Users();

            user.setFirst_name(rs.getString("first_name")); // this column lable MUST MATCH THE DB
            user.setLast_name(rs.getString("last_name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));

            return user;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        } catch (ResourcePersistanceException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean update(Users updatedObj) {return false;}
    //@Override
    @Override
    public boolean delete(String id) {
        return false;
    }


    public static Users authenticateUser(String email, String password){

        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from user_account where email = ? and password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(!rs.next()){
                return null;
            }

            Users user = new Users();

            user.setFirst_name(rs.getString("first_name")); // this column lable MUST MATCH THE DB
            user.setLast_name(rs.getString("last_name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));

            return user;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }


    }
    @Override
    public boolean checkEmail(String email) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select email from User where email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if(!rs.isBeforeFirst()){
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}