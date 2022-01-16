package edu.kis.powp.jobs2d.command.visitor;

public class VisitorFlipX extends TransformationVisitor {
	@Override
	int calculateNewPositionX(int oldX, int oldY) {
		return oldX;
	}

	@Override
	int calculateNewPositionY(int oldX, int oldY) {
		return oldY * (-1);
	}
}
