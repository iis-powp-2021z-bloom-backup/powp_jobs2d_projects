package edu.kis.powp.jobs2d.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

		String fileName = this.file.getName();
		String fileType = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String fileContent =  br.lines().collect(Collectors.joining(System.lineSeparator()));
        br.close();
        System.out.println(fileContent);

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

