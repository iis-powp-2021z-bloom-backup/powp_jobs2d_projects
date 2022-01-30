package edu.kis.powp.jobs2d.command;

public class ImmutableCommandBuilderTest {

    public static void main(String[] args) {
        ImmutableCommandBuilder ImmutableCommandBuilder = new ImmutableCommandBuilder(5).setName("Name").setCommandsList("CommandList").build();
        System.out.println(ImmutableCommandBuilder);
    }

}