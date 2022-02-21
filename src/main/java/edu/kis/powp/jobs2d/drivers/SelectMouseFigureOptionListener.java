package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.drivers.visitor.VisitorReturn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectMouseFigureOptionListener implements ActionListener {

    private DriverManager driverManager;
    private JPanel drawPanel;
    private Integer previousHeadXPosition;
    private Integer previousHeadYPosition;

    private boolean firstClick = true;

    public SelectMouseFigureOptionListener(JPanel drawPanel, DriverManager driverManager) {
        this.drawPanel = drawPanel;
        this.driverManager = driverManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cleanHeadPosition();
        int halfWindowHeight = drawPanel.getHeight() / 2;
        int halfWindowWidth = drawPanel.getWidth() / 2;

        this.drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                VisitorReturn visitorReturn = new VisitorReturn();
                if (firstClick) {
                    firstClick = false;
                    updatePreviousMousePosition(e, halfWindowWidth, halfWindowHeight);
                }

                if (previousHeadXPosition == null || previousHeadYPosition == null) {
                    updatePreviousMousePosition(e, halfWindowWidth, halfWindowHeight);
                }

                driverManager.accept(visitorReturn);
                visitorReturn.getSavedJob2dDriver().setPosition(previousHeadXPosition, previousHeadYPosition);

                if (SwingUtilities.isLeftMouseButton(e)) {
                    visitorReturn.getSavedJob2dDriver().operateTo(e.getX() - halfWindowWidth, e.getY() - halfWindowHeight);
                }

                updatePreviousMousePosition(e, halfWindowWidth, halfWindowHeight);
            }
        });
    }

    private void updatePreviousMousePosition(MouseEvent event, int halfWidth, int halfHeight) {
        previousHeadXPosition = event.getX() - halfWidth;
        previousHeadYPosition = event.getY() - halfHeight;
    }

    private void cleanHeadPosition() {
        previousHeadYPosition = null;
        previousHeadXPosition = null;
    }

}