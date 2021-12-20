package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class DeepCopyTest {

    public static void main(String[] args) {
        testDeepCopyOfSimpleCommand();
        testDeepCopyOfCompoundCommand();
    }

    private static void testDeepCopyOfSimpleCommand() {
        System.out.println("Test that simple command can be copied");
        // given
        final MockDriver driver1 = new MockDriver();
        final MockDriver driver2 = new MockDriver();
        final DriverCommand command = new OperateToCommand(1,2);

        // when
        final DriverCommand commandCopy = (DriverCommand)command.clone();

        // then
        assert(commandCopy instanceof OperateToCommand);
        assert(commandCopy != command);

        command.execute(driver1);
        commandCopy.execute(driver2);
        assert(driver1.getList().size() == driver2.getList().size());
        assert(driver1.getList().equals(driver2.getList()));
    }

    private static void testDeepCopyOfCompoundCommand() {
        System.out.println("Test deep copy of compound command");
        // given
        final MockDriver driver1 = new MockDriver();
        final MockDriver driver2 = new MockDriver();
        final String name = "UniqueName";

        final CompoundCommand command = new CompoundCommand(Stream.of(
                new OperateToCommand(1,2),
                new SetPositionCommand(3,4),
                new OperateToCommand(5,6),
                new CompoundCommand(Stream.of(
                    new OperateToCommand(1,2),
                    new SetPositionCommand(3,4)
                ).collect(toList()), name)
        ).collect(toList()), name);

        // when
        final ICompoundCommand commandCopy = (ICompoundCommand) command.clone();

        // then
        assert(commandCopy != null);
        assert(commandCopy != command);
        assert(command.toString().equals(commandCopy.toString()));

        command.execute(driver1);
        commandCopy.execute(driver2);
        assert(driver1.getList().size() == driver2.getList().size());
        assert(driver1.getList().equals(driver2.getList()));

        final List<DriverCommand> list = getAllObjects(commandCopy);
        assert(getAllObjects(command).stream().noneMatch(list::contains));
    }

    // recursively get all commands from compound command
    private static List<DriverCommand> getAllObjects(DriverCommand command) {
        final List<DriverCommand> list = new ArrayList<>();
        list.add(command);
        if(command instanceof ICompoundCommand) {
            ((ICompoundCommand) command).iterator().forEachRemaining(x -> list.addAll(getAllObjects(x)));
        }
        return list;
    }

    // Mock driver used to record movements
    private static class MockDriver implements Job2dDriver {
        private final List<Integer> movements = new ArrayList<>();
        @Override
        public void setPosition(int x, int y) {
            movements.add(x);
            movements.add(y);
        }
        @Override
        public void operateTo(int x, int y) {
            setPosition(x,y);
        }
        public List<Integer> getList() {
            return movements;
        }
    }
}
