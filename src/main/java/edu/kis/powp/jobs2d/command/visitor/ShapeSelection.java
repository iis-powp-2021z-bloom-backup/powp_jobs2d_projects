package edu.kis.powp.jobs2d.command.visitor;

public class ShapeSelection {
    public int shortenedX;
    public int shortenedY;
    public int stretchingX;
    public int stretchingY;

    public ShapeSelection(int strokeheight, int strokewidth) {
        this.shortenedX = (-1) * (strokewidth / 2);
        this.shortenedY = (-1) * (strokeheight / 2);
        this.stretchingX = strokewidth / 2;
        this.stretchingY = strokeheight / 2;

    }

    public boolean CheckRelationSize(int x, int y) {
        if ((y > stretchingY) || (y < shortenedY)) {
            return true;
        }
        if ((x > stretchingX) || (x < shortenedX)) {
            return true;
        }
        System.out.println("Problem with shape model");
        return false;
    }



}