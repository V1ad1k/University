import java.util.Locale;

public class Process {
    public int id;
    public double time;
    public double timeLeft;
    Status status;

    public Process(int id, double time) {
        this.id = id;
        this.time = time;
        this.timeLeft = time;
        if (timeLeft == 0.0) {
            this.status = Status.TERMINATED;
        } else {
            this.status = Status.CREATED;
        }
    }

    public void decreaseTimeLeft(double timePassed) {
        this.timeLeft -= timePassed;
        if (this.timeLeft <= 0.0){
            this.timeLeft = 0.0;
            this.status = Status.TERMINATED;
        }
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getTime() {
        return time;
    }

    public double getTimeLeft() {
        return timeLeft;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        String message = "ID: %d, %fu (%fu left), %s";
        return String.format(Locale.US, message, id, time, timeLeft, status);
    }
}
