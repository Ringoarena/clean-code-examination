package solution1.integration;

import solution1.business.PlayerAverage;
import solution1.business.User;

import java.sql.*;
import java.util.*;

public class DAOImpl implements DAO {
    Connection connection;
    PreparedStatement login, postResult, getTopTen;
    Map<String, String> gameTable = new HashMap<>();
    String gameTitle;

    public DAOImpl() {
        gameTable.put("bc", "bcresults");
        gameTable.put("hilo", "hiloresults");

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/cleancodeexam", "root", "root");
            login = connection.prepareStatement("SELECT * FROM players WHERE name = ?");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> loginByName(String name) {
        Optional<User> user = Optional.empty();
        try {
            login.setString(1, name);
            ResultSet rs = login.executeQuery();
            if (rs.next()) {
                User foundUser = new User(rs.getString("name"), rs.getInt("id"));
                user = Optional.of(foundUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void postResult(int result, int playerId) {
        String queryString = "INSERT INTO " + gameTable.get(gameTitle) + " (result, player) VALUES(?,?)";
        try {
            postResult = connection.prepareStatement(queryString);
            postResult.setInt(1, result);
            postResult.setInt(2, playerId);
            postResult.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PlayerAverage> getTopTen() {
        List<PlayerAverage> list = new ArrayList<>();
        String queryString = "SELECT name, AVG(result) as Average " +
                "FROM players " +
                "JOIN " + gameTable.get(gameTitle) + " ON players.id = " + gameTable.get(gameTitle) + ".player " +
                "WHERE result > 0 " +
                "GROUP BY players.id " +
                "ORDER BY Average LIMIT 10";
        try {
            getTopTen = connection.prepareStatement(queryString);
            ResultSet rs = getTopTen.executeQuery();
            while (rs.next()) {
                list.add(new PlayerAverage(rs.getString(1), rs.getDouble(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }
}
