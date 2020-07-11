package Task2;

public class Disk implements Cloneable {
    // Representation of a disk
    private int headPos;        // Position of the disk head
    private int bound;          // Upper bound of number of cylinders
    private boolean goesRight;  // Keeps track of the disk head going left or right
 
    public Disk(int headPos, int bound) {
        this.headPos = headPos;
        this.bound = bound;
        goesRight = true;
    }
 
    //Setters and getters
    public boolean doesGoRight() {
        return goesRight;
    }
 
    public void setGoesRight(boolean goesRight) {
        this.goesRight = goesRight;
    }
 
    public int getHeadPos() {
        return headPos;
    }
 
    public void setHeadPos(int headPos) {
        this.headPos = headPos;
    }
 
    public int getBound() {
        return bound;
    }
 
    public void setBound(int bound) {
        this.bound = bound;
    }
 
    public int posDiff(int pos) {
        // Returns the distance between disk head's position and passed value
        if (pos > bound || pos < 0) {
            throw new IndexOutOfBoundsException(); //TODO: Unhandled as of now.
        }
        return Math.abs(headPos - pos);
    }
 
    @Override
    public String toString() {
        String output = "Current position of head: " + headPos + "\n";
        output += "Cylinders' ID range: 0-" + bound;
        return output;
    }
 
    @Override
    public Object clone() throws CloneNotSupportedException {
        // Using Java's default implementation of cloning. Needed in order to be able to reset the simulator.
        return super.clone();
    }
}