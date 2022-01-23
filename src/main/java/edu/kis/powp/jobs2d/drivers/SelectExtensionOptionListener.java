package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SelectExtensionOptionListener implements ActionListener {
    private ExtensionsManager extensionsManager;


    @Override
    public void actionPerformed(ActionEvent e) {
        List<Job2dDriver> extensionList = extensionsManager.getExtensionList();
        if(extensionList.isEmpty()){
            //extensionList
        }
        for(Job2dDriver job2dDriver: extensionList){
            System.out.println(job2dDriver.toString());
            System.out.println(e.paramString());
            if(job2dDriver.toString().equals(e.getActionCommand())){

            }
        }
    }
}
