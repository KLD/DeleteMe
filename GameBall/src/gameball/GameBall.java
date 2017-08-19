/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameball;

import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javafx.embed.swing.JFXPanel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author User
 */
public class GameBall extends JPanel implements ActionListener,MouseMotionListener,KeyListener{
    
    public final static int WIDTH=700;
    public final static int HIEGHT=700;
    
    int Xbat=350;
    int Ybat=670;
    
    int XBall=50;
    int YBall=50;
    
    int dx=10;
    int dy=15;
    
    int Score =0;
   // int speed=20;
    Rectangle bat = new Rectangle(Xbat,Ybat,100,10);
    Rectangle ball = new Rectangle(XBall,YBall,30,30);
    
    boolean start=true;
    boolean gameOver=false;
    
    int speed=35;
    
    Timer t;

    public GameBall()
    {
        t = new Timer(10,this);
        t.start();
    }
   
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
       
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 700, 700);
        
        if(start)
        {
            g.setColor(Color.WHITE);
            g.drawString("Score :"+Score,20,50);
            g.fillOval(ball.x ,ball.y, ball.width,ball.height);
            g.fillRect(bat.x, bat.y, bat.width, bat.height);
        }

        //draw over 
        if(gameOver)
        {
            g.setColor(Color.WHITE);
            g.drawString("Game Over ", getWidth()/2-20, getHeight()/2);
            g.drawString("Score :  "+Score, getWidth()/2-20, getHeight()/2+20);
            g.drawString("Press Space to Play Again", getWidth()/2-60, getHeight()/2+40);
        } 
     }
    
    public void BallMove(){

          ball.x+=dx;
          ball.y+=dy;
          if(ball.x> getWidth()-50){
          dx=-dx;
          }
          if(ball.x<0){
          dx=-dx;
          }
            if(ball.y> getHeight()-50){
            gameOver=true;
            start=false;
            //t.stop();
          }
          if(ball.y<50){
          dy=-dy;
          }
          if(ball.intersects(bat)){
          dy=-dy;
          Score+=10;
          }
        }
  
    public static void main(String[] args) {
       
        JFrame frame = new JFrame(); 
        frame.setSize(WIDTH, HIEGHT);
        frame.setTitle("Game Ball");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        GameBall game =  new GameBall();
        
        frame.addMouseMotionListener(game);
        frame.addKeyListener(game);
        
        //add JPanel
        frame.add(game); 
       
        game.start(); 
    }
    
    public void start()
    {
        XBall = 50;
        YBall = 50;
    
        dx = 1;
        dy = 2;
    
        Score = 0;
        
        
        bat = new Rectangle(Xbat,Ybat,100,10);
        ball = new Rectangle(XBall,YBall,30,30);
        
        start = true;
        gameOver = false;
    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        BallMove();  
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    {
        bat.x= e.getX();
        bat.y= e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) 
    {
        bat.x= e.getX();
        bat.y= e.getY(); 
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
       int s = e.getKeyCode();
       if(s==KeyEvent.VK_SPACE && gameOver)
       {
            start(); 
         
       }
       if(s==KeyEvent.VK_SPACE)
       { 
           //I don't know -KLD
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
