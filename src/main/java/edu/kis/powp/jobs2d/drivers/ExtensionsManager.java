package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.composite.DriverComposite;

import java.util.ArrayList;
import java.util.List;

public class ExtensionsManager {
    private List<Job2dDriver> allExtensionList;

    private List<Job2dDriver> activeExtensionList;
    public ExtensionsManager() {
        allExtensionList = new ArrayList<>();
        activeExtensionList = new ArrayList<>();
    }

    public void addExtension(Job2dDriver dDriver){
        allExtensionList.add(dDriver);
    }

    public void enableOrDisableExtension(String nameOfExtension){
       for(Job2dDriver driver:allExtensionList){
           if(driver.toString().equals(nameOfExtension)){
               if (activeExtensionList.contains(driver)){
                   activeExtensionList.remove(driver);
               } else {
                   activeExtensionList.add(driver);
               }
           }
       }

    }

    public List<Job2dDriver> getActiveExtensionList(){
        return activeExtensionList;
    }
}
