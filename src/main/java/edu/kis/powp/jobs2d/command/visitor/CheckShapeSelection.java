package edu.kis.powp.jobs2d.command.visitor;

public class CheckShapeSelection implements checkPaperSize {

    private int bonduaryPositiveX;
    private int bonduaryNegativeX;
    private int bonduaryPositiveY;
    private int bonduaryNegativeY;

    public CheckShapeSelection( Size a){
        this.bonduaryPositiveX=a.getWidth()/2;
        this.bonduaryPositiveY=a.getHeight()/2;
        this.bonduaryNegativeX=(-1)*(a.getWidth()/2);
        this.bonduaryNegativeY=(-1)*(a.getHeight()/2);
    }


    @Override
    public boolean checkSize(int x, int y) {
        if((x>bonduaryPositiveX)||(x<bonduaryNegativeX)){
            return true;
        }
        if((y>bonduaryPositiveY)||(y<bonduaryNegativeY)){
            return true;
        }
        return false;
    }
}

