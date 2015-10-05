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
        
    }
    
    // paints a stone on the sheet.
    public void paintStone( Graphics g, int w, int h, Stone s, boolean highlight )
    {
        
    }
    
    @Override
    public void paint(CurlingEndResult er, Graphics g, int w, int h)
    {
        paintSheet( g, w, h );

        // paint each stone
        
    }
    
}
