package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.actors.Player;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerDaoJdbc implements PlayerDao {
    private DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Player player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO player (id, player_name, hp, damage, armor, cell, inventory)" +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, player.getId());
            statement.setString(2, player.getName());
            statement.setInt(3, player.getHp());
            statement.setInt(4, player.getDamage());
            statement.setInt(5, player.getArmor());
            statement.setBytes(6, Objects.requireNonNull(player.getCellSerialized().orElse(null)).toByteArray());
            statement.setBytes(7, Objects.requireNonNull(player.getInventorySerialized().orElse(null)).toByteArray());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Player player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE player " +
                    "SET player_name=?, hp=?, damage=?, armor=?, cell=?, inventory=?" +
                    "WHERE player.id=?";

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, player.getName());
            statement.setInt(2, player.getHp());
            statement.setInt(3, player.getDamage());
            statement.setInt(4, player.getArmor());
            statement.setBytes(5, Objects.requireNonNull(player.getCellSerialized().orElse(null)).toByteArray());
            statement.setBytes(6, Objects.requireNonNull(player.getInventorySerialized().orElse(null)).toByteArray());
            statement.setInt(7, player.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean isPlayerInDb(int hashcode) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * " +
                    "FROM player " +
                    "WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, hashcode);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Player get(int id) {
        try (Connection conn = dataSource.getConnection()) {
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
    public List<Player> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * " +
                    "FROM player " +
                    "ORDER BY player_name";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            return convertToPlayerModel(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Player> convertToPlayerModel(ResultSet resultSet) throws SQLException {
        List<Player> result = new ArrayList<>();
        while (resultSet.next()) {
            int playerId = resultSet.getInt(1);
            String playerName = resultSet.getString(2);
            int hp = resultSet.getInt(3);
            int damage = resultSet.getInt(4);
            int armor = resultSet.getInt(5);

            byte[] cellBytes = resultSet.getBytes(6);
            Cell cell = Player.getCellDeserialized(cellBytes);

            byte[] inventoryBytes = resultSet.getBytes(7);
            List<Item> inventory = Player.getInventoryDeserialized(inventoryBytes);

            for (Item item : inventory) {
                System.out.println(item);
            }
            result.add(new Player(playerId, playerName, hp, damage, armor, cell, inventory));
        }
        return result;
    }
}
