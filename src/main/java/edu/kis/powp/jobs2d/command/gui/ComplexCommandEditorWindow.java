package edu.kis.powp.jobs2d.command.gui;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.modifier.model.Point;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComplexCommandEditorWindow extends JFrame implements WindowComponent {

    private static final Insets INSETS = new Insets(0, 0, 0, 0);

    private JList<Object> commandJList;
    private CompoundCommand compoundCommand;
    private JFormattedTextField xTextField;
    private JFormattedTextField yTextField;
    private List<DriverCommand> driverCommandList = new ArrayList<>();
    private LineDriverAdapter lineDriverAdapter;
    private DrawPanelController drawPanelController;

    public ComplexCommandEditorWindow() {
        this.setTitle("Complex command editor");
        this.setSize(600, 400);
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        initializeView();
    }

    public void updateCurrentCommand() {
        this.compoundCommand = (CompoundCommand) CommandsFeature.getDriverCommandManager().getCurrentCommand();
        populateListData();
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }

    private void initializeView() {
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());
        content.setSize(1000, 300);
        content.setVisible(true);

        JScrollPane listScrollPanel = initializeList();
        JPanel moveCommandPanel = initializeMoveCommandPanel();
        JPanel coordsPanel = initializeCordsPanel();
        JPanel controlPanel = initializeSaveCancelPanel();
        JPanel previewPanel = initializePreviewPanel();

        JButton removeButton = new JButton("Remove operation");
        removeButton.addActionListener((ActionEvent e) -> this.onRemoveClick());

        JButton saveCordsButton = new JButton("Save cords");
        saveCordsButton.addActionListener((ActionEvent e) -> this.onSaveCords());

        addComponent(content, listScrollPanel, 0, 0, 1, 3, 1, 1, GridBagConstraints.BOTH);
        addComponent(content, coordsPanel, 1, 0, 1, 1, 0, 1, GridBagConstraints.HORIZONTAL);
        addComponent(content, removeButton, 1, 1, 1, 1, 0, 1, GridBagConstraints.HORIZONTAL);
        addComponent(content, moveCommandPanel, 1, 2, 1, 1, 0, 1, GridBagConstraints.NONE);
        addComponent(content, saveCordsButton, 2, 0, 1, 1, 0, 1, GridBagConstraints.NONE);
        addComponent(content, controlPanel, 2, 1, 1, 1, 0, 1, GridBagConstraints.NONE);
        addComponent(content, previewPanel, 3, 0, 1, 3, 1, 1, GridBagConstraints.BOTH);
    }

    private JPanel initializePreviewPanel() {
        JPanel previewPanel = new JPanel(new BorderLayout());
        this.drawPanelController = new DrawPanelController();
        drawPanelController.initialize(previewPanel);
        drawPanelController.drawLine(LineFactory.getBasicLine());
        previewPanel.setPreferredSize(new Dimension(500, 500));
        this.lineDriverAdapter = new LineDriverAdapter(drawPanelController, LineFactory.getBasicLine(), "basic");
        return previewPanel;
    }

    private JPanel initializeSaveCancelPanel() {
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener((ActionEvent e) -> this.onSave());

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener((ActionEvent e) -> this.onClose());

        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.add(saveButton, BorderLayout.NORTH);
        controlPanel.add(cancelButton, BorderLayout.SOUTH);
        return controlPanel;
    }

    private JPanel initializeCordsPanel() {
        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);

        NumberFormatter numberFormatter = new NumberFormatter(format);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setAllowsInvalid(false);

        JFormattedTextField xTextField = new JFormattedTextField(numberFormatter);
        this.xTextField = xTextField;
        JFormattedTextField yTextField = new JFormattedTextField(numberFormatter);
        this.yTextField = yTextField;

        JLabel xLabel = new JLabel("X: ");
        JLabel yLabel = new JLabel("Y: ");

        JPanel xPanel = new JPanel(new BorderLayout());
        xPanel.add(xLabel, BorderLayout.WEST);
        xPanel.add(xTextField, BorderLayout.CENTER);

        JPanel yPanel = new JPanel(new BorderLayout());
        yPanel.add(yLabel, BorderLayout.WEST);
        yPanel.add(yTextField, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(xPanel, BorderLayout.NORTH);
        inputPanel.add(yPanel, BorderLayout.SOUTH);

        return inputPanel;
    }

    private JPanel initializeMoveCommandPanel() {
        JButton moveUpButton = new JButton("Move up");
        moveUpButton.addActionListener((ActionEvent e) -> this.onOrderChangeUp());
        JButton moveDownButton = new JButton("Move down");
        moveDownButton.addActionListener((ActionEvent e) -> this.onOrderChangeDown());
        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.add(moveUpButton, BorderLayout.NORTH);
        orderPanel.add(moveDownButton, BorderLayout.SOUTH);
        return orderPanel;
    }

    private JScrollPane initializeList() {
        commandJList = new JList<>();
        commandJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        commandJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        commandJList.setVisibleRowCount(-1);
        commandJList.addMouseListener(onListClick());

        JScrollPane listScrollPanel = new JScrollPane(commandJList);
        listScrollPanel.setViewportView(commandJList);
        return listScrollPanel;
    }

    private void onSaveCords() {
        int selectedIndex = commandJList.getSelectedIndex();
        Object x = xTextField.getValue();
        Object y = yTextField.getValue();
        if (x != null && y != null) {
            this.driverCommandList.get(selectedIndex).setPoint(new Point((Integer) x, (Integer) y));
            refreshWindow();
        }
    }

    private void clearInputData() {
        xTextField.setValue(null);
        yTextField.setValue(null);
    }

    private void onOrderChangeUp() {
        int selectedIndex = commandJList.getSelectedIndex();
        if (selectedIndex > 0) {
            Collections.swap(driverCommandList, selectedIndex, selectedIndex - 1);
            refreshWindow();
            commandJList.setSelectedIndex(selectedIndex - 1);
        }
    }

    private void onOrderChangeDown() {
        int selectedIndex = commandJList.getSelectedIndex();
        if (selectedIndex < driverCommandList.size() - 1) {
            Collections.swap(driverCommandList, selectedIndex, selectedIndex + 1);
            refreshWindow();
            commandJList.setSelectedIndex(selectedIndex + 1);
        }
    }

    private void onSave() {
        CommandsFeature.getDriverCommandManager().setCurrentCommand(compoundCommand);
        onClose();
    }

    private void onClose() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    private void onRemoveClick() {
        int selectedIndex = commandJList.getSelectedIndex();
        this.driverCommandList.remove(selectedIndex);
        refreshWindow();
        clearInputData();
    }

    private MouseAdapter onListClick() {
        return new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                JList list = (JList) mouseEvent.getSource();
                int itemIndex = list.locationToIndex(mouseEvent.getPoint());
                DriverCommand command = compoundCommand.getDriverCommands().get(itemIndex);
                xTextField.setValue(command.getPoint().getX());
                yTextField.setValue(command.getPoint().getY());
            }
        };
    }

    private void addComponent(Container container, Component component, int gridX, int gridY,
                              int gridWidth, int gridHeight, int weightX, int weightY, Integer gridBagConstraints) {
        GridBagConstraints gbc = new GridBagConstraints(gridX, gridY, gridWidth, gridHeight, weightX, weightY,
                GridBagConstraints.CENTER, gridBagConstraints, INSETS, 0, 0);
        container.add(component, gbc);
    }

    private void populateListData() {
        this.driverCommandList = compoundCommand.getDriverCommands();
        refreshWindow();
    }

    private void refreshWindow() {
        commandJList.setListData(driverCommandList.toArray());
        refreshPreviewPanel();
    }

    private void refreshPreviewPanel() {
        drawPanelController.clearPanel();
        driverCommandList.forEach(command -> {
            if(command instanceof OperateToCommand) {
                OperateToCommand operateToCommand = (OperateToCommand) command;
                lineDriverAdapter.operateTo(operateToCommand.getPosX(), operateToCommand.getPosY());
            } else if (command instanceof SetPositionCommand) {
                SetPositionCommand setPositionCommand = (SetPositionCommand) command;
                lineDriverAdapter.setPosition(setPositionCommand.getPosX(), setPositionCommand.getPosY());
            }
        });
    }

}
