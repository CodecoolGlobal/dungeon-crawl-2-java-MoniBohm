package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapObject.actors.Player;
import com.codecool.dungeoncrawl.model.BaseModel;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.codecool.dungeoncrawl.util.MiscUtilHelper;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Date;

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
        PlayerModel playerModel = new PlayerModel(player);
        savePlayer(playerModel);
        saveGameMap(saveName ,mapFilename, currentMap, playerModel);
    }

    public void saveGameMap(String saveName, String mapFilename, int currentMap, PlayerModel playerModel) {
        Date date = MiscUtilHelper.getDate();
        GameState model = new GameState(saveName, mapFilename, currentMap, date, playerModel);
        gameStateDao.add(model);

    }


    public void savePlayer(PlayerModel playerModel) {
        if (playerAlreadyInDatabase(playerModel.getPlayerId())) {
            playerDao.update(playerModel);
        } else {
            playerDao.add(playerModel);
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
