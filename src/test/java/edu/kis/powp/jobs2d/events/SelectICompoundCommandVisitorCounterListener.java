package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.visitor.VisitorCommand;
import edu.kis.powp.jobs2d.command.visitor.VisitorCounter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

class DataTest implements ICompoundCommand
{
    List<DriverCommand> list;

    public List<DriverCommand> getList() {
        return list;
    }

    public void setList(List<DriverCommand> list) {
        this.list = list;
    }

    @Override
    public void execute(Job2dDriver driver) {

    }

    @Override
    public DriverCommand clone() {
        return null;
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return list.iterator();
    }
    
}

public class SelectICompoundCommandVisitorCounterListener implements ActionListener
{
    private Logger logger = Logger.getLogger("global");

    @Override
    public void actionPerformed(ActionEvent e)
    {
        List<DriverCommand> commands = new ArrayList<>(
                Arrays.asList(
                        new OperateToCommand(3, 4),
                        new SetPositionCommand(1, 2),
                        new OperateToCommand(2, 2)

                )
        );

        List<DriverCommand> commands2 = new ArrayList<>(
                Arrays.asList(
                        new OperateToCommand(1, 2),
                        new SetPositionCommand(2, 3),
                        new OperateToCommand(3, 4)

                )
        );

        DataTest com = new DataTest();
        com.setList(commands2);

        commands.add(com);

        DataTest test = new DataTest();
        test.setList(commands);

        VisitorCounter visitor = new VisitorCounter();

        test.accept(visitor);

        logger.info("Counter: " + visitor.getCounter());

    }
}