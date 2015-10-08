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
import java.util.Arrays;
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
        CurlingEndResult [] cer = null;
        
        try
        {
            // create a scanner from a file created from the file name
            Scanner in = new Scanner( new File(fileName) );
            
            // read the number of ends
            int endCount = in.nextInt();
            
            // NOW create the array with the appropriate number of entries
            cer = new CurlingEndResult[endCount];
            
            // read several ends from the file
            for (int i = 0; i < endCount; i++) {
                // each end starts with the number of stones for that end
                int stoneCount = in.nextInt();
               
                // declare and create an array to hold Stones
                Stone [] stones = new Stone[stoneCount];
                // read several stones from the file
                for(int j = 0; j < stoneCount; j++) {
                    // first read in the color (as a string)
                    String color = in.next();
                    // then the x and y coordinates
                    double x = in.nextDouble();
                    double y = in.nextDouble();
                    // create a new stone... if the color is "red" use Color.RED for the color
                    // if the color is "yellow", use Color.YELLOW
                    Color stoneColor = Color.YELLOW;
                    if (color.equals("red")) stoneColor = Color.RED;
                    // store the new stone in one of the entries for the array of stones
                    
                    Stone stone = new Stone(x, y, stoneColor);
                    stones[j] = stone;
                }
                    
                
                // create a new CurlingEndResult from the array of stones
                    //k: already done?
                // store the new CurlingEndResult in one of the entries of our CER array.
                
                cer[i] = new CurlingEndResult(stones);
                
            }
            
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(CurlingUtilityFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // return the result array
        
        return cer;
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
        int [] output = new int[game.length + 1];
        for( int i = 0; i < game.length; i++) {
            if (game[i].getEndScore() == 0)
                output[i] = 0;
            if (game[i].getEndScore() > 0 && c == Color.RED)
                output[i] = game[i].getEndScore();
            if (game[i].getEndScore() < 0 && c == Color.YELLOW)
                output[i] = -game[i].getEndScore();
        }
        output[output.length-1] = Arrays.stream(output).sum();
        return output;
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
        int[] scores = getBaseballStyleLine(game, c);
        int scoreSum = 0;
        int[] output = new int[maxScore];
        for( int i = 0; i < game.length; i++) { //the end counter
            scoreSum += scores[i];
            if (scores[i] != 0)
                output[scoreSum-1] = i + 1;
        }
        return output;
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
