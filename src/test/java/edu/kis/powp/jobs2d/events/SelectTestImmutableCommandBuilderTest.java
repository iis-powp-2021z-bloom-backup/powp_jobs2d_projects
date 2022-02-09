package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.TaskImmutableCommandBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectTestImmutableCommandBuilderTest implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        CompoundCommand compoundCommand = new TaskImmutableCommandBuilder(5).setName("Name").addCommandsList("clone").build();
    }}






