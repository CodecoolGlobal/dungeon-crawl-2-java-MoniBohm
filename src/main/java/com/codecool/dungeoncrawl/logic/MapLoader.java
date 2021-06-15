package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.MapObject.actors.*;
import com.codecool.dungeoncrawl.logic.MapObject.items.Door;
import com.codecool.dungeoncrawl.logic.MapObject.items.armor.BodyArmor;
import com.codecool.dungeoncrawl.logic.MapObject.items.armor.HeadGear;
import com.codecool.dungeoncrawl.logic.MapObject.items.armor.LegArmor;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Coin;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Key;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String mapFile) {
        InputStream is = MapLoader.class.getResourceAsStream(mapFile);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ': // not player area
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#': // wall
                            cell.setType(CellType.WALL);
                            break;
                        case '.': // empty cell
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'b': // bucket
                            cell.setType(CellType.FLOOR);
                            new Bucket(cell);
                            break;
                        case 'd': // drumstick
                            cell.setType(CellType.FLOOR);
                            new Drumstick(cell);
                            break;
                        case 'c': // colonel
                            cell.setType(CellType.FLOOR);
                            new Colonel(cell);
                            break;
                        case 'k': // item
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case 'C': // item
                            cell.setType(CellType.FLOOR);
                            new Coin(cell);
                            break;
                        case 'h': // item
                            cell.setType(CellType.FLOOR);
                            new HeadGear(cell);
                            break;
                        case 'a': // item
                            cell.setType(CellType.FLOOR);
                            new BodyArmor(cell);
                            break;
                        case 'l': // item
                            cell.setType(CellType.FLOOR);
                            new LegArmor(cell);
                            break;
                        case 'E': // item
                            cell.setType(CellType.FLOOR);
                            new Door(cell);
                            break;
                        case '@': // player
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
