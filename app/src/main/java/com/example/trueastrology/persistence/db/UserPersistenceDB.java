package com.example.trueastrology.persistence.db;

import android.util.Log;

import com.example.trueastrology.objects.StarSign;
import com.example.trueastrology.objects.User;
import com.example.trueastrology.persistence.IUserPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

// adapted from sample project
public class UserPersistenceDB implements IUserPersistence{
    private final String dbPath;

    public UserPersistenceDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private User fromResultSet(final ResultSet rs) throws SQLException {

        //creates a new starsign so it isn't linked with db star signs (not an issue as ss are never changed)
        final StarSign userStarSign = new StarSign(rs.getString("USERSTARSIGN"));
        final int userID = rs.getInt("USERID");
        final String name = rs.getString("NAME");
        final int loginCounter = rs.getInt("LOGINCOUNTER");
        final int predictionCounter = rs.getInt("PREDICTIONCOUNTER");
        final int preferredSeverity = rs.getInt("PREFERREDSEVERITY");
        User toReturn = new User(userStarSign,userID, name, loginCounter, predictionCounter);
        toReturn.setPreferredSeverity(preferredSeverity);

        return toReturn;
    }

    @Override
    public User getUser() {
        User user = null;

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM USERS WHERE USERID=?");
            st.setInt(1, 0);

            final ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                user = fromResultSet(rs);
            }
            rs.close();
            st.close();

            return user;
        }
        catch (final SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User updateUser(User updatedUser) {

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("UPDATE USERS SET USERSTARSIGN = ?, NAME = ?, LOGINCOUNTER = ?, PREDICTIONCOUNTER = ?, PREFERREDSEVERITY= ? WHERE USERID = 0");
            st.setString(1, updatedUser.getUserStarSign().getSignName());
            st.setString(2, updatedUser.getName());
            st.setInt(3, updatedUser.getLogin());
            st.setInt(4, updatedUser.getPredictionCounter());
            st.setInt(5, updatedUser.getPreferredSeverity());
            st.executeUpdate();

            return updatedUser;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(){
        try(final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("DELETE FROM USERS WHERE USERID = 0");
            st.executeUpdate();
        }catch(final SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void insertUser(User toAdd){
        try(final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("INSERT INTO USERS VALUES(?, ?, ?, ?, ? WHERE USERID = 0");
            if(this.getUser() == null){
                st.setString(1, toAdd.getUserStarSign().getSignName());
                st.setString(2, toAdd.getName());
                st.setInt(3, toAdd.getLogin());
                st.setInt(4, toAdd.getPredictionCounter());
                st.setInt(5, toAdd.getPreferredSeverity());
                st.executeUpdate();
            }
        }catch(final SQLException e){
            throw new RuntimeException(e);
        }
    }
}
