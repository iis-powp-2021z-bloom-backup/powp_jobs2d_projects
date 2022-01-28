package edu.kis.powp.jobs2d.command.gui;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.modifier.ModifierCache;
import edu.kis.powp.jobs2d.command.modifier.model.ModifierCommand;
import edu.kis.powp.jobs2d.command.modifier.VisitorCache;
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
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.Collections;

public class ComplexCommandEditorWindow extends JFrame implements WindowComponent {

    private static final Insets INSETS = new Insets(0, 0, 0, 0);

    private final VisitorCache visitorCache;
    private final ModifierCache modifierCache;

    private JList<Object> commandJList;
    private CompoundCommand compoundCommand;
    private JFormattedTextField xTextField;
    private JFormattedTextField yTextField;
    private LineDriverAdapter lineDriverAdapter;
    private DrawPanelController drawPanelController;

    public ComplexCommandEditorWindow() {
        this.visitorCache = new VisitorCache();
        this.modifierCache = ModifierCache.getInstance();
        initializeView();
    }

    public void updateCurrentCommand() {
        DriverCommandManager driverCommandManager = CommandsFeature.getDriverCommandManager();
        this.compoundCommand = (CompoundCommand) driverCommandManager.getCurrentCommand();
        populateListData();
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }

    private void initializeView() {
        this.setTitle("Complex command editor");
        this.setSize(600, 400);

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
        int itemIndex = commandJList.getSelectedIndex();
        Object x = xTextField.getValue();
        Object y = yTextField.getValue();
        if (x != null && y != null) {
            ModifierCommand modifierCommand = modifierCache.getModifierCommands().get(itemIndex);
            modifierCommand.setX((Integer) x);
            modifierCommand.setY((Integer) y);
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
            Collections.swap(modifierCache.getModifierCommands(), selectedIndex, selectedIndex - 1);
            refreshWindow();
            commandJList.setSelectedIndex(selectedIndex - 1);
        }
    }

    private void onOrderChangeDown() {
        int selectedIndex = commandJList.getSelectedIndex();
        if (selectedIndex < modifierCache.getModifierCommands().size() - 1) {
            Collections.swap(modifierCache.getModifierCommands(), selectedIndex, selectedIndex + 1);
            refreshWindow();
            commandJList.setSelectedIndex(selectedIndex + 1);
        }
    }

    private void onSave() {
        CommandsFeature.getDriverCommandManager().setCurrentCommand(modifierCache.getCachedDriverCommands());
        onClose();
    }

    private void onClose() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    private void onRemoveClick() {
        int selectedIndex = commandJList.getSelectedIndex();
        modifierCache.getModifierCommands().remove(selectedIndex);
        refreshWindow();
        clearInputData();
    }

    private MouseAdapter onListClick() {
        return new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                JList list = (JList) mouseEvent.getSource();
                int itemIndex = list.locationToIndex(mouseEvent.getPoint());
                ModifierCommand modifierCommand = modifierCache.getModifierCommands().get(itemIndex);
                xTextField.setValue(modifierCommand.getX());
                yTextField.setValue(modifierCommand.getY());
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
        compoundCommand.getDriverCommands().forEach(command -> command.accept(visitorCache));
        refreshWindow();
    }

    private void refreshWindow() {
        commandJList.setListData(modifierCache.getModifierCommands().toArray());
        refreshPreviewPanel();
    }

    private void refreshPreviewPanel() {
        drawPanelController.clearPanel();
        modifierCache.getCachedDriverCommands().execute(lineDriverAdapter);
    }

}
