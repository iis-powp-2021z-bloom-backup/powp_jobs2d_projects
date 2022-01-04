package edu.kis.powp.jobs2d.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	private String fileContent;
	private List<DriverCommand> commandList;

	public JsonParser(String fileContent) {
		this.fileContent = fileContent;
		this.commandList = new ArrayList<>();
	}

	@Override
	public void fillListFromFile() {


		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(DriverCommand.class, new JsonAdapter());
		Gson gson = builder.create();

		DriverCommand[] commands = gson.fromJson(fileContent, DriverCommand[].class);
		this.commandList = Arrays.asList(commands);
	}

	@Override
	public List<DriverCommand> getCommandList() {
		return commandList;
	}
}
