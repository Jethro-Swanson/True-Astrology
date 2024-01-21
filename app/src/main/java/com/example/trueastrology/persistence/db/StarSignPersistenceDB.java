package com.example.trueastrology.persistence.db;

import android.util.Log;

import com.example.trueastrology.objects.Prediction;
import com.example.trueastrology.objects.StarSign;
import com.example.trueastrology.persistence.IStarSignPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// adapted from sample project
public class StarSignPersistenceDB implements IStarSignPersistence{
    private final String dbPath;

    public StarSignPersistenceDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private StarSign fromResultSet(final ResultSet rs) throws SQLException {
        final String signName = rs.getString("SIGNNAME");
        final int starID=rs.getInt("ID");
        return new StarSign(signName,starID);
    }

    @Override
    public ArrayList<StarSign> getStarSignList() {
        final ArrayList<StarSign> starSigns = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM STARSIGNS");
            while (rs.next())
            {
                final StarSign starSign = fromResultSet(rs);
                starSigns.add(starSign);
            }
            rs.close();
            st.close();

            return starSigns;
        }
        catch (final SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StarSign getStarSign(String signName) {
        StarSign starSign = null;

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM STARSIGNS WHERE SIGNNAME=?");
            st.setString(1, signName);

            final ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                starSign = fromResultSet(rs);
            }
            rs.close();
            st.close();

            return starSign;
        }
        catch (final SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void deleteStarSign(StarSign toDelete){
        try(final Connection c = connection()){
            final PreparedStatement st= c.prepareStatement("DELETE FROM STARSIGNS WHERE ID = ?");
            if(this.getStarSign(toDelete.getSignName()) != null){
                st.setInt(1, toDelete.getStarID());
                st.executeUpdate();
            }
        }catch (final SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void insertStarSign(StarSign toAdd){
        try(final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("INSERT INTO STARSIGNS VALUES (?, ?)");
            if(this.getStarSign(toAdd.getSignName()) == null){
                st.setString(1, toAdd.getSignName());
                st.setInt(2, toAdd.getStarID());
                st.executeUpdate();
            }
        }catch (final SQLException e){
            throw new RuntimeException(e);
        }
    }
}
