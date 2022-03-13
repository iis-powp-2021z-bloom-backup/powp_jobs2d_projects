package edu.kis.powp.jobs2d.command.visitor;

public class CheckShapeSelection {

    private int bonduaryPositiveX;
    private int bonduaryNegativeX;
    private int bonduaryPositiveY;
    private int bonduaryNegativeY;

    public CheckShapeSelection(int height, int width){
        this.bonduaryPositiveX=width/2;
        this.bonduaryPositiveY=height/2;
        this.bonduaryNegativeX=(-1)*(width/2);
        this.bonduaryNegativeY=(-1)*(height/2);
    }

    public boolean checkPaperSize(int x, int y) {
        if((x>bonduaryPositiveX)||(x<bonduaryNegativeX)){
            return true;
        }
        if((y>bonduaryPositiveY)||(y<bonduaryNegativeY)){
            return true;
        }
        return false;
    }
}

