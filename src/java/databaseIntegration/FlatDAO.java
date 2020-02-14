/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseIntegration;

import jpa.entities.Flat;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

/**
 *
 * @author Michal
 */
public class FlatDAO {

    private static int ID;

    public static void AddFlat(Flat flat) {
        String name = flat.getName();
        String desc = flat.getDescriprion();
        //Part awatar = flat.getImages();
        Statement stmt = null;
        Connection con = null;
        InputStream inputStream = null; // input stream of the upload file

        try {
            con = DataConnect.getConnectionToDatabase();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String query = "INSERT INTO FLATSTABLE (ID, NAME, DESCRIPTION, IMAGE)"
                    + " VALUES (?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setInt(1, ID);
            pstmt.setString(2, name);
            pstmt.setString(3, desc);
            pstmt.setBlob(4, inputStream);

            pstmt.execute();

        } catch (SQLException ex) {
            System.out.println("FlatsAdder error -->" + ex);
        } finally {
            DataConnect.close(con);
        }

    }

    public static Flat[] GetFlats(String que) {
        Statement stmt = null;
        Connection con = null;

        try {
            con = DataConnect.getConnectionToDatabase();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = (que == null || que.equals("") ? "*" : que);
            ResultSet rs = stmt.executeQuery("SELECT " + query + " FROM FLATSTABLE");

            int rows = GetRows(query);

            //
            //TODO return!!!
            //
            if (rs.next()) {
                String name = rs.getString("NAME");
                String desc = rs.getString("DESCRIPTION");
                Blob blob = rs.getBlob("IMAGE");
                String[] names = name.split(" ");
                String[] descs = desc.split(" ");
                System.out.println("\n" + names.length + "\n\n" + desc + "\n\n");
            }

        } catch (SQLException ex) {
            System.out.println("FlatsGetter error -->" + ex.getMessage());

        } finally {
            DataConnect.close(con);
        }
        return null;

    }

    public static int GetRows(String query) {
        Statement stmt = null;
        Connection con = null;
        try {
            con = DataConnect.getConnectionToDatabase();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = stmt.executeQuery("SELECT COUNT(" + query + ") FROM FLATSTABLE");
            if (rs.next()) {
                return rs.getInt("1");
            }

        } catch (SQLException ex) {
            System.out.println("FlatsGetterRows error -->" + ex);
        } finally {
            DataConnect.close(con);
        }
        return -1;
    }

    public static int getID() {
        return ID;
    }

    public static void setID(int aID) {
        ID = aID;
    }
}
