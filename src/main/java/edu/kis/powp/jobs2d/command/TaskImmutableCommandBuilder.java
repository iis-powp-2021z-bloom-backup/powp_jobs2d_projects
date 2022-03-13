package edu.kis.powp.jobs2d.command;


public class TaskImmutableCommandBuilder {
    private String name;
    private String commandsList;

    public TaskImmutableCommandBuilder(String name, String commandsList){

        this.name = name;
        this.commandsList = commandsList;
    }

    public TaskImmutableCommandBuilder(int name) {

    }

    public TaskImmutableCommandBuilder setName(String name){
        this.name=name;
        return this;
    }
    public TaskImmutableCommandBuilder addCommandsList(String commandsList){
        this.commandsList=commandsList;
        return this;
    }


}

