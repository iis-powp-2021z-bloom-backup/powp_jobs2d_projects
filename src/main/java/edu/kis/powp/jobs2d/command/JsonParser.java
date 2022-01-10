package edu.kis.powp.jobs2d.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

/***
 * JSON example utilizing SetPositionCommand and OperateToCommand commands
 *
 * [{"CLASSNAME": "SetPositionCommand",
 *     "DATA": {
 *       "posX": 100,
 *       "posY": 100
 * }},
 * {"CLASSNAME": "OperateToCommand",
 *     "DATA": {
 *       "posX": 200,
 *       "posY": 200
 * }}]
 *
 ***/
public class JsonParser implements CommandParser {

	private DriverCommand parsedCommand;

	@Override
	public void parse(String commandsInput) {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(DriverCommand.class, new JsonAdapter());
		Gson gson = builder.create();

		DriverCommand[] commands = gson.fromJson(commandsInput, DriverCommand[].class);
		this.parsedCommand = new CompoundCommand(Arrays.asList(commands), "Json file command");
	}

	@Override
	public DriverCommand getParsedCommand() {
		return parsedCommand;
	}
}
