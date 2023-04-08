
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

class WaterLevelObserver extends JFrame {//super class

    public void update(int waterLevel) {
        // nothing
    }

}

class DisplayWindow extends WaterLevelObserver {

    private JLabel displayLabel;

    DisplayWindow() {
        setSize(300, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Display Window");
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        displayLabel = new JLabel("0");
        displayLabel.setFont(new Font("", 1, 25));

        add(displayLabel);
        setVisible(true);

    }

    public void update(int waterLevel) {
        displayLabel.setText(waterLevel + "");// Integer.toString(waterLevel);
    }
}

class AlarmWindow extends WaterLevelObserver {

    private JLabel alarmLabel;

    AlarmWindow() {
        setSize(300, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Alarm Window");
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        alarmLabel = new JLabel("OFF");
        alarmLabel.setFont(new Font("", 1, 25));

        add(alarmLabel);
        setVisible(true);

    }

    public void update(int waterLevel) {
        alarmLabel.setText(waterLevel >= 50 ? "ON" : "OFF");
    }
}

class SplitterWindow extends WaterLevelObserver {

    private JLabel splitterLabel;

    SplitterWindow() {
        setSize(300, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Splitter Window");
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        splitterLabel = new JLabel("Splitter OFF");
        splitterLabel.setFont(new Font("", 1, 25));

        add(splitterLabel);
        setVisible(true);

    }

    public void update(int waterLevel) {
        splitterLabel.setText(waterLevel >= 75 ? "Splitter ON" : "Splitter OFF");
    }
}

class WaterTankWindow extends JFrame {

    private WaterLevelObserver alarmWindow;
    private WaterLevelObserver displayWindow;
    private WaterLevelObserver splitterWindow;

    private JSlider waterLevelSlider;

    WaterTankWindow() {
        setSize(300, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Water Tank Window");
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        waterLevelSlider = new JSlider(JSlider.VERTICAL, 0, 100, 0);
        waterLevelSlider.setFont(new Font("", 1, 25));
        waterLevelSlider.setMajorTickSpacing(25);
        waterLevelSlider.setMinorTickSpacing(5);
        waterLevelSlider.setPaintLabels(true);

        waterLevelSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                int waterLevel = waterLevelSlider.getValue();
                alarmWindow.update(waterLevel);
                displayWindow.update(waterLevel);
                splitterWindow.update(waterLevel);
            }
        });

        add(waterLevelSlider);

    }

    public void addAlarmWindow(AlarmWindow alarmWindow) {
        this.alarmWindow = alarmWindow;
    }

    public void addDisplayWindow(DisplayWindow displayWindow) {
        this.displayWindow = displayWindow;
    }

    public void addSplitterWindow(SplitterWindow splitterWindow) {
        this.splitterWindow = splitterWindow;
    }
}

class Ijse {

    public static void main(String[] args) {
        WaterTankWindow wt = new WaterTankWindow();
        wt.addAlarmWindow(new AlarmWindow());
        wt.addDisplayWindow(new DisplayWindow());
        wt.addSplitterWindow(new SplitterWindow());
        wt.setVisible(true);
    }
}
