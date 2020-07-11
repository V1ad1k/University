
public class Task implements Comparable<Task> {

    private String Classical;
    private String Urgent;
    private String Preparing = "Preparing" ;
    private String InProgressing = "InProgressing" ;
    private String Finished = "Finished";
    private String Name;
    private String tryb;
    private String status;
    private String employee;
    private Boolean IsFinished = false;


    public Task(String Name, String tryb){
        this.Name = Name;
        this.tryb = tryb;
        this.status = InProgressing;
    }

    public Task(String Name, String tryb, Employee employee) {
        this.Name = Name;
        this.tryb = tryb;
        this.status = Preparing;
        this.employee = employee.getPESEL();
    }

    public String getName() {
        return Name;
    }

    public String getStatus() {
        return status;
    }

    public String getTryb() {
        return tryb;
    }

    public String getEmployee(){return employee;}

    public void setStatus(String status) {
        this.status = status;
    }

    protected Boolean todoTask(Task task) {
        if (this.status == Preparing) {
            return !task.IsFinished;
        }
        return task.IsFinished;
    }

    protected void changeStatus(String newStatus) throws StatusException {
        String setStatus = newStatus.toUpperCase();
        if (!setStatus.equals(InProgressing.toString()) || !setStatus.equals(Preparing.toString())
                || !setStatus.equals(Finished.toString())) {
        	 System.out.println("Illegal status exception");;
        } else {
            this.status = setStatus;
        }
    }

    protected void changeStatus() {
        if (this.employee != null) this.setStatus(Preparing);
        if (todoTask(this)) this.setStatus(Finished);
    }

    @Override
    public int compareTo(Task task) {
        if (this.tryb == task.tryb) return 0;
        if (this.tryb == Urgent && task.tryb == Classical) return 1;
        return -1;
    }

    public void getState() {
        System.out.print("Name: " + getName() + "\t" + "Tryb: " + getTryb() + "\t" + "Status: "
                + getStatus() + "\t" + "Employee: " + getEmployee() + "\t" );
    }
}

