package com.revature.bankapp.daos;


import com.revature.bankapp.model.Account;
import com.revature.bankapp.model.Users;
import com.revature.bankapp.util.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDao {
    private int index;

    public static boolean update(String id2, String newAccount) {
        return false;
    }

    public static Account deposit(String value, String id) {
        return null;
    }

    public static Account withdraw(String value, String id) {
        return null;
    }

    public com.revature.bankapp.model.Account create(com.revature.bankapp.model.Account newAccount) {
        return newAccount;
    }

    public com.revature.bankapp.model.Account[] findAll(String email) {
        return new Account[0];
    }

    public com.revature.bankapp.model.Account findById(String id) {
        return null;
    }

    public Account readAccount(String email) {
        return null;
    }

    public Account showBalance() throws IOException {


        try (Connection conn = ConnectionFactory.getInstance().getConnection();) { // try with resoruces, because Connection extends the interface Auto-Closeable

            String sql = "select * from banking_accounts";
            Statement s = conn.createStatement();

            // conn.createStatement().executeQuery("select * from trainer"); fine but generally not used
            // TODO: Hey why are we using the sql variable string here?
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) { // the last line of the file is null
                Account account = new Account();

                account.Username(rs.getString("username")); // this column lable MUST MATCH THE DB
                account.setAccountName(rs.getString("account_type"));
                account.setBalance(Integer.parseInt(rs.getString("account_balance")));

                System.out.println("acquired account balance" + index);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }
}
