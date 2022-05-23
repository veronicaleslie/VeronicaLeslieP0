package com.revature.bankapp.daos;

import com.revature.bankapp.exceptions.ResourcePersistanceException;
import com.revature.bankapp.model.Account;
import com.revature.bankapp.util.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class AccountDao implements Crudable<Account> {


    @Override
    public Account create(Account newAccount) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "insert into banking_accounts values (default,?,?,?);"; // incomplete sql statement

            PreparedStatement ps = conn.prepareStatement(sql);

            // 1-indexed, so first ? starts are 1
            ps.setInt(1, newAccount.getAccountID());
            ps.setString(2, newAccount.getUsername());
            ps.setString(3, newAccount.getAccountName());

            int checkInsert = ps.executeUpdate();
            if (checkInsert == 0) {
                throw new ResourcePersistanceException("Account not created issue found.");
            }

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return null;
        } catch (ResourcePersistanceException e) {
            throw new RuntimeException(e);
        }
        return newAccount;
    }


    @Override
    public List<Account> findAll() throws IOException {
        return Arrays.asList(new Account[0]);
    }

    public Account[] findAll(String email) throws IOException {

        Account[] userAccounts = new Account[10];
        int index = 0;

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) { // try with resources, because Connection extends the interface Auto-Closeable

            String sql = "select * from banking_accounts where username=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) { // the last line of the file is null
                Account account = new Account();

                account.setAccountID(rs.getInt("id")); // this column label MUST MATCH THE DB
                account.setUsername(rs.getString("username"));
                account.setAccountName(rs.getString("account_type"));
                account.setBalance(rs.getInt("account_balance"));

                userAccounts[index] = account;
                index++;
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
            return null;
        }
        return userAccounts;
    }

    @Override
    public Account findById(String id) {

        Account account;
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from banking_accounts where id =?";


            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id)); // Wrapper class example
            ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords
            System.out.println(ps);

            if (rs.next()) {
             Account accounts = new Account();


            accounts.setAccountID(rs.getInt("id")); // this column label MUST MATCH THE DB
            accounts.setUsername(rs.getString("username"));
            accounts.setAccountName(rs.getString("account_type"));
            accounts.setBalance(rs.getInt("account_balance"));

            return accounts;
        }else throw new ResourcePersistanceException("User could not be found. Please try again.");
        }

            catch (ResourcePersistanceException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


    }

    @Override
    public boolean update(Account updatedObj) {
        return false;

        }
    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean checkEmail(String email) {
        return false;
    }


    @Override
        public void deposit (String amount, String id){
            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

                String sql = "update banking_accounts set account_balance = account_balance + ? where username  = '?'";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(amount));
                ps.setInt(1, Integer.parseInt(id));
                int rs = ps.executeUpdate();
                System.out.println("transaction complete" + amount + "has been inserted into your account");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }




}