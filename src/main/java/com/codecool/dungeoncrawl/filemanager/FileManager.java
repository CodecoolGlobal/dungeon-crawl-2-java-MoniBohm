package com.codecool.dungeoncrawl.filemanager;

import com.codecool.dungeoncrawl.model.GameState;

import java.io.*;
import java.util.Optional;

public class FileManager {

    GameState gameState;

    public FileManager(GameState gameState) {
        this.gameState = gameState;
    }

    public void exportDataToFile() {
        try {
            final FileOutputStream out = new FileOutputStream("savegame/export.txt");
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(gameState);
            System.out.println("EXPORT");
            oos.flush();
            oos.close();
            out.close();

        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public Optional<GameState> importDataFromFile() {
        try {
            final FileInputStream in = new FileInputStream("savegame/export.txt");
            ObjectInputStream ois = new ObjectInputStream(in);
            GameState gamestate = (GameState) ois.readObject();
            ois.close();
            in.close();
            System.out.println("IMPORT");
            return Optional.of(gamestate);
        } catch (IOException | ClassNotFoundException e){
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }
}
