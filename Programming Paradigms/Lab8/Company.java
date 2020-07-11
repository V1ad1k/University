import java.util.ArrayList;
import java.util.Collections;


public class Company {
    private ArrayList<Employee> listofEmployees = new ArrayList<>();
    private ArrayList<Task> listofTasks = new ArrayList<>();

    protected void addEmployee(Employee os) {
    	listofEmployees.add(os);
    }

    protected void addTask(Task task) {
        listofTasks.add(task);
    }

    protected void SearchingofPESEL(String PESEL) {
        for (int i = 0; i < listofEmployees.size(); i++) {
            if (listofEmployees.get(i).getPESEL() == PESEL) {
            	listofEmployees.get(i).getStan();
            }
        }
    }

    protected void changePercent(int procent) {
        for (Employee os : listofEmployees) {
            os.setPercentofIncrease(0 + procent);
        }
        for (int i = 0; i < listofEmployees.size(); i++) {
        	listofEmployees.get(i).getStan();
            System.out.println();
        }
    }

    public void DisplayEmployees() {
        for (int i = 0; i < listofEmployees.size(); i++) {
        	listofEmployees.get(i).getStan();
            System.out.println();
        }
    }

    public void ShowTasks() {
        for (int j = 0; j < listofTasks.size(); j++) {
            listofTasks.get(j).getState();
            System.out.println();
        }
    }

    protected void FilterofName(String Name) {
        for (int i = 0; i < listofTasks.size(); i++) {
            if (listofTasks.get(i).getName() == Name) {
                Collections.sort(listofTasks, new TryBComparator());
                listofTasks.get(i).getState();
                System.out.println();
            }
        }
    }

    public void FilterofStatus(String Status) {
        for (int i = 0; i < listofTasks.size(); i++) {
            if (listofTasks.get(i).getStatus() == Status) {
                Collections.sort(listofTasks, new TryBComparator());
                listofTasks.get(i).getState();
                System.out.println();
            }
        }
    }

    public void FilterofEmployees(String PESEL) {
        for (int i = 0; i < listofTasks.size(); i++) {
            if (listofTasks.get(i).getEmployee() == PESEL) {
                Collections.sort(listofTasks, new TryBComparator());
                listofTasks.get(i).getState();
                System.out.println();
            }
        }
    }
}