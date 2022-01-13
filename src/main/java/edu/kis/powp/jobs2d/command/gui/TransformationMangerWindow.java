package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.visitor.*;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TransformationMangerWindow extends JFrame implements WindowComponent {
    private static final int VERTICAL = 0;
    private static final int CANCEL = 2;

    public TransformationMangerWindow() {
        this.setTitle("Transformation Manager");
        this.setSize(400, 400);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JButton scaleButton = new JButton("Scale");
        scaleButton.addActionListener((ActionEvent e) -> this.scale());
        scaleButton.setActionCommand("scale");
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridy = 0;
        c.weighty = 1;
        content.add(scaleButton, c);

        JButton flipButton = new JButton("Flip");
        flipButton.addActionListener((ActionEvent e) -> this.flip());
        flipButton.setActionCommand("flip");
        c.weightx = 1;
        c.gridy = 1;
        c.weighty = 1;
        content.add(flipButton, c);

        JButton rotateButton = new JButton("Rotate");
        rotateButton.addActionListener((ActionEvent e) -> this.rotate());
        rotateButton.setActionCommand("rotate");
        c.weightx = 1;
        c.gridy = 2;
        c.weighty = 1;
        content.add(rotateButton, c);
    }

    private void scale() {
        JFrame f = new JFrame();
        SpinnerModel model = new SpinnerNumberModel(1.0, 0.0, 5.0, 0.1);
        JSpinner spinner = new JSpinner(model);
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(new Object[] { "Select factor: ", spinner });
        optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
        optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = optionPane.createDialog(f, "Transform settings");
        dialog.setVisible(true);

        Double factor = (Double) model.getValue();
        DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
        if(factor != null && command != null) {
            command.accept(new VisitorScale(factor));
        }
    }

    private void flip() {
        String[] options = { "Vertical", "Horizontal", "Cancel" };
        int chosen = JOptionPane.showOptionDialog(null, "Select flip axis", "Transform settings",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
        if(chosen != CANCEL && command != null) {
            VisitorCommand visitor;
            if(chosen == VERTICAL) visitor = new VisitorFlipX();
            else visitor = new VisitorFlipY();
            command.accept(visitor);
        }
    }

    private void rotate() {
        JFrame frame = new JFrame();
        JSlider slider = new JSlider(JSlider.HORIZONTAL, -360, 360, 0);
        slider.setMajorTickSpacing(30);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        JTextField field = new JTextField("0");
        field.setEditable(false);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(field);
        panel.add(slider);

        slider.addChangeListener(e -> field.setText("" + slider.getValue()));

        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(new Object[] { "Select degree: ", panel });
        optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
        optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);

        JDialog dialog = optionPane.createDialog(frame, "Transform settings");
        dialog.setSize(new Dimension(800, 200));
        dialog.setResizable(false);
        dialog.setVisible(true);

        int degree = slider.getValue();
        DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
        if(degree != 0 && command != null) {
            command.accept(new VisitorRotate(degree));
        }
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }
}