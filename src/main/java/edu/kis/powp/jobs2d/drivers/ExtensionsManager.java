package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.List;

public class ExtensionsManager {
    private List<Job2dDriver> allExtensionList;
    private List<Job2dDriver> activeExtensionList;
    private DriverManager driverManager;

    public ExtensionsManager() {
        allExtensionList = new ArrayList<>();
        activeExtensionList = new ArrayList<>();
    }

    public void addExtension(Job2dDriver dDriver){
        allExtensionList.add(dDriver);
    }
    public void removeExtension(Job2dDriver dDriver){
        allExtensionList.remove(dDriver);
    }



    public List<Job2dDriver> getExtensionList(){
        return allExtensionList;
    }

}
