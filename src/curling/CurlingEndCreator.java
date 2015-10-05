/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curling;

import apcscvm.DefaultControl;
import apcscvm.GraphicsUtilityFunctions;
import apcscvm.Mouse;
import apcscvm.View;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import static javafx.scene.text.Font.font;

/**
 *
 * @author DSTIGANT
 */
public class CurlingEndCreator extends DefaultControl<ArrayList<Stone>> implements View<ArrayList<Stone>>
{
    private Color curColor;
    private boolean pickupMode;
    private Stone pickedUpStone;
    private int width, height;
    private ArrayList<CurlingEndResult> ends;
    boolean displayScore;
    
    public CurlingEndCreator()
    {
        curColor = Color.RED;
        width = 0;
        height = 0;
        ends = new ArrayList<>();
        pickupMode = true;
        pickedUpStone = null;
        displayScore = false;
    }
    
    @Override
    public void paint(ArrayList<Stone> m, Graphics g, int w, int h)
    {
        width = w;
        height = h;
        Stone [] sa = new Stone[m.size()];
        for ( int i = 0; i < m.size(); i++ )
        {
            sa[i] = m.get(i);
        }
        
        CurlingEndResult er = new CurlingEndResult( sa );
        new CurlingSheetView().paint( er, g, w, h );
        
        double x = (Mouse.getX() - width/2.0)/(width/15.0);
        double y = (height * 21.0/27 - Mouse.getY())/(height/27.0);
        
        g.setColor( Color.BLACK );
        GraphicsUtilityFunctions.drawStringWithFontInRectangle(g, 
                String.format( "%.2f, %.2f", x, y), 
                GraphicsUtilityFunctions.getFont(10), 
                Mouse.getX()-25, Mouse.getY()-40, 50, 40);
        
        if ( pickupMode && pickedUpStone != null )
        {
            pickedUpStone.setLocation( x, y );
        }
        
        if ( displayScore )
        {
            int score = er.getEndScore();
            GraphicsUtilityFunctions.drawStringWithFontInRectangle(g, 
                    "" + score, 
                    GraphicsUtilityFunctions.getFont( 4*h/27 ), 
                    w/15, h/27, 4*h/27, 4*h/27 );
            g.drawRect(w/15, h/27, 4*h/27, 4*h/27);
        }
    }
    
    public void handleMouseClick( ArrayList<Stone> sl, int ea, MouseEvent me )
    {
        double x = (me.getX() - width/2.0)/(width/15.0);
        double y = (height * 21.0/27 - me.getY())/(height/27.0);
        
        Stone stone = null;
        for ( Stone s : sl )
        {
            double dx = x - s.getX();
            double dy = y - s.getY();
            if ( Math.sqrt( dx*dx + dy*dy ) < (pickedUpStone == null?.5:1) && s != pickedUpStone )
            {
                stone = s;
            }
        }
        
        if ( stone != null && stone != pickedUpStone )
        {
            if ( pickupMode )
            {
                if ( pickedUpStone == null )
                {
                    pickedUpStone = stone;
                }
            }
            else
            {
                sl.remove( stone );
            }
        }
        else if ( pickedUpStone != null )
        {
            pickedUpStone = null;
        }
        else
        {
            sl.add( new Stone( x, y, curColor ) );
        }
    }
    
    public void handleKeyPress( ArrayList<Stone> sl, int ea, KeyEvent ke )
    {
        if ( Character.toLowerCase( ke.getKeyChar() ) == 'r' )
        {
            curColor = Color.RED;
        }
        else if ( Character.toLowerCase( ke.getKeyChar() ) == 'y' )
        {
            curColor = Color.YELLOW;
        }
        else if ( Character.toLowerCase( ke.getKeyChar() ) == 'n' )
        {
            Stone [] stones = new Stone[sl.size()];
            for ( int i = 0; i < sl.size(); i++ )
            {
                stones[i] = sl.get(i);
            }
            
            ends.add( new CurlingEndResult( stones ) );
            
            sl.clear();
        }
        else if ( Character.toLowerCase( ke.getKeyChar() ) == 's' )
        {
            CurlingEndResult [] game = new CurlingEndResult[ends.size()];
            for ( int i = 0; i < ends.size(); i++ )
            {
                game[i] = ends.get(i);
            }
            CurlingUtilityFunctions.writeGameToFile( "CurlingGame.txt", game );
        }
        else if ( Character.toLowerCase( ke.getKeyChar() ) == 'd' )
        {
            pickedUpStone = null;
            pickupMode = false;
        }
        else if ( Character.toLowerCase( ke.getKeyChar() ) == 'p' )
        {
            pickupMode = true;
        }
        else if ( ke.getKeyChar() == ' ' )
        {
            displayScore = !displayScore;
        }
        else if ( Character.toLowerCase( ke.getKeyChar() ) == 'l' )
        {
            CurlingEndResult [] game = CurlingUtilityFunctions.readGameFromFile( "CurlingGame.txt" );
            ends.clear();
            for ( CurlingEndResult cer : game )
            {
                ends.add( cer );
            }
            sl.clear();
            CurlingEndResult cer = game[0];
            for ( Stone s : cer.getStones() )
            {
                sl.add( s );
            }
        }
    }
}
