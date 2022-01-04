package edu.kis.powp.jobs2d.command;

import java.io.*;
import java.util.*;

public class CommandReader {
	private File file;
	private List<DriverCommand> commandList;

	public CommandReader(File file) {
		this.file = file;
		this.commandList = new ArrayList<>();
	}

	public void fillListFromFile() throws Exception {
		final String TXT = "txt";
		final String JSON = "json";
		final String EOF = "\\z";

		String fileName = this.file.getName();
		String fileType = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
		Scanner scanner = new Scanner(file);
		scanner.useDelimiter(EOF);
		String fileContent = scanner.next();
		scanner.close();

		CommandParser parser;

		switch (fileType) {
			case TXT:
				parser = new TxtParser(fileContent);
				break;
			case JSON:
				parser = new JsonParser(fileContent);
				break;
			default:
				throw new Exception("Invalid file type");
		}
		if (Objects.nonNull(parser)) {
			parser.fillListFromFile();
			this.commandList = parser.getCommandList();
		}
	}

	public List<DriverCommand> getCommandList() {
		return commandList;
	}
}

