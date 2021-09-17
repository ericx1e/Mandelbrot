import java.awt.*;
 
public class Mandelbrot {
 
    private double aMin, aMax, bMin, bMax;
    private int maxLoops;
 
    private int screenWidth, screenHeight;
 
    public Mandelbrot(int screenWidth, int screenHeight) {
        aMin = -2;
        aMax = 1.5;
        bMin = -1.5;
        bMax = 1.5;
 
        maxLoops = 50;
 
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
 
    }

    public void zoom(int x, int y) {
        double da = (aMax-aMin)/screenWidth;
        double db = (bMax-bMin)/screenHeight;
        double a = aMin + x*da;
        double b = bMax - y*db;
        double width = aMax-aMin;
        aMin = a - width/4;
        aMax = a + width/4;
        double height = bMax - bMin;
        bMin = b - height/4;
        bMax = b + height/4;
        maxLoops*=1.2;
    }
 
    public void draw(Graphics2D g2){
 
        double da = (aMax - aMin)/screenWidth;  //delta A
        double db = (bMax - bMin)/screenHeight; //delta b
 
        //go to all the points on the screen.
        //plot point if in the set.

        for(double a = aMin; a < aMax; a += da) {
            for(double b = bMin; b < bMax; b += db) {
                int n = testPoint(a,b);
                if(n == maxLoops) {
                    plotPoint(a, b, g2);
                } else {
                    plotPoint(a, b, g2, n);
                }
            }
        }
 
    }
 
    private int testPoint(double a, double b){
        double ta = a;
        double tb = b;
        int counter = 0;

        while(counter < maxLoops && ta*ta + tb*tb < 16) {
            double tempA = ta*ta - tb*tb + a;//a;
            double tempB = 2*ta*tb + b; //b;
            ta = tempA;
            tb = tempB;
            counter++;
        }
        return counter;
    }
 
    private void plotPoint(double a, double b, Graphics2D g2){
        g2.setColor(Color.BLACK);
        double x = (a - aMin) * (screenWidth / (aMax-aMin));
        double y = screenHeight - (b - bMin) * (screenHeight / (bMax-bMin));
        g2.drawLine((int)x, (int)y, (int)x+1, (int)y);
    }
    private void plotPoint(double a, double b, Graphics2D g2, int n){
        Color c = Color.getHSBColor(1f*n/maxLoops,1-1f*n/maxLoops,1f);
        g2.setColor(c);
        double x = (a - aMin) * (screenWidth / (aMax-aMin));
        double y = screenHeight - (b - bMin) * (screenHeight / (bMax-bMin));
        g2.drawLine((int)x, (int)y, (int)x+1, (int)y);
    }
}