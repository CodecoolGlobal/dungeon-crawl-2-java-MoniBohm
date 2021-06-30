package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameStateDaoJdbc implements GameStateDao {
    private DataSource dataSource;

    public GameStateDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(GameState state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (save_name, map_filename, map, current_map, saved_at, player_id) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, state.getSaveName());
            statement.setString(2, state.getMapFilename());
            statement.setBytes(3, Objects.requireNonNull(state.getMapSerialized().orElse(null)).toByteArray());
            statement.setInt(4, state.getCurrentMap());
            statement.setTimestamp(5, state.getSavedAt());
            statement.setInt(6, state.getPlayerModel().getId());

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
                    "SET save_name=?, map_filename=?, current_map=?, map=?, saved_at=?, player_id=?" +
                    "WHERE game_state.id=?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, state.getSaveName());
            statement.setString(2, state.getMapFilename());
            statement.setInt(3, state.getCurrentMap());
            statement.setBytes(4, Objects.requireNonNull(state.getMapSerialized().orElse(null)).toByteArray());
            statement.setTimestamp(5, state.getSavedAt());
            statement.setInt(6, state.getPlayerModel().getId());
            statement.setInt(7, state.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameState get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = """
                         SELECT gs.id,
                                gs.save_name,
                                gs.map_filename,
                                gs.current_map,
                                gs.map,
                                gs.saved_at,
                                p.id,
                                p.player_name,
                                p.hp,
                                p.damage,
                                p.armor,
                                p.x,
                                p.y,
                                p.inventory
                         FROM game_state gs
                         INNER JOIN player p
                            ON p.id = gs.player_id
                         WHERE gs.id=?
                         """;
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
            String sql = """
                            SELECT gs.id,
                                gs.save_name,
                                gs.map_filename,
                                gs.current_map,
                                gs.map,
                                gs.saved_at,
                                p.id,
                                p.player_name,
                                p.hp,
                                p.damage,
                                p.armor,
                                p.x,
                                p.y,
                                p.inventory
                            FROM game_state gs
                            INNER JOIN player p
                                ON p.id = gs.player_id
                            WHERE p.id=?
                            ORDER BY saved_at
                            DESC""";
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
            String saveName = resultSet.getString(2);
            String mapFilename = resultSet.getString(3);
            int currentMap = resultSet.getInt(4);
            byte[] mapBytes = resultSet.getBytes(5);
            GameMap map = GameState.getMapDeserialized(mapBytes);
            Timestamp savedAt = resultSet.getTimestamp(6);


            int playerId = resultSet.getInt(7);
            String playerName = resultSet.getString(8);
            int hp = resultSet.getInt(9);
            int damage = resultSet.getInt(10);
            int armor = resultSet.getInt(11);
            int x = resultSet.getInt(12);
            int y = resultSet.getInt(13);
            byte[] inventoryBytes = resultSet.getBytes(14);
            List<Item> inventory = PlayerModel.getInventoryDeserialized(inventoryBytes);
            PlayerModel player = new PlayerModel(playerId, playerName, hp, damage, armor, x, y, inventory);
            result.add(new GameState(id, saveName, mapFilename, currentMap, savedAt, player, map));
        }
        return result;
    }
}
