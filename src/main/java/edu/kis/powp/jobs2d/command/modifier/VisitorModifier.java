package edu.kis.powp.jobs2d.command.modifier;

import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.modifier.model.Point;

public class VisitorModifier implements IVisitorModifier {

    @Override
    public Point getPoint(OperateToCommand operateToCommand) {
        return new Point(operateToCommand.getPosX(), operateToCommand.getPosY());
    }

    @Override
    public void setPoint(OperateToCommand operateToCommand, Point point) {
        operateToCommand.setPosX(point.getX());
        operateToCommand.setPosY(point.getY());
    }

    @Override
    public Point getPoint(SetPositionCommand setPositionCommand) {
        return new Point(setPositionCommand.getPosX(), setPositionCommand.getPosY());
    }

    @Override
    public void setPoint(SetPositionCommand setPositionCommand, Point point) {
        setPositionCommand.setPosX(point.getX());
        setPositionCommand.setPosY(point.getY());
    }

}
