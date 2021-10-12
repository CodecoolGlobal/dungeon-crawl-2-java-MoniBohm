package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapObject.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.util.MiscUtilHelper;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameStateDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("dbName");
        String user = System.getenv("Username");
        String password = System.getenv("Password");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");
        return dataSource;
    }

    public void saveGame(String saveName, GameMap map, String mapFilename, int currentMap) {
        Player player = map.getPlayer();
        saveGameMap(saveName ,mapFilename, currentMap, player, map);
    }

    public void saveGameMap(String saveName, String mapFilename, int currentMap, Player player, GameMap map) {
        Timestamp date = MiscUtilHelper.getDate();
        GameState gameState = new GameState(saveName, mapFilename, currentMap, date, player, map);
        int saveId = gameStateAlreadyInDatabase(saveName);
        if (saveId == 0) {
            savePlayer(player);
            gameStateDao.add(gameState);
        } else {
            gameState.setId(saveId);
            savePlayer(player);
            gameStateDao.update(gameState);
        }

    }


    public GameState getGameStateFromDb(int gameId) {
        return gameStateDao.get(gameId);
    }

    private int gameStateAlreadyInDatabase(String saveName) {
        return gameStateDao.isSavenameInDb(saveName);
    }


    public void savePlayer(Player player) {
        if (playerAlreadyInDatabase(player.getId())) {
            playerDao.update(player);
        } else {
            playerDao.add(player);
        }
    }

    private boolean playerAlreadyInDatabase(int playerHash) {
        return playerDao.isPlayerInDb(playerHash);
    }

    public PlayerDao getPlayerDao() {
        return playerDao;
    }

    public GameStateDao getGameStateDao() {
        return gameStateDao;
    }
}
