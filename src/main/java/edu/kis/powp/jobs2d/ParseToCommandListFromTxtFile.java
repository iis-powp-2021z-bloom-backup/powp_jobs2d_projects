package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

        //TODO add verification of file type and implement solution for at least 1-2 more filetypes (JSON, csv)
    }

    public void fillListFromFile() throws Exception {
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
        //TODO if list is empty do something maybe
        reader.close();
    }

    public List<DriverCommand> getCommandList() {
        return commandList;
    }
}
