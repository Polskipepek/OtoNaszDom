/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Michal
 */
public class LoginDAO {

    public static boolean validate(String user, String password) {
        if (user == null) {
            return false;
        }
        
        PreparedStatement ps = null;
        Connection con = null;

        try {
            con = DataConnect.getConnectionToDatabase();
            PreparedStatement pst = null;
            String sql = "Select USERNAME, PASSWORD from Users where USERNAME = ? and PASSWORD = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                //result found, means valid inputs
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }
        return false;
    }
}
