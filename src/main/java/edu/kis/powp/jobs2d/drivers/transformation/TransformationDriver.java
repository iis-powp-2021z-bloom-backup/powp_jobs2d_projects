package edu.kis.powp.jobs2d.drivers.transformation;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.transformation.model.Point;

import java.util.ArrayList;
import java.util.List;

public class TransformationDriver implements Job2dDriver {

    private final List<Transformation> transformationsList = new ArrayList<>();
    private final Job2dDriver driver;

    public TransformationDriver(Job2dDriver job2dDriver) {
        this.driver = job2dDriver;
    }

    public void addNewTransformation(Transformation transformation) {
        transformationsList.add(transformation);
    }

    @Override
    public void setPosition(int x, int y) {
        Point point = transformedPoint(new Point(x, y));
        driver.setPosition(point.getX(), point.getY());
    }

    @Override
    public void operateTo(int x, int y) {
        Point point = transformedPoint(new Point(x, y));
        driver.operateTo(point.getX(), point.getY());
    }

    private Point transformedPoint(Point point) {
        for (Transformation transformation : transformationsList) {
            point = transformation.transform(point);
        }
        return point;
    }

}
