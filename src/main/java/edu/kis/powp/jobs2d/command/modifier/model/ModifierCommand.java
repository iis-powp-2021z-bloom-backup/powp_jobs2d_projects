package edu.kis.powp.jobs2d.command.modifier.model;

public class ModifierCommand {

    private final CommandType commandType;
    private int x;
    private int y;

    public ModifierCommand(int x, int y, CommandType commandType) {
        this.x = x;
        this.y = y;
        this.commandType = commandType;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return commandType + " x=" + x + ", y=" + y;
    }

}
