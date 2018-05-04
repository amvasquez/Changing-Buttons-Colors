/*
 
Andrea Vasquez
HW4
ButtonHW4

 */

import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonHW4 {
   
    public static void main(String[] args) {
        JFrame jf = new JFrame("Homework 4!! ");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(800,800);
        jf.setResizable(true);
        
        JPanel jp = new JPanel();
        jf.add(jp);
        GridLayout grid = new GridLayout(0,2,2,2);
        jp.setLayout(grid);
        
        CstmButton [] c = new CstmButton[8];
        Random rand = new Random();
        
        for(int i=0;i<8;i++){
            CstmButton btn = new CstmButton("Button " + i);
            btn.setBackground(new Color(rand.nextInt()));
            btn.addActionListener(new MyButtonAction());
            jp.add(btn);
            c[i] = btn;
        }
        
        jf.add(jp);
        jf.setVisible(true);
        
        UpdateThread t = new UpdateThread(c);
        t.start();
    }
}

@SuppressWarnings("serial")
class CstmButton extends JButton {
    boolean paused;
    CstmButton(String text) { 
        super(text);
        paused = false;
    }
    public void setPaused(boolean p) {
         paused = p; 
        }
    public boolean getPaused() { 
        return paused;
     }
}

class UpdateThread extends Thread {
    CstmButton [] btn;
    
    UpdateThread(CstmButton[] newButton){
        btn = newButton;
    }

    @Override
    public void run(){
        boolean p;
        while(true){
            for (int i = 0; i < btn.length; i++){
                p = btn[i].getPaused();
                if (!p){ 
                  changeColor(btn[i]);  
                }
            }
            try{ //does it every second
                    sleep(1000);
            } catch(InterruptedException e){
                System.out.println("Thread interrupted.");
            }
        }
    }

    static synchronized void changeColor(JButton btn){ //changes color of button
        Random rnd = new Random();
        btn.setBackground(new Color(rnd.nextInt()));
    }
}


class MyButtonAction implements ActionListener{
    
    @Override
    public void actionPerformed(ActionEvent e) {
        CstmButton button = (CstmButton)e.getSource();
        
        boolean state = button.getPaused();
        if (!state){
            button.setPaused(true); // puts a pause 
        }
        else{
            button.setPaused(false); //gets rid of pause
        }  
    }

}




    
