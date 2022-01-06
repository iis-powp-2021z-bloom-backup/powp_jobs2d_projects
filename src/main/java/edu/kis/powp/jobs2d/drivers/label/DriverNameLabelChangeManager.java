package edu.kis.powp.jobs2d.drivers.label;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.observer.Publisher;

public class DriverNameLabelChangeManager {
    private Job2dDriver job2dDriver;
    private final Publisher changePublisher = new Publisher();

    public synchronized void setCurrentDriver(Job2dDriver job2dDriver){
        this.job2dDriver = job2dDriver;
        changePublisher.notifyObservers();
    }

    public synchronized Job2dDriver getCurrentDriver(){
        return job2dDriver;
    }

    public synchronized String getCurrentDriverString(){
        if(job2dDriver == null){
            return "No driver loaded";
        } else {
            return job2dDriver.toString();
        }
    }

    public synchronized void clearCurrentDriver(){
        job2dDriver = null;
    }

    public Publisher getChangePublisher(){
        return changePublisher;
    }

}
