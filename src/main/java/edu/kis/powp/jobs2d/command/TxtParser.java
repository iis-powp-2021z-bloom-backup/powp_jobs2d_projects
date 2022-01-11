package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * txt file in format:
 * Z XX YY
 * Z XX YY
 * where:
 * Z - 's' for setPosition or 'o' for operateTo
 * XX - x coordinate (int)
 * YY - y coordinate (int)
 **/
public class TxtParser implements CommandParser {

	private DriverCommand parsedCommand;

	public void parse(String commandsInput) {
		List<DriverCommand> TEMP = new ArrayList<>();
		String line;
		Scanner scanner = new Scanner(commandsInput);
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			List<String> lineList = Arrays.asList(line.toLowerCase().split(" "));
			if (lineList.get(0).equals("s")) {
				TEMP.add(new SetPositionCommand(Integer.parseInt(lineList.get(1)), Integer.parseInt(lineList.get(2))));
			} else if (lineList.get(0).equals("o")) {
				TEMP.add(new OperateToCommand(Integer.parseInt(lineList.get(1)), Integer.parseInt(lineList.get(2))));
			}
		}
		scanner.close();
		parsedCommand = new CompoundCommand(TEMP, "Txt file command");
	}

	@Override
	public DriverCommand getParsedCommand() {
		return parsedCommand;
	}
}
