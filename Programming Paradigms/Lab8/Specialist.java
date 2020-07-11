public class Specialist extends Employee{
    int Bonus;
    int PercentofIncrease;

       public Specialist(String Name, String Surname, String PESEL, int Salary, int increaseofSalary,
                          int Bonus, int PercentofIncrease) {
        super(Name, Surname, PESEL, Salary, increaseofSalary);
        this.Bonus = Bonus;
        this.PercentofIncrease = PercentofIncrease;
    }

    public int getBonus() {
        return Bonus;
    }

    public double getPercentofIncrease(){

        return PercentofIncrease;
    }

    public void SetPremia(int Premia) {
        this.Bonus = Premia;
    }

    public void SetProcentPodwyzkiPrem(int ProcentPodwyzkiPrem){

        this.PercentofIncrease = ProcentPodwyzkiPrem;
    }

    protected int dodajProcentPodwyzkiPrem(int value) {
        return PercentofIncrease = value;
    }

    public void getStan() {
        super.getStan();
        System.out.println( "Bonus : " + getBonus() + "\t" + "% of increase Salary: " + getPercentofIncrease() + "\t");
    }
}












