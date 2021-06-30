package com.codecool.dungeoncrawl.filemanager;

import com.codecool.dungeoncrawl.model.BaseModel;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import java.io.*;
import java.util.Optional;

public class FileManager {

    PlayerModel playerModel;
    GameState gameState;

    public FileManager(PlayerModel playerModel, GameState gameState) {
        this.playerModel = playerModel;
        this.gameState = gameState;
    }

    public void exportDataToFile() {
        try {
//            ObjectOutputStream oosPlayer = new ObjectOutputStream(new FileOutputStream("savegame/export_player.txt"));
            final FileOutputStream out = new FileOutputStream("savegame/export_gamestate.txt");
            ObjectOutputStream oosGameState = new ObjectOutputStream(out);
//            oosPlayer.writeObject(playerModel);
            oosGameState.writeObject(gameState);
            System.out.println("EXPORT");
            oosGameState.flush();
            oosGameState.close();
            out.close();

        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public Optional<GameState> importDataFromFile() {
        try {
            final FileInputStream in = new FileInputStream("savegame/export_gamestate.txt");
            ObjectInputStream oisGameState = new ObjectInputStream(in);
            GameState gamestate = (GameState) oisGameState.readObject();
            oisGameState.close();
            in.close();
            System.out.println("IMPORT");
            System.out.println(gamestate);
            return Optional.of(gamestate);
        } catch (IOException | ClassNotFoundException e){
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }

    public GameState importGameState() {
        return null;
    }

    public PlayerModel importGPlayerModel() {
        return null;
    }
}
