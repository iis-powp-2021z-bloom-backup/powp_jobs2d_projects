package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.CommandReader;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.observers.CommandHistoryObserver;
import edu.kis.powp.observer.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CommandHistoryWindow extends JFrame implements WindowComponent {

    private DriverCommandManager commandManager;

    private JTextArea currentCommandField;

    private String observerListString = "";
    private JTextArea observerListField;

    List<JButton> button = new ArrayList<>();
    Integer i = 0;

    public List<String> list;

    /**
     *
     */

    public CommandHistoryWindow(List<String> list, List<DriverCommand> comList) {
        this.setTitle("SHOW");
        this.setSize(400, 400);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        this.list = list;
        commandManager = CommandsFeature.getDriverCommandManager();
        CommandHistoryWindowObserver obs = new CommandHistoryWindowObserver(commandManager);

        commandManager.getChangePublisher().addSubscriber(obs);

        GridBagConstraints c = new GridBagConstraints();

        currentCommandField = new JTextArea("No history command");
        currentCommandField.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(currentCommandField, c);

        JButton undo = new JButton("UNDO");
        /*undo.addActionListener((ActionEvent e) -> currentCommandField.setText(
                String.valueOf((i = (--i) < 0 ? 0 : i)))
        );*/
        undo.addActionListener((ActionEvent e) ->
        {
            if (list.isEmpty())
            {
                currentCommandField.setText("No history command");
            }
            i = --i;
            if (i < 0)
            {
                i = 0;
            }
            else if (i >= list.size())
            {
                i = list.size()-1;
            }
            currentCommandField.setText(list.get(i)+" "+i.toString());
            System.out.println(comList);
        });



        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;

        content.add(undo, c);

        JButton redo = new JButton("REDO");
        /*redo.addActionListener((ActionEvent e) -> currentCommandField.setText(
                String.valueOf((i = (++i) > list.size()-1 ? 0 : i)))
        );*/

        redo.addActionListener((ActionEvent e) ->
                {
                    if (list.isEmpty())
                    {
                        currentCommandField.setText("No history command");
                    }

                    i = ++i;
                    if (i < 0)
                    {
                        i = 0;
                    }
                    else if (i >= list.size())
                    {
                        i = list.size()-1;
                    }
                    currentCommandField.setText(list.get(i)+" "+i.toString());
                    System.out.println(comList);
                });

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(redo, c);

        JButton reaction = new JButton("REACTION");
        reaction.addActionListener((ActionEvent e) -> {
                    commandManager.setCurrentCommand(comList, "TopSecretCommand History");
        }
                );
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(reaction, c);

        currentCommandField.setText(i.toString());

    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        currentCommandField.setText("");
        if (this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
    }

}
