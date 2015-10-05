/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curling;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DSTIGANT
 */
public class CurlingUtilityFunctions
{
    // readGameFromFile
    // reads a curling game from a file
    // input: the name of the file
    // output: a game (array of CurlingEndResults) or null if something goes wrong.
    public static CurlingEndResult [] readGameFromFile( String fileName )
    {
        // declare an array of CurlingEndResults to be the return value (do not create the array yet)
        
        try
        {
            // create a scanner from a file created from the file name
            Scanner in = new Scanner( new File(fileName) );
            
            // read the number of ends
            
            
            // NOW create the array with the appropriate number of entries
            
            
            // read several ends from the file
            
                // each end starts with the number of stones for that end
               
                // declare and create an array to hold Stones
                
                // read several stones from the file
                
                    // first read in the color (as a string)
                    
                    // then the x and y coordinates
                    
                    // create a new stone... if the color is "red" use Color.RED for the color
                    // if the color is "yellow", use Color.YELLOW
                    // store the new stone in one of the entries for the array of stones
                    
                
                // create a new CurlingEndResult from the array of stones
                // store the new CurlingEndResult in one of the entries of our CER array.
                
            
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(CurlingUtilityFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // return the result array
        
        return null;
    }
    
    // getBaseballStyleLine
    // Gets the baseball-style score line from a curling game for one of the teams
    // inputs:
    // game - the array of curling ends representing the game
    // c - the color of the team for whom to get the score
    // output:
    // an array containing the score for team c in each end of the game plus the total score for the team
    private static int [] getBaseballStyleLine( CurlingEndResult [] game, Color c )
    {
        return null;
    }
    
    public static int [][] getBaseballStyleBoard( CurlingEndResult [] game )
    {
        int [][] scarlet = new int[3][];
        int redTotal = 0;
        int yellowTotal = 0;
        scarlet[0] = getBaseballStyleLine( game, Color.RED );
        scarlet[2] = getBaseballStyleLine( game, Color.YELLOW );
        
        scarlet[1] = new int[game.length+1];
        
        for ( int i = 0; i < game.length; i++ )
        {
            scarlet[1][i] = (i+1);    
        }
        
        if ( scarlet[0] == null ) return null;
        
        return scarlet;
    }
    
    // getClubStyleLine
    // gets the club-style line from a game for a team
    // input:
    // game - an array of CurlingEndResults which describe the game
    // c - the color of the team for whom the line will be created
    // maxScore - the maximum score on the line
    // output: an array of maxScore ints.  A non-zero entry E at index I indicates
    // that in end E, team c scored and had a cumulative score of I points. 
    public static int [] getClubStyleLine( CurlingEndResult [] game, Color c, int maxScore )
    {
        return null;
    }
    
    public static int [][] getClubStyleBoard( CurlingEndResult [] game )
    {
        int redScore = 0;
        int yellowScore = 0;
        for ( CurlingEndResult er : game )
        {
            int es = er.getEndScore();
            if ( es > 0 )
            {
                redScore += es;
            }
            else if ( es < 0 )
            {
                yellowScore -= es;
            }
        }
        
        int [][] scarlet = new int[3][];
        
        int maxScore = Math.max(10, Math.max( redScore, yellowScore ));
        scarlet[0] = getClubStyleLine( game, Color.RED, maxScore );
        if ( scarlet[0] == null ) return null;
        
        scarlet[2] = getClubStyleLine( game, Color.YELLOW, maxScore );
        
        scarlet[1] = new int[maxScore];
        
        for ( int i = 0; i < scarlet[0].length; i++ )
        {
            scarlet[1][i] = (i+1);
        }
        
        
        return scarlet;
    }
    
    public static void writeGameToFile( String fileName, CurlingEndResult [] game )
    {
        try
        {
            PrintStream out = new PrintStream( new File(fileName) );
            out.println( game.length );
            for ( int i = 0; i < game.length; i++ )
            {
                Stone [] stones = game[i].getStones();
                out.println( stones.length );
                for ( int j = 0; j < stones.length; j++ )
                {
                    out.println( (stones[j].getColor() == Color.RED?"red ":"yellow ") + stones[j].getX() + " " + stones[j].getY() );
                }
            }
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(CurlingUtilityFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
