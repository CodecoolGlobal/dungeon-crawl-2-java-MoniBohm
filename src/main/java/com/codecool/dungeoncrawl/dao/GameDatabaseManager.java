package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.MapObject.actors.Player;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class GameDatabaseManager {
    private PlayerDao playerDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
    }

    public void savePlayer(Player player) {
        PlayerModel model = new PlayerModel(player);
        playerDao.add(model);
    }

    public void testConnection() throws SQLException {
        PGSimpleDataSource dataSource = (PGSimpleDataSource) connect();

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
}
