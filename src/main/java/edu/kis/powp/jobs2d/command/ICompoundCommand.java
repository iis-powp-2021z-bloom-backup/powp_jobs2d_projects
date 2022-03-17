package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.command.visitor.VisitorCommand;

import java.util.Iterator;

/**
 * Interface extending Job2dDriverCommand to execute more than one command.
 */
public interface ICompoundCommand extends DriverCommand {

	Iterator<DriverCommand> iterator();

	default void accept(VisitorCommand visitor) {
		visitor.visit(this);
	}


}