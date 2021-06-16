package com.codecool.dungeoncrawl.logic.MapObject.items.general;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;

public class openDoor extends Item {

    public boolean isOpen;

    public openDoor(Cell cell) {
        super(cell);
        isOpen = false;
    }
    public void setOpen(boolean value){
        this.isOpen = value;
    }
    @Override
    public String toString() {
        return "Door";
    }

    @Override
    public String getTileName() {
        if (isOpen){
            return "opendoor";
        }else{
            return "door";
        }
    }

}
