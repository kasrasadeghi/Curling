/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curling;

import apcscvm.GraphicsUtilityFunctions;
import apcscvm.View;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author DSTIGANT
 */
public class CurlingScoreBoardView implements View<int [][]>
{
    private boolean showZeroes;
    
    public CurlingScoreBoardView( boolean sz )
    {
        showZeroes = sz;
    }
    
    @Override
    public void paint(int[][] m, Graphics g, int w, int h)
    {
        g.setColor( Color.BLACK );
        g.drawRect( 0, 0, w-1, h-1 );
        double squareWidth = w/(m[0].length + 3.0);
        Font f = GraphicsUtilityFunctions.getFont( h/3 );
        g.setColor( Color.RED );
        g.fillRect( 0, 0, (int)(3*squareWidth), h/3 );
        g.setColor( Color.BLACK );
        g.drawRect( 0, 0, (int)(3*squareWidth), h/3 );
        g.setColor( Color.YELLOW );
        g.fillRect( 0, 2*h/3, (int)(3*squareWidth), h/3 );
        g.setColor( Color.BLACK );
        g.drawRect( 0, 2*h/3, (int)(3*squareWidth), h/3 );
        
        for ( int i = 0; i < m[0].length; i++ )
        {
            g.drawRect( (int)((i+3)*squareWidth), 0, (int)squareWidth, h/3 );
            if ( showZeroes || m[0][i] != 0 )
            {
                GraphicsUtilityFunctions.drawStringWithFontInRectangle(
                        g,
                        "" + m[0][i], f, 
                        (int)((i+3)*squareWidth), 0, (int)squareWidth, h/3);
            }
            
            if ( showZeroes && i == m[0].length-1 )
            {
                g.drawRect( (int)((i+3)*squareWidth), h/3, (int)squareWidth, h/3 );
                GraphicsUtilityFunctions.drawStringWithFontInRectangle(
                            g,
                            "T", f, 
                            (int)((i+3)*squareWidth), h/3, (int)squareWidth, h/3);
                
                g.drawRect( (int)((i+3)*squareWidth), 2*h/3, (int)squareWidth, h/3 );
            }
            else
            {
                g.drawRect( (int)((i+3)*squareWidth), h/3, (int)squareWidth, h/3 );
                GraphicsUtilityFunctions.drawStringWithFontInRectangle(
                            g,
                            "" + m[1][i], f, 
                            (int)((i+3)*squareWidth), h/3, (int)squareWidth, h/3);
                
                g.drawRect( (int)((i+3)*squareWidth), 2*h/3, (int)squareWidth, h/3 );
            }
            if ( showZeroes || m[2][i] != 0 )
            {
                GraphicsUtilityFunctions.drawStringWithFontInRectangle(
                        g,
                        "" + m[2][i], f, 
                        (int)((i+3)*squareWidth), 2*h/3, (int)squareWidth, h/3);
            }
        }
    }
    
}
