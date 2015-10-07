/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curling;

import java.awt.Color;
import java.util.Arrays;

/**
 *
 * @author DSTIGANT
 */
public class CurlingEndResult
{
    private Stone [] stones;
    
    public CurlingEndResult( Stone [] ss )
    {
        stones = ss;
    }
    
    public Stone [] getStones() { return stones; }
    
    public static double distanceToCenter( Stone s )
    {
        return Math.sqrt( s.getX() * s.getX() + s.getY() * s.getY() );
    }
    
    // isScoringStone
    // determines if s is a scoring stone - that is, it is in or touching the house and
    // no stones of the opposite color are closer to the tee
    // input: Stone s
    // output: true if s is scoring, false if not
    public boolean isScoringStone( Stone s )
    {
        // start by figuring out how far the stone is from the tee
        double distance = distanceToCenter(s);
        // if we're not in or touching the house, return false
        if (distance > 6) return false;
        
        // look at each stone in turn
        for (Stone stone : stones) 
            // if a stone is the opposite color and is closer than s, return false
            if(distanceToCenter(stone) < distanceToCenter(s) && stone.getColor() != s.getColor())
                return false;
        
        // if none of the opposite color stones were closer, return true
        return true;
    }
    
    // getDistanceToClosestStone
    // returns the distance to the closest stone of Color c (RED or YELLOW)
    // input: the color of stones to look at
    // output: the distance to the closest stone of that color or Double.MAX_VALUE if there
    // are no stones of that value
    private double getDistanceToClosestStone( Color c )
    {
        double closest = Double.MAX_VALUE;
        for ( Stone stone : stones ) {
            if (stone.getColor() == c) {
                double dist = distanceToCenter(stone);
                if (dist < closest) closest = dist;
            }
        }
            
        return closest;
    }
    
    // getNumScoringStones
    // returns the number of stones which are scoring for the given color
    // input: Color c is the color of the team for which we are getting scoring stones
    // output: the number of stones scoring for team c
    private int getNumScoringStones( Color c )
    {
        return (int) Arrays.stream(stones)
                .filter(stone -> stone.getColor() == c)
                .filter(stone -> isScoringStone(stone))
                .count();
    }

    // getEndScore
    // returns the score for the end - the number of stones that the scoring team has in the house
    // closer than the other team's closest stone
    // if the Red team scores, the result is positive number, if the yellow team scores
    // the result is a negative number, and if neither team scores (blank end) the result is 0.
    public int getEndScore()
    {
        if (getDistanceToClosestStone(Color.RED) == getDistanceToClosestStone(Color.YELLOW))
            return 0;
        return (getDistanceToClosestStone(Color.RED) < getDistanceToClosestStone(Color.YELLOW))
                ? getNumScoringStones( Color.RED) 
                : -getNumScoringStones( Color.YELLOW);
    }
    
    
}
