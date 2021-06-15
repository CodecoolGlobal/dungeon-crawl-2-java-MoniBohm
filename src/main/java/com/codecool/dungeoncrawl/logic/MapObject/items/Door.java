package com.codecool.dungeoncrawl.logic.MapObject.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Door extends Item{

    public boolean isOpen;

    public Door(Cell cell) {
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
        }
        return "door";
    }

}
