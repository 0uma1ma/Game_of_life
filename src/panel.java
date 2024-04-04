import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class panel extends JPanel implements ActionListener , MouseListener, MouseMotionListener,KeyListener {
    int PWidth = 500;
    int PHeight = 450;
    int CSize = 12;
    boolean[][] cells = new boolean [PWidth/CSize][PHeight/CSize];
    boolean[][] futureGeneration = new boolean [PWidth/CSize][PHeight/CSize];
    boolean start = true;
    int delay =100;
    Timer timer;
    public panel(){
        this.setSize(PWidth,PHeight);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(PWidth,PHeight));
        this.setFocusable(true);
        timer =new Timer(delay, this);
        timer.start();
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        grid(g);
        if(start) generate();
        display(g);
    }

    private void grid(Graphics g){
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < cells.length; i++) {
            g.drawLine(0,i*CSize,PWidth,i*CSize);
            g.drawLine(i*CSize,0,i*CSize,PHeight);
        }
    }
    private void generate(){
        for(int i =0;i < cells.length;i++){
            for (int j = 0; j <cells[i].length ; j++) {
                if(Math.random()<0.3){
                    futureGeneration[i][j] = true;
                }
            }
        }
        start = false;
    }
    private void clear(){
        for(int i =0;i < cells.length;i++){
            for (int j = 0; j <cells[i].length ; j++) {
                futureGeneration[i][j] = false;
            }
        }
    }
    private void display(Graphics g){
        g.setColor(Color.white);
        copy();
        for(int i =0;i < cells.length;i++){
            for (int j = 0; j <cells[i].length ; j++) {
                if(cells[i][j]) {
                    g.fillRect(i * CSize, j * CSize, CSize, CSize);
                }
            }
        }
    }
    public int checkAlive(int i,int j){
        int aliveCells=0;

        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <=1 ; l++) {
                if(k!=0 || l!=0){
                    if(cells[(i+cells.length-k)%cells.length][(j+cells[i].length-l)%cells[i].length]){
                        aliveCells++;
                    }
                }
            }
        }
        return aliveCells;
    }
    private void copy(){
        for(int i =0;i < cells.length;i++){
            System.arraycopy(futureGeneration[i], 0, cells[i], 0, cells[i].length);
        }
    }
    public void actionPerformed(ActionEvent e) {
        int aliveCells;
        for(int i =0;i < cells.length;i++){
            for (int j = 0; j <cells[i].length ; j++) {
                aliveCells=checkAlive(i,j);
                if(cells[i][j]) {
                    if (aliveCells==3 || aliveCells==2){
                        futureGeneration[i][j]=true;
                    }
                    else futureGeneration[i][j]=false;
                }
                else{
                    if(aliveCells==3){
                        futureGeneration[i][j]=true;
                    }
                    else futureGeneration[i][j]=false;
                }
            }
        }
        repaint();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / CSize;
        int y = e.getY() / CSize;
        if (x < cells.length && x > 0 && y < cells[cells.length - 1].length && y > 0) {
            if (cells[x][y]) {
                futureGeneration[x][y] = false;
            } else if (!cells[x][y]) {
                futureGeneration[x][y] = true;
            }
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
        int x = e.getX() / CSize;
        int y = e.getY() / CSize;
        if (x < cells.length && x > 0 && y < cells[cells.length - 1].length && y > 0) {
            if (cells[x][y]) {
                futureGeneration[x][y] = false;
            } else if (!cells[x][y]) {
                futureGeneration[x][y] = true;
            }
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case 80: timer.stop();//pause//p
                break;
            case 82: timer.start();//resume//r
                break;
            case 67: clear();//clear//c
                break;
            case 71:generate();//gr
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
