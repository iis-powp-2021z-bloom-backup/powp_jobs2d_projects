package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.transforms.Transform;

import java.util.Iterator;

public class TransformationVisitor implements VisitorCommand {
	private final Transform transform;

	public TransformationVisitor(Transform transform) {
		this.transform = transform;
	}

	@Override
	public void visit(OperateToCommand operateToCommand) {
		/* required because transform changes the initial value */
		int x = operateToCommand.getPosX();
		int y = operateToCommand.getPosY();
		operateToCommand.setPosX(this.transform.transformX(x, y));
		operateToCommand.setPosY(this.transform.transformY(x, y));
	}

	@Override
	public void visit(SetPositionCommand setPositionCommand) {
		/* required because transform changes the initial value */
		int x = setPositionCommand.getPosX();
		int y = setPositionCommand.getPosY();
		setPositionCommand.setPosX(this.transform.transformX(x, y));
		setPositionCommand.setPosY(this.transform.transformY(x, y));
	}

	@Override
	public void visit(ICompoundCommand iCompoundCommand) {
		Iterator<DriverCommand> it = iCompoundCommand.iterator();

		while (it.hasNext()) {
			DriverCommand driverCommand = it.next();
			driverCommand.accept(this);
		}
	}
}
