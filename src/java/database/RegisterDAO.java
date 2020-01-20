/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.pepek.misc.Utilieties;
import java.awt.Image;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Michal
 */
public class RegisterDAO {

    private String dbName;
    private Connection con;
    private String dbms;

    public RegisterDAO(Connection conn, String dbNameArg, String dbmsArg) {
        super();
        this.con = conn;
        this.dbName = dbNameArg;
        this.dbms = dbmsArg;
    }

    public static boolean InsertRegister(String user, String password, String email, int telefon, Date date,
            Utilieties.Sex plec, String adres, Blob awatar) {
        Statement stmt = null;
        Connection con = null;

        try {
            con = DataConnect.getConnectionToDatabase();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");

            rs.moveToInsertRow();

            rs.updateString("USERNAME", user);
            rs.updateString("PASSWORD", password);
            rs.updateString("EMAIL", email);
            rs.updateInt("NUMBER", telefon);
            rs.updateString("ADDRESS", adres);
            rs.updateDate("DATE", date);
            rs.updateString("SEX", plec.toString());
            rs.updateBlob("AVATAR", awatar);
            
            rs.insertRow();
            rs.beforeFirst();

        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }
        return false;
    }
}
