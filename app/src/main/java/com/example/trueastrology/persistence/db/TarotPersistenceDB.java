package com.example.trueastrology.persistence.db;

import com.example.trueastrology.objects.Tarot;
import com.example.trueastrology.persistence.ITarotPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TarotPersistenceDB implements ITarotPersistence {
    private final String dbPath;

    public TarotPersistenceDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Tarot fromResultSet(final ResultSet rs) throws SQLException {
        final int cardNum = rs.getInt("CARDNUM");
        final String cardName = rs.getString("CARDNAME");
        final String tarotText = rs.getString("tarotText");

        return new Tarot(cardNum, cardName, tarotText);
    }

    @Override
    public ArrayList<Tarot> getTarotList() {
        final ArrayList<Tarot> tarotCards = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM TAROT");
            while (rs.next())
            {
                final Tarot tarotCard = fromResultSet(rs);
                tarotCards.add(tarotCard);
            }
            rs.close();
            st.close();

            return tarotCards;
        }
        catch (final SQLException e)
        {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Tarot getTarotCard(String cardName) {
        Tarot tarotCard = null;

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM TAROT WHERE CARDNAME=?");
            st.setString(1, cardName);

            final ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                tarotCard = fromResultSet(rs);
            }
            rs.close();
            st.close();

            return tarotCard;
        }
        catch (final SQLException e)
        {
            throw new RuntimeException(e);
        }
    }


    public void deleteTarot(Tarot toDelete) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM TAROT WHERE CARDNUM = ?");
            st.setInt(1, toDelete.getCardNum());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll(ArrayList<Tarot> list){
        for(int i=0;i<list.size();i++){
            deleteTarot(list.get(i));
        }
    }

    public void insertAll(ArrayList<Tarot> list){
        for(int i=0;i<list.size();i++){
            insertTarot(list.get(i));
        }
    }

    public void insertTarot(Tarot toAdd){
        try(final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("INSERT INTO TAROT VALUES (?, ?, ?)");
            if(this.getTarotCard(toAdd.getCardName()) == null) {
                st.setInt(1, toAdd.getCardNum());
                st.setString(2, toAdd.getCardName());
                st.setString(3, toAdd.getTarotText());
                st.executeUpdate();
            }
        }catch (final SQLException e){
            throw new RuntimeException(e);
        }
    }
}
