package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.MapObject.actors.*;
import com.codecool.dungeoncrawl.logic.MapObject.items.booster.HealtPotion;
import com.codecool.dungeoncrawl.logic.MapObject.items.booster.ManaPotion;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.NextStageDoor;
import com.codecool.dungeoncrawl.logic.MapObject.items.armor.BodyArmor;
import com.codecool.dungeoncrawl.logic.MapObject.items.armor.HeadGear;
import com.codecool.dungeoncrawl.logic.MapObject.items.armor.LegArmor;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Coin;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Key;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.PrevStageDoor;
import com.codecool.dungeoncrawl.logic.MapObject.items.weapon.Bazooka;
import com.codecool.dungeoncrawl.logic.MapObject.items.weapon.Uzi;

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
                        case 'f': // fire
                            cell.setType(CellType.FIRE);
                            break;
                        case 'g': // grave
                            cell.setType(CellType.GRAVE);
                            break;
                        case 'x': // corpse
                            cell.setType(CellType.CORPSE);
                            break;
                        case '#': // wall
                            cell.setType(CellType.WALL);
                            break;
                        case '.': // empty cell
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'b': // enemy: bucket
                            cell.setType(CellType.FLOOR);
                            new Bucket(cell);
                            break;
                        case 'd': // enemy: drumstick
                            cell.setType(CellType.FLOOR);
                            new Drumstick(cell);
                            break;
                        case 'c': // enemy: colonel
                            cell.setType(CellType.FLOOR);
                            new Colonel(cell);
                            break;
                        case 'k': // key
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case 'C': // coin
                            cell.setType(CellType.FLOOR);
                            new Coin(cell);
                            break;
                        case 'h': // head gear
                            cell.setType(CellType.FLOOR);
                            new HeadGear(cell);
                            break;
                        case 'a': // body armor
                            cell.setType(CellType.FLOOR);
                            new BodyArmor(cell);
                            break;
                        case 'l': // leg armor
                            cell.setType(CellType.FLOOR);
                            new LegArmor(cell);
                            break;
                        case 'E': // next stage door
                            cell.setType(CellType.FLOOR);
                            new NextStageDoor(cell);
                            break;
                       case 'p': // prev stage door
                            cell.setType(CellType.FLOOR);
                            new PrevStageDoor(cell);
                            break;
                        case 'u':
                            cell.setType(CellType.FLOOR);
                            new Uzi(cell);
                            break;
                        case 'z':
                            cell.setType(CellType.FLOOR);
                            new Bazooka(cell);
                            break;
                        case 'm':
                            cell.setType(CellType.FLOOR);
                            new ManaPotion(cell);
                            break;
                        case 'j':
                            cell.setType(CellType.FLOOR);
                            new HealtPotion(cell);
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
