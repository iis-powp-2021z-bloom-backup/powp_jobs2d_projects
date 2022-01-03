package edu.kis.powp.jobs2d;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.InterfaceAdapter;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParseToCommandListFromTxtFile {
    private File file;
    private List<DriverCommand> commandList;

    //TODO figure out how to change class ane methods names and where to put the class

    public ParseToCommandListFromTxtFile(File file) {
        this.file = file;
        this.commandList = new ArrayList<>();
    }

    public void fillListFromFile() throws Exception {
        String name = this.file.getName();
        String type = name.substring(name.lastIndexOf('.') + 1).toLowerCase();

        switch (type) {
            case "txt":
                populateCommandListFromTxt();
                break;
            case "json":
                populateCommandListFromJson();
                System.out.println("json");
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
        builder.registerTypeAdapter(DriverCommand.class, new InterfaceAdapter());
        Gson gson = builder.create();

        BufferedReader reader;
        reader = new BufferedReader(new FileReader(this.file));
//        String carsJsonFormat = gson.toJson(dc, DriverCommand[].class);
        DriverCommand[] returnedOnes = gson.fromJson(reader, DriverCommand[].class);
        this.commandList = Arrays.asList(returnedOnes);
    }

}
