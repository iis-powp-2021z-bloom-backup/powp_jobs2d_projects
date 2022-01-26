package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.modifier.model.OperationNotSupportedException;
import edu.kis.powp.jobs2d.command.modifier.model.Point;
import edu.kis.powp.jobs2d.command.visitor.VisitorCommand;
import edu.kis.powp.jobs2d.command.modifier.IVisitorModifier;

/**
 * DriverCommand interface.
 */
public interface DriverCommand extends Cloneable {

	/**
	 * Execute command on driver.
	 * 
	 * @param driver driver.
	 */
	void execute(Job2dDriver driver);

	void accept(VisitorCommand visitor);

	Object clone();

	Point getPoint(IVisitorModifier visitor) throws OperationNotSupportedException;

	void setPoint(IVisitorModifier visitor, Point point) throws OperationNotSupportedException;

}
