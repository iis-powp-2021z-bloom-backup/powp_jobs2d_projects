package edu.kis.powp.jobs2d.command;

public class ImmutableCommandBuilder {
    public String name;
    public String commandsList;
    public ImmutableCommandBuilder (String name, String commandsList){

        this.name = name;
        this.commandsList = commandsList;
    }

    public ImmutableCommandBuilder(int i) {
    }
    public ImmutableCommandBuilder setName(String name){
        this.name=name;
        return this;
    }
    public ImmutableCommandBuilder addCommandsList(String commandList){
        this.commandsList = commandList;
        return this;
    }
    public ImmutableCommandBuilder build(){
        return new ImmutableCommandBuilder(name,commandsList);
    }
}