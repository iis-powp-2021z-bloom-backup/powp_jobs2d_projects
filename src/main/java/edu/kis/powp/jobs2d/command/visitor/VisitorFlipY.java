package edu.kis.powp.jobs2d.command.visitor;

public class VisitorFlipY extends TransformationVisitor {
	@Override
	int calculateNewPositionX(int oldX, int oldY) {
		return oldX * (-1);
	}

	@Override
	int calculateNewPositionY(int oldX, int oldY) {
		return oldY;
	}

}
