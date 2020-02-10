/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.pepek.misc.Utilieties;
import java.io.IOException;
import java.io.InputStream;
//import java.sql.Blob;
import javax.servlet.http.Part;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
    private static int ID;
    public static int tempID = -1;

    public RegisterDAO(Connection conn, String dbNameArg, String dbmsArg) {
        super();
        this.con = conn;
        this.dbName = dbNameArg;
        this.dbms = dbmsArg;
    }

    public static boolean InsertRegister(String user, String password, String email, int telefon, Date date,
            Utilieties.Sex plec, String adres, Part awatar) throws IOException, SQLException {

        InputStream inputStream = null; // input stream of the upload file

        if (awatar != null) {
            // prints out some information for debugging
            System.out.println(awatar.getName());

            // obtains input stream of the upload file
            inputStream = awatar.getInputStream();
        }

        Statement stmt = null;
        Statement stmtId = null;
        Connection con = null;

        try {
            con = DataConnect.getConnectionToDatabase();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmtId = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");

            ResultSet idS = stmt.executeQuery("SELECT COUNT(*) FROM USERS");
            idS.next();
            tempID = Integer.parseInt(idS.getString("1")) + 1;

            System.out.println("numerID: " + tempID);

            String query = "INSERT INTO USERS (ID, USERNAME, PASSWORD, EMAIL, NUMBER, ADDRESS, DATE, SEX, AVATAR)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setInt(1, tempID);
            pstmt.setString(2, user);
            pstmt.setString(3, password);
            pstmt.setString(4, email);
            pstmt.setInt(5, telefon);
            pstmt.setString(6, adres);
            pstmt.setDate(7, date);
            pstmt.setString(8, plec.toString());
            pstmt.setBlob(9, inputStream);

            pstmt.execute();

            return true;

        } catch (SQLException ex) {
            System.out.println("Register error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }

    }

}
