package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SelectExtensionOptionListener implements ActionListener {
    private ExtensionsManager extensionsManager;

    public SelectExtensionOptionListener(ExtensionsManager extensionsManager) {
        this.extensionsManager = extensionsManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        extensionsManager.enableOrDisableExtension(e.getActionCommand());
    }
}
