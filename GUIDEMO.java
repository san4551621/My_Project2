
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUIDEMO {
    public static void main(String[] args) {
        Abc a=new Abc();
        /*a.setVisible(true);
        //a.show();
        a.setSize(400, 400);*/
    }
}
class Abc extends JFrame{
    public Abc(){
        
        JLabel s=new JLabel("To Do List");
        JLabel t=new JLabel("Enter the task");
        add(s);
        add(t);
        
        setLayout(new FlowLayout());
        setVisible(true);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
