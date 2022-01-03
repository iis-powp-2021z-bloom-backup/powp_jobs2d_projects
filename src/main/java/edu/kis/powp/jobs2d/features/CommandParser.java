package edu.kis.powp.jobs2d.features;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.drivers.adapter.JsonAdapter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandParser {
    private File file;
    private List<DriverCommand> commandList;

    public CommandParser(File file) {
        this.file = file;
        this.commandList = new ArrayList<>();
    }

    public void fillListFromFile() throws Exception {
        String fileName = this.file.getName();
        String fileType = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

        switch (fileType) {
            case "txt":
                populateCommandListFromTxt();
                break;
            case "json":
                populateCommandListFromJson();
                break;
            default:
                throw new Exception("Invalid file type");
        }
    }

    public List<DriverCommand> getCommandList() {
        return commandList;
    }

    /**
     * txt file in format:
     * Z XX YY
     * Z XX YY
     * where:
     * Z - 's' for setPosition or 'o' for operateTo
     * XX - x coordinate (int)
     * YY - y coordinate (int)
     **/
    private void populateCommandListFromTxt() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(this.file));

        String line = reader.readLine();
        while (line != null) {

            List<String> lineList = Arrays.asList(line.toLowerCase().split(" "));
            if (lineList.get(0).equals("s")) {
                commandList.add(new SetPositionCommand(Integer.parseInt(lineList.get(1)), Integer.parseInt(lineList.get(2))));
            } else if (lineList.get(0).equals("o")) {
                commandList.add(new OperateToCommand(Integer.parseInt(lineList.get(1)), Integer.parseInt(lineList.get(2))));
            }
            line = reader.readLine();
        }
        reader.close();
    }

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
    private void populateCommandListFromJson() throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(DriverCommand.class, new JsonAdapter());
        Gson gson = builder.create();

        BufferedReader reader;
        reader = new BufferedReader(new FileReader(this.file));
        DriverCommand[] commands = gson.fromJson(reader, DriverCommand[].class);
        this.commandList = Arrays.asList(commands);
    }

}
