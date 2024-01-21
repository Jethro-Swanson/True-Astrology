package com.example.trueastrology.persistence.db;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.example.trueastrology.objects.Prediction;
import com.example.trueastrology.persistence.IPredictionPersistence;

// adapted from sample project
public class PredictionPersistenceDB implements IPredictionPersistence{
    private final String dbPath;

    public PredictionPersistenceDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Prediction fromResultSet(final ResultSet rs) throws SQLException {
        final int ID = rs.getInt("ID");
        final String predictionText = rs.getString("PREDICTIONTEXT");
        final int severity = rs.getInt("SEVERITY");
        return new Prediction(ID, predictionText, severity);
    }

    @Override
    public ArrayList<Prediction> getPredictionList() {
        final ArrayList<Prediction> predictions = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM PREDICTIONS");
            while (rs.next())
            {
                final Prediction prediction = fromResultSet(rs);
                predictions.add(prediction);
            }
            rs.close();
            st.close();

            return predictions;
        }
        catch (final SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Prediction getPrediction(int ID) {
        Prediction prediction = null;

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM PREDICTIONS WHERE ID=?");
            st.setInt(1, ID);

            final ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                prediction = fromResultSet(rs);
            }
            rs.close();
            st.close();

            return prediction;
        }
        catch (final SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addPrediction(Prediction prediction) {

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO predictions VALUES(?, ?, ?)");
            st.setInt(1, prediction.getID());
            st.setString(2, prediction.getText());
            st.setInt(3, prediction.getSeverity());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePrediction(int ID) {

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM predictions WHERE ID = ?");
            st.setInt(1, ID);
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
