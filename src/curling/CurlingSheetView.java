/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curling;

import apcscvm.Mouse;
import apcscvm.View;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author DSTIGANT
 */
public class CurlingSheetView implements View<CurlingEndResult>
{

    // paints the house (target), tee- and center-lines
    protected void paintSheet( Graphics g, int w, int h )
    {
        int cx = w/2;
        int cy = 21 * h/27;
        int rx, ry;
        
        g.setColor(Color.BLUE);
        rx = 6 * w/15; ry = 6 * h/27;
        g.fillOval(cx - rx, cy - ry, 2*rx, 2*ry); g.setColor(Color.BLACK);
        g.drawOval(cx - rx, cy - ry, 2*rx, 2*ry);
        
        g.setColor(Color.WHITE);
        rx = 4 * w/15; ry = 4 * h/27;
        g.fillOval(cx - rx, cy - ry, 2*rx, 2*ry); g.setColor(Color.BLACK);
        g.drawOval(cx - rx, cy - ry, 2*rx, 2*ry);
        
        g.setColor(Color.RED.darker());
        rx = 2 * w/15; ry = 2 * h/27;
        g.fillOval(cx - rx, cy - ry, 2*rx, 2*ry); g.setColor(Color.BLACK);
        g.drawOval(cx - rx, cy - ry, 2*rx, 2*ry);
        
        g.setColor(Color.WHITE);
        rx = w/30; ry = h/54;
        g.fillOval(cx - rx, cy - ry, 2*rx, 2*ry); g.setColor(Color.BLACK);
        g.drawOval(cx - rx, cy - ry, 2*rx, 2*ry);
        
        g.setColor(Color.BLACK);
        g.drawLine(cx, 0, cx, h);
        g.drawLine(0, cy, w, cy);
        
        g.drawRect(0, 0, w-1,h-1);
    }
    
    // paints a stone on the sheet.
    public void paintStone( Graphics g, int w, int h, Stone s, boolean highlight )
    {
        int cx = (int) (w/2 + s.getX() * w/15);
        int cy = (int) (21 * h/27 - s.getY() * h/27);
        if (highlight) g.setColor(s.getColor());
        else g.setColor(s.getColor().darker().darker());
        
        g.fillOval(cx - w/30, cy - h/54, w/15, h/27);
        g.drawOval(cx - w/30, cy - h/54, w/15, h/27);
    }
    
    @Override
    public void paint(CurlingEndResult er, Graphics g, int w, int h)
    {
        paintSheet( g, w, h );
        // paint each stone
        Stone [] stones = er.getStones();
        for( Stone stone : stones) 
            paintStone(g, w, h, stone, er.isScoringStone(stone));
    }
    
}
