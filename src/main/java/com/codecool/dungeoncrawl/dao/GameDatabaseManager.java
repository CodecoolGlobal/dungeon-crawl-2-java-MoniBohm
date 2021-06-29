package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapObject.actors.Player;
import com.codecool.dungeoncrawl.model.BaseModel;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
        System.out.println(dataSource);
        return dataSource;
    }

    public void savePlayer(PlayerModel player) {
        playerDao.add(player);
    }

    public void saveGameMap(GameMap map, String mapFilename, PlayerModel player) {
//        java.sql.Date date=new java.sql.Date(millis);
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        GameState model = new GameState(mapFilename, date, player);
        gameStateDao.add(model);

    }

    public void saveGameState(GameMap map, String mapFilename, int currentMap) {
        Player player = map.getPlayer();
        PlayerModel playerModel = new PlayerModel(player);
        saveGameMap(map, mapFilename, playerModel);

        savePlayer(playerModel);
    }
}
