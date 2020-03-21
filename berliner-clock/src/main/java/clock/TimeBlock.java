package clock;

/**
 * Created by Iryna on 22.01.2020.
 */
public class TimeBlock {
    private int line;
    private boolean lighted;
    private String color;
    
    public TimeBlock() {
    }
    
    public TimeBlock(int line, boolean lighted, String color) {
        this.line = line;
        this.lighted = lighted;
        this.color = color;
    }

    public void setLine(int line) {
        this.line = line;
    }
    public void setLighted(boolean lighted) {
        this.lighted = lighted;
    }
    public void setColor(String color) {
        this.color = color;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
       if(super.equals(obj)){
           return true;
       }
        if(getClass()!=obj.getClass()){
           return false;
       }
        TimeBlock otherTimeBlock = (TimeBlock)obj;
        return line == otherTimeBlock.line && lighted == otherTimeBlock.lighted && color.equals(otherTimeBlock.color);
    }

    @Override
    public String toString() {
        return line + ", " + lighted + ", " + color + "|| "; 
    }
}
