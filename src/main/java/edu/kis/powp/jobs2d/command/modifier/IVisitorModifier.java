package edu.kis.powp.jobs2d.command.modifier;

import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.modifier.model.Point;

public interface IVisitorModifier {

    Point getPoint(OperateToCommand operateToCommand);

    void setPoint(OperateToCommand operateToCommand, Point point);

    Point getPoint(SetPositionCommand setPositionCommand);

    void setPoint(SetPositionCommand setPositionCommand, Point point);

}
