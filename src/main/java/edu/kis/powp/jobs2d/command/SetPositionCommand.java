package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.modifier.model.Point;
import edu.kis.powp.jobs2d.command.visitor.VisitorCommand;

/**
 * Implementation of Job2dDriverCommand for setPosition command functionality.
 */
public class SetPositionCommand implements DriverCommand {

	private int posX;
	private int posY;

	public SetPositionCommand(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	@Override
	public Point getPoint() {
		return new Point(posX, posY);
	}

	@Override
	public void setPoint(Point point) {
		this.posX = point.getX();
		this.posY = point.getY();
	}

	@Override
	public void execute(Job2dDriver driver) {
		driver.setPosition(posX, posY);
	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return new SetPositionCommand(this.posX, this.posY);
		}
	}
  
	@Override
	public void accept(VisitorCommand visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return "SetPositionCommand: posX=" + posX + ", posY=" + posY;
	}

}
