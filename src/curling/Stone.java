/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curling;

import java.awt.Color;

/**
 *
 * @author DSTIGANT
 */
public class Stone
{
    private double x, y;
    private Color color;
    
    public Stone( double X, double Y, Color c )
    {
        x = X;
        y = Y;
        color = c;
    }
    
    public double getX() { return x; }
    public double getY() { return y; }
    public Color getColor() { return color; }

    public void setLocation(double X, double Y)
    {
        x = X;
        y = Y;
    }

    
}
