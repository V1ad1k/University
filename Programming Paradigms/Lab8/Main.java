public class Main {

    public static void main(String[] args) throws StatusException {
        Company f = new Company();
        System.out.println("The first thing is creating Employees: ");
        Employee os1 = new Employee("Nadia", "Dazhunts", "135792468", 1100, 10);
        Employee os2 = new Specialist("Mgr and just Vlad", "Gavryliuk", "&1", 5550, 5, 777, 0);
        Employee os3 = new Specialist("Darya", "Belanovich", "&2", 4500, 0, 100, 20);
        Employee os4 = new Employee("Yehor", "Brilov", "9876543210", 1000, 0);
        f.addEmployee(os1);
        f.addEmployee(os2);
        f.addEmployee(os3);
        f.addEmployee(os4);

        f.DisplayEmployees();

        System.out.println();
        System.out.println("Searching by Pesel which equals to &1 : ");
        f.SearchingofPESEL("&1");

        System.out.println("\n");
        System.out.println("Increase of Salary for everyone : ");
        f.changePercent(10);

        System.out.println("\n");
        System.out.println("Creating list with Employees and upload them");
        Task zad1 = new Task("Graph", "Urgent", os1);
        Task zad2 = new Task("Print plan", "Classical", os2);
        Task zad3 = new Task("Graph", "Classical");
        Task zad4 = new Task("Checking size", "Urgent");


        f.addTask(zad1);
        f.addTask(zad2);
        f.addTask(zad3);
        f.addTask(zad4);

        f.ShowTasks();

        System.out.println("\n");
        System.out.println("Filtring by Name : ");
        f.FilterofName("Graph");

        System.out.println("\n");
        System.out.println("Filtring by Status : ");
        f.FilterofStatus("In Progressing");


        System.out.println("\n");
        System.out.println("Filtring by Status : ");
        f.FilterofEmployees("&1");

        System.out.println("\n");
        System.out.println("Trying to change status of task : ");
        Task z = new Task("Print plan", "Classical", os2);
        z.todoTask(zad2);
        z.changeStatus("finished");
        zad2.getState();





}}
