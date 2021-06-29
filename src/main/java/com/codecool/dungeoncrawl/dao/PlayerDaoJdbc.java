package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO player (id, player_name, hp, x, y) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            System.out.println(player.getPlayerHash());
            statement.setInt(1, player.getPlayerHash());
            statement.setString(2, player.getPlayerName());
            statement.setInt(3, player.getHp());
            statement.setInt(4, player.getX());
            statement.setInt(5, player.getY());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()){
            String sql = "UPDATE player " +
                         "SET player_name=?, hp=?, x=?, y=? " +
                         "WHERE player.id=?";

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getHp());
            statement.setInt(3, player.getX());
            statement.setInt(4, player.getY());
            statement.setInt(5, player.getPlayerHash());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean isPlayerInDb(int hashcode) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM player WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, hashcode);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PlayerModel get(int id) {
        try (Connection conn = dataSource.getConnection()){
            String sql = "SELECT * " +
                         "FROM player " +
                         "WHERE id=? " +
                         "ORDER BY player_name";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return convertToPlayerModel(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PlayerModel> getAll() {
        try (Connection conn = dataSource.getConnection()){
            String sql = "SELECT * FROM player ORDER BY player_name";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            return convertToPlayerModel(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<PlayerModel> convertToPlayerModel(ResultSet resultSet) throws SQLException {
        List result = new ArrayList();
        while(resultSet.next()){
            int playerId = resultSet.getInt(1);
            String playerName = resultSet.getString(2);
            int hp = resultSet.getInt(3);
            int x = resultSet.getInt(4);
            int y = resultSet.getInt(5);
            result.add(new PlayerModel(playerId, playerName, hp, x, y));
        }
        return result;
    }
}
