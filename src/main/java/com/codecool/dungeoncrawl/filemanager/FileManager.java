package com.codecool.dungeoncrawl.filemanager;

import com.codecool.dungeoncrawl.model.BaseModel;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import java.io.*;

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
            oosGameState.flush();
            oosGameState.close();
            out.close();

        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public void importDataFromFile() {
        try {
            final FileInputStream in = new FileInputStream("savegame/export_gamestate.txt");
            ObjectInputStream oisGameState = new ObjectInputStream(in);
            System.out.println("yo");
            GameState gamestate = (GameState) oisGameState.readObject();
            oisGameState.close();
            in.close();
            System.out.println(gamestate);
        } catch (IOException | ClassNotFoundException e){
            System.err.println(e.getMessage());

        }
    }

    public GameState importGameState() {
        return null;
    }

    public PlayerModel importGPlayerModel() {
        return null;
    }
}
