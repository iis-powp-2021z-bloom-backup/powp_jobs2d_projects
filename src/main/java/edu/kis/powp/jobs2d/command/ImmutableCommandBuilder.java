package edu.kis.powp.jobs2d.command;

import java.util.List;

public class ImmutableCommandBuilder {
    private String name;
    private List<DriverCommand> commandsList;
    public ImmutableCommandBuilder (String name,List<DriverCommand>  commandsList){

        this.name = name;
        this.commandsList = commandsList;
    }

    public ImmutableCommandBuilder setName(String name){
        this.name=name;
        return this;
    }
    public ImmutableCommandBuilder setCommandsList(List<DriverCommand> commandList){
        this.commandsList = commandList;
        return this;
    }
    public ImmutableCommandBuilder build(){
        return new ImmutableCommandBuilder(name,commandsList);
    }
}