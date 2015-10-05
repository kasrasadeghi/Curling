/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curling;

import apcscvm.DefaultControl;
import apcscvm.GraphicsUtilityFunctions;
import apcscvm.View;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author DSTIGANT
 */
public class CurlingGameView extends DefaultControl<CurlingEndResult[]> implements View<CurlingEndResult[]>
{
    private boolean scoreBoardMode;     // true ==> baseball style
                                        // false ==> club style
    
    private int curEnd;
    
    public CurlingGameView()
    {
        curEnd = 0;
        scoreBoardMode = true;
    }
    
    
    
    @Override
    public void paint(CurlingEndResult[] game, Graphics g, int w, int h)
    {
        Font f = GraphicsUtilityFunctions.getFont( 50 );
        if ( curEnd > 0 ) 
        {
            BufferedImage lastEnd = new BufferedImage( 300, 540, BufferedImage.TYPE_4BYTE_ABGR );
            Graphics gle = lastEnd.createGraphics();
            new CurlingSheetView().paint( game[curEnd-1], gle, 300, 540 );
            gle.setColor( Color.BLACK );
            gle.drawRect( 20, 20, 50, 50);
            GraphicsUtilityFunctions.drawStringWithFontInRectangle(gle, "" + (curEnd), f, 20, 20, 50, 50 );
            g.drawImage( lastEnd, w/2 - 450, h/2 - 325, null );
        }
        
        if ( curEnd < game.length-1 )
        {
            BufferedImage nextEnd = new BufferedImage( 300, 540, BufferedImage.TYPE_4BYTE_ABGR );
            Graphics gne = nextEnd.createGraphics();
            new CurlingSheetView().paint( game[curEnd+1], gne, 300, 540 );
            gne.setColor( Color.BLACK );
            gne.drawRect( 20, 20, 50, 50);
            GraphicsUtilityFunctions.drawStringWithFontInRectangle(gne, "" + (curEnd+2), f, 20, 20, 50, 50 );

            g.drawImage( nextEnd, w/2 + 150, h/2 - 325, null );
        }
        
        BufferedImage thisEnd = new BufferedImage( 300, 540, BufferedImage.TYPE_4BYTE_ABGR );
        Graphics gte = thisEnd.createGraphics();
        new CurlingSheetView().paint( game[curEnd], gte, 300, 540 );
        gte.setColor( Color.BLACK );
        gte.drawRect( 20, 20, 50, 50);
        GraphicsUtilityFunctions.drawStringWithFontInRectangle(gte, "" + (curEnd+1), f, 20, 20, 50, 50 );

        g.drawImage( thisEnd, w/2 - 150, h/2 - 325, null );
        
        int [][] scoreBoard = scoreBoardMode?
                CurlingUtilityFunctions.getBaseballStyleBoard( game ) :
                CurlingUtilityFunctions.getClubStyleBoard( game );
        
        if ( scoreBoard != null )
        {
            BufferedImage scoreImage = new BufferedImage( w, h/10, BufferedImage.TYPE_4BYTE_ABGR );
            Graphics gsi = scoreImage.createGraphics();
            new CurlingScoreBoardView( scoreBoardMode ).paint( scoreBoard, gsi, w, h/10 );
            g.drawImage( scoreImage, 0, 575, null );
        }
    }
    
    @Override
    public void handleKeyPress( CurlingEndResult[] m, int ea, KeyEvent ke )
    {
        if ( Character.toLowerCase(ke.getKeyChar() ) == 'n' )
        {
            curEnd = (curEnd+1)%m.length;
        }
        else if ( Character.toLowerCase( ke.getKeyChar() ) == 'p' )
        {
            curEnd = (curEnd+m.length-1)%m.length;
        }
        else if ( ke.getKeyChar() == ' ' )
        {
            scoreBoardMode = !scoreBoardMode;
        }
        
    }
    
}
