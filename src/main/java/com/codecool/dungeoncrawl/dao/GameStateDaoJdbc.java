package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameStateDaoJdbc implements GameStateDao {
    private DataSource dataSource;

    public GameStateDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(GameState state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (map_filename, current_map, saved_at, player_id) VALUES (?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, state.getMapFilename());
            statement.setInt(2, state.getCurrentMap());
            statement.setDate(3, state.getSavedAt());
            statement.setInt(4, state.getPlayer().getPlayerId());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            state.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(GameState state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE game_state " +
                    "SET map_filename=?, current_map=?, saved_at=?, player_id=?" +
                    "WHERE game_state.id=?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, state.getMapFilename());
            statement.setInt(2, state.getCurrentMap());
            statement.setDate(3, state.getSavedAt());
            statement.setInt(4, state.getPlayer().getPlayerId());
            statement.setInt(5, state.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameState get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT gs.id, gs.map_filename, gs.current_map, gs.saved_at, " +
                         "p.id, p.player_name, p.hp, p.damage, p.armor, p.x, p.y, p.inventory" +
                         " FROM game_state gs INNER JOIN player p ON p.id = gs.player_id WHERE gs.id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return convertToGameStates(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<GameState> getAll(int playerId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM game_state gs INNER JOIN player p ON p.id = gs.player_id WHERE p.id=? ORDER BY saved_at DESC";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, playerId);
            ResultSet resultSet = statement.executeQuery();
            return convertToGameStates(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<GameState> convertToGameStates(ResultSet resultSet) throws SQLException {
        List<GameState> result = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String mapFilename = resultSet.getString(2);
            int currentMap = resultSet.getInt(3);
            Date savedAt = resultSet.getDate(4);
            int playerId = resultSet.getInt(5);
            String playerName = resultSet.getString(7);
            int hp = resultSet.getInt(8);
            int damage = resultSet.getInt(9);
            int armor = resultSet.getInt(10);
            int x = resultSet.getInt(11);
            int y = resultSet.getInt(12);
            String inventory = resultSet.getString(13);
            PlayerModel player = new PlayerModel(playerId, playerName, hp, damage, armor, x, y, inventory);

            result.add(new GameState(id, mapFilename, currentMap, savedAt, player));
        }
        return result;
    }
}
