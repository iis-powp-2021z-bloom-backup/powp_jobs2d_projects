package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.Job2dDriver;

public class FigureForShapeSelection {


    public static void star(Job2dDriver driver) {
        driver.setPosition(0, -60);
        driver.operateTo(-30, -30);
        driver.operateTo(-60, -30);
        driver.operateTo(-30, 0);
        driver.operateTo(-45, 50);
        driver.operateTo(0, 30);
        driver.operateTo(45, 50);
        driver.operateTo(30, 0);
        driver.operateTo(60, -30);
        driver.operateTo(30, -30);
        driver.operateTo(0, -60);

    }
    public static void trapeze(Job2dDriver driver) {
        driver.setPosition(-200, 200);
        driver.operateTo(200, 200);
        driver.operateTo(60, 0);
        driver.operateTo(-60, 0);
        driver.operateTo(-200, 200);

    }
    public static void triangle(Job2dDriver driver) {
        driver.setPosition( 0, 0);
        driver.operateTo(-60, 0);
        driver.operateTo( 0, -90);
        driver.operateTo(60, 0);
        driver.operateTo( 0, 0);

    }
}
