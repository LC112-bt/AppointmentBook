public class AppointmentBook {

    private boolean[][] schedule;

    public AppointmentBook(boolean[][] schedule) {
        this.schedule = schedule;
    }

    private boolean isMinuteFree(int period, int minute) {
        return schedule[period - 1][minute];
    }

    public int findFreeBlock(int period, int duration) {
        int block = 0;

        for (int minute = 0; minute < 60; minute++) {
            if (isMinuteFree(period, minute)) {
                block++;
                if (block == duration) {
                    return minute - duration + 1;
                }

            } else {
                block = 0;

            }
        }

        return -1;
    }

    public boolean makeAppointment(int startPeriod, int endPeriod, int duration) {
        for (int period = startPeriod; period <= endPeriod; period++) {

            int freeBlock = findFreeBlock(period, duration);

            if (freeBlock > -1) {
                reserveBlock(period, freeBlock, duration);
                return true;
            }
        }

        return false;
    }

    private void reserveBlock(int period, int startMinute, int duration) {

        for (int minute = startMinute; minute < startMinute + duration; minute++) {

            schedule[period - 1][minute] = false;
            
        }
    }
}