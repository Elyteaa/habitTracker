import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class HabitTrackerApp {
    private Habits habitInfo = new Habits();
    
    private JTextField currentWeekInput = new JTextField(2);
    private List<JTextField> habitNameFields = new ArrayList<>();
    private List<List<JCheckBox>> habitMatrixFields = new ArrayList<>();
    private JButton saveButton = new JButton("Save");
    
    HabitTrackerApp()
    {
        buildUI();
        addListeners();    
    }

    private void buildUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        JPanel weekInfo = new JPanel(new FlowLayout());
        JLabel currentWeek = new JLabel("Current Week Number: ");
        currentWeekInput.setText(String.valueOf(habitInfo.getCurrentWeek()));

        weekInfo.add(currentWeek);
        weekInfo.add(currentWeekInput);
        
        frame.add(weekInfo, BorderLayout.NORTH);

        JPanel habitsList = new JPanel();
        habitsList.setLayout(new BoxLayout(habitsList, BoxLayout.PAGE_AXIS));
        for (int i = 0; i < 10; i++)
        {
            JPanel habitEntry = new JPanel(new FlowLayout());
            
            JTextField habitName = new JTextField(habitInfo.getHabits(i), 20);
            habitNameFields.add(habitName);
            habitName.setMaximumSize(new Dimension(100, 50));
            
            habitEntry.add(habitName);

            List<JCheckBox> weekCheckFields = new ArrayList<>();
            for (int j = 0; j < 7; j++)
            {
                JCheckBox dailyCheck = new JCheckBox("", habitInfo.getHabitTrackerMatrix(i, j));
                weekCheckFields.add(dailyCheck);
                habitEntry.add(dailyCheck);
            }

            habitMatrixFields.add(weekCheckFields);
            habitsList.add(habitEntry);
        }

        frame.add(habitsList);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        saveButton.setPreferredSize(new Dimension(80, 40));
        buttonPanel.add(saveButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void addListeners() {
        saveButton.addActionListener(e -> saveHabits());
    }

    private void saveHabits() {
        int newWeek = Integer.parseInt(currentWeekInput.getText());
        String[] newHabits = new String[10];
        boolean[][] newHabitTrackerMatrix = new boolean[7][10];

        for (int i = 0; i < habitNameFields.size(); i++) {
            String newHabitName = habitNameFields.get(i).getText();
            newHabits[i] = newHabitName;
        }

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 7; j++) {
                newHabitTrackerMatrix[j][i] = habitMatrixFields.get(i).get(j).isSelected();
            }
        }

        habitInfo.saveData(newWeek, newHabits, newHabitTrackerMatrix);
    }

    public static void main(String a[]) { 
        new HabitTrackerApp(); }
}