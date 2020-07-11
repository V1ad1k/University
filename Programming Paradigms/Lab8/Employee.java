import java.io.Serializable;


@SuppressWarnings("serial")
public class Employee implements Serializable {
    String Name;
    String Surname;
    String PESEL;
    double Salary;
    double IncreaseSalary;

    public Employee(String Name, String Surname, String PESEL, int Salary, int IncreaseSalary){
        this.Name = Name;
        this.Surname = Surname;
        this.PESEL = PESEL;
        this.Salary = Salary;
        this.IncreaseSalary = IncreaseSalary;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getPESEL() {
        return PESEL;
    }

    public double getSalary() {

        return Salary;
    }
    public double getPercentofIncrease(){

        return IncreaseSalary;
    }

    public void setName(String Imie) {

        this.Name = Imie;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public void setPESEL(String PESEL) {

        this.PESEL = PESEL;
    }

    public void setSalary(int salary) {

        this.Salary = salary;
    }

    public void setPercentofIncrease(int IncreaseSalary){
        this.IncreaseSalary = IncreaseSalary;
    }

    @Override
    public boolean equals(Object os) {
        Employee other = (Employee) os;
        if (PESEL.equals(other.PESEL)) return true;
        else return false;
    }

    protected double addPercentofIncrease(double wartosc) {
        return this.IncreaseSalary = wartosc;
    }

    @Override
    public String toString() {
        return " Name=" + Name + ", Surname=" + Surname + ", PESEL=" + PESEL + ", Salary=" + Salary +
                ", % of IncreaseSalary=" + IncreaseSalary  ;
    }

    public void getStan() {
        System.out.print("Name: " + getName() + "\t" + "Surname: " + getSurname() + "\t" + "PESEL: "
                + getPESEL() + "\t" + "Salary: " + getSalary() + "\t" + "% of IncreaseSalary: " + getPercentofIncrease() + "\t" + "\n");
    }
}

