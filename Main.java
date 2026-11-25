import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static boolean[] getRow(String line) {
        Scanner scanner = new Scanner(line);
        boolean[] row = new boolean[60];
        int minute = 0;

        while (scanner.hasNextBoolean()) {
            row[minute] = scanner.nextBoolean();
            minute++;
        }

        scanner.close();
        return row;
    }

    public static void main(String[] args) throws IOException {

        AppointmentBook[] schedules = new AppointmentBook[100];

        File f = new File("schedules.txt");

        Scanner s = new Scanner(f);

        int i = 0;

        while (s.hasNextLine() && i < schedules.length) {

            boolean[][] schedule = new boolean[8][60];

            int j = 0;

            while (j < schedule.length) {
                String line = s.nextLine();

                if (line.length() > 0) {

                    schedule[j] = getRow(line);
                    j++;
                }
            }

            schedules[i] = new AppointmentBook(schedule);
            i++;
        }
        s.close();

        f = new File("requests.txt");
        s = new Scanner(f);
        int meetings = 0;

        while (s.hasNextInt()) {
            
            int schedule = s.nextInt();

            int start = s.nextInt();

            int end = s.nextInt();

            int duration = s.nextInt();

            if (schedules[schedule].makeAppointment(start, end, duration)) {

                meetings++;

            }
        }

        s.close();

        System.out.println(meetings);
    }
}