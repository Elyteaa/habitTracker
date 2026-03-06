import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

class Habits {
    private int currentWeek;
    private String[] habit = new String[10];
    private boolean[][] habitTrackerMatrix = new boolean[7][10];

    Habits() {
        // some mock values for dev/init testing
        habit[0] = "test1";
        habitTrackerMatrix[0][0] = true;
        habitTrackerMatrix[2][0] = true;
        habitTrackerMatrix[4][0] = true;

        // TODO: check if there is saved info for the week

        // if there is not, populate with today's week number
        GregorianCalendar gc = new GregorianCalendar();
        int day = 0;
        gc.add(Calendar.DATE, day);
        currentWeek = gc.get(Calendar.WEEK_OF_YEAR);
    }

    protected void readData() {
        // TODO: read saved data and populate it
    }

    protected void saveData(int newWeek, String[] newHabits, boolean[][] newTrackerMatrix) {
        currentWeek = newWeek;
        habit = newHabits;
        habitTrackerMatrix = newTrackerMatrix;

        try {
            File dataFile = new File("dataFile.csv");
            PrintWriter printWriter = new PrintWriter(dataFile);
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(String.valueOf(newWeek));
            stringBuilder.append('\n');

            for (int i = 0; i < newHabits.length; i++){
                // skip if no habit name entered
                if ((newHabits[i] != null) && !newHabits[i].trim().isEmpty()) {
                    stringBuilder.append(newHabits[i]);
                } else {
                    continue;
                }
                
                for (int j = 0; j < 7; j++) {
                    stringBuilder.append(',');
                    stringBuilder.append(newTrackerMatrix[j][i]);
                }
            
                stringBuilder.append('\n');
            }

            stringBuilder.append('\n');

            printWriter.write(stringBuilder.toString());
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected int getCurrentWeek() {
        return currentWeek;
    }

    protected String[] getHabits() {
        return habit;
    }

    protected String getHabits(int index) {
        return habit[index];
    }

    protected boolean[][] getHabitTrackerMatrix() {
        return habitTrackerMatrix;
    }

    protected boolean getHabitTrackerMatrix(int habitIndex, int weekDay) {
        return habitTrackerMatrix[weekDay][habitIndex];
    }

    public static void main(String[] args){
    }
}