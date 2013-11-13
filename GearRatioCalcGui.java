import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
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
    private JLabel tireLabel;
    private JLabel finalDriveLabel;
    private GearRatioCalc gearCalculator;

    /**
     * Constructor for a GearRatioCalcGui
     */
    public GearRatioCalcGui() {
        super("Gear Ratio Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(createJMenuBar());
        tireLabel = new JLabel("");
        finalDriveLabel = new JLabel("Final Drive: 4.3");
        updateTireSize();
        setLayout(new GridLayout(1, 2));
        add(createButtonPanel());
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(tireLabel);
        textPanel.add(finalDriveLabel);
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
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(changeTireSize);
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
    }

    public static void main(String[] args) {
        GearRatioCalcGui gui = new GearRatioCalcGui();
        gui.setVisible(true);
    }
}
