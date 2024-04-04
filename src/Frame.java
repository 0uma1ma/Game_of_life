import javax.swing.JFrame;
public class Frame extends JFrame {
    public Frame(){
        this.add(new panel());
        this.setTitle("Game of Life");
        this.setSize(510,490);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}

