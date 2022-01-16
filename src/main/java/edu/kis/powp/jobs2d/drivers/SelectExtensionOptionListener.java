package edu.kis.powp.jobs2d.drivers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectExtensionOptionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.paramString());
        System.out.println(e.getModifiers());
        System.out.println(e.getActionCommand());
        System.out.println(e.getWhen());
    }
}
