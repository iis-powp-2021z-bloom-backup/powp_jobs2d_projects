package edu.kis.powp.jobs2d.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;
import java.util.stream.Collectors;

public class CommandReader {
	private File file;
	private DriverCommand commandList;

	public CommandReader(File file) {
		this.file = file;
	}

	public void fillListFromFile() throws Exception {
		final String TXT_EXTENSION = "txt";
		final String JSON_EXTENSION = "json";

		String fileName = this.file.getName();
		String fileType = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String fileContent = br.lines().collect(Collectors.joining(System.lineSeparator()));
		br.close();
		CommandParser parser;

		switch (fileType) {
			case TXT_EXTENSION:
				parser = new TxtParser();
				break;
			case JSON_EXTENSION:
				parser = new JsonParser();
				break;
			default:
				throw new Exception("Invalid file type");
		}
		if (Objects.nonNull(parser)) {
			parser.parse(fileContent);
			this.commandList = parser.getParsedCommand();
		}
	}

	public DriverCommand getCommandList() {
		return commandList;
	}
}

