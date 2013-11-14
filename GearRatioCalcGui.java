import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * GearRatioCalcGui creates a GUI for a GearRatioCalc
 *
 * @author  Thomas Coe
 * @version 1.0 2013-11-13
 */
@SuppressWarnings("serial")
public class GearRatioCalcGui extends JFrame {

    private Tire tireSize;
    private double[] ratios = {3.136, 1.888, 1.330, 1.0, 0.814};
    private double dRatio = 4.3;
    private JLabel tireLabel;
    private JLabel finalDriveLabel;
    private JLabel ratioLabel;
    private GearRatioCalc gearCalculator;

    /**
     * Constructor for a GearRatioCalcGui
     */
    public GearRatioCalcGui() {
        super("Gear Ratio Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(createJMenuBar());
        setLayout(new GridLayout(1, 2));

        tireLabel = new JLabel("Tire Size: null");
        finalDriveLabel = new JLabel("Final Drive: " + dRatio);
        ratioLabel = new JLabel("Gear Ratios: " + ratios);

        updateTireSize();

        add(createButtonPanel());

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(tireLabel);
        textPanel.add(finalDriveLabel);
        textPanel.add(ratioLabel);
        add(textPanel);

        pack();
    }

    /**
     * Create a menu bar
     *
     * @return A JMenuBar of menu options
     */
    private JMenuBar createJMenuBar() {
        JMenuItem changeTireSize = new JMenuItem("Change Tire Size");
        changeTireSize.addActionListener(new TireChangeListener());
        JMenu options = new JMenu("Options");
        options.add(changeTireSize);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(options);
        return menuBar;
    }

    /**
     * Creates a button panel that will be on the left size and hold buttons
     * for updating the values of each field.
     *
     * @return a new JPanel with the buttons
     */
    private JPanel createButtonPanel() {
        JButton changeTireSize = new JButton("Change Tire Size");
        changeTireSize.addActionListener(new TireChangeListener());
        JButton calculateSpeed = new JButton("Calculate speeds at a given RPM");
        calculateSpeed.addActionListener(new SpeedCalculateListener());
        JButton calculateRpms = new JButton("Calculate RPMs at a given speed");
        calculateRpms.addActionListener(new RpmCalculateListener());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(changeTireSize);
        buttonPanel.add(calculateSpeed);
        buttonPanel.add(calculateRpms);
        return buttonPanel;
    }

    /**
     * Prompts the user for a new TireSize.
     *
     * @author  Thomas Coe
     * @version 1.0 2013-11-13
     */
    private class TireChangeListener implements ActionListener {

        /**
         * actionPerformed method for the listener
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            updateTireSize();
        }
    }

    private class SpeedCalculateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            speedsAtRpm();
        }
    }

    private class RpmCalculateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            rpmsAtSpeed();
        }
    }

    /**
     * Prompts the user for a Tire size, used to create a new Tire object
     */
    private void updateTireSize() {
        String size = JOptionPane.showInputDialog(null,
                "Please enter a Tire Size", "205/40R17");
        if (size != null) {
            tireSize = new Tire(size);
            tireLabel.setText("Tire Size: " + tireSize);
        }
        gearCalculator = new GearRatioCalc(ratios, dRatio, tireSize);
    }

    /**
     * Prompt user for input to calculate all speeds at a given RPM
     */
    private void speedsAtRpm() {
        String rpmString = JOptionPane.showInputDialog(null,
                "Please input the RPM to calculate for", "2000 rpm");
        if (rpmString != null) {
            Scanner rpmFinder = new Scanner(rpmString);
            try {
                double rpm = rpmFinder.nextDouble();
                //debugging for now
                gearCalculator.RPMcalc((int)rpm);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "RPM not found", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Prompt the user for input to calculate all RPMs at a given speed
     */
    private void rpmsAtSpeed() {
        String speedString = JOptionPane.showInputDialog(null,
                "Please input the speed to calculate for", "60 mph");
        if (speedString != null) {
            Scanner speedFinder = new Scanner(speedString);
            try {
                double mph = speedFinder.nextDouble();
                //debugging for now
                gearCalculator.MPHcalc((int)mph);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "MPH not found", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        GearRatioCalcGui gui = new GearRatioCalcGui();
        gui.setVisible(true);
    }
}
