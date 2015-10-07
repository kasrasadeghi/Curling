/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curling;

import apcscvm.CVMProgram;
import apcscvm.DefaultControl;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author DSTIGANT
 */
public class Curling
{
    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args)
    {
        //runEndCreator();
        runGameViewer();
        
    }
    
    public static void runEndCreator()
    {
        CurlingEndCreator cec = new CurlingEndCreator();
        new CVMProgram( "Setup", 300, 540, cec, cec, new ArrayList<Stone>() ).start();
    }
    
    public static void runGameViewer()
    {
        CurlingEndResult [] game = CurlingUtilityFunctions.readGameFromFile( "CurlingGame.txt" );
        CurlingGameView cgv = new CurlingGameView();
        new CVMProgram( "Game", 950, 700, cgv, cgv, game ).start();   
    }
}
