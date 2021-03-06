/**
 * Aaron Schraufnagel.
 */
package epidemic.progpracticum.model;

import java.awt.Point;

/**
 * The <code>Human</code> class represents a Human object that is a subclass of the abstract
 * class <code>AbstractEntity</code>. A human can be healthy of sick.
 * 
 * @author Aaron Schraufnagel
 * 
 * @see java.awt.Point
 * 
 * @cust.inv aDayNum >= 0, the number of days the object has been sick
 *           aLocation 0 <= x < world width, 0 <= y < world height
 */
public class Human extends AbstractEntity {
    /**
     * Parameterized constructor that sets the Human object's state to parameters passed.
     * @param aSpecies is a character denoting the species
     * @param aSick is a boolean value denoting whether the human object is sick or not
     * @param aDayNum >= 0, the number of days the human object has been sick
     * @param aLocation is the location of the human object in the world, 0 <= x < world width,
     *                  0 <= y < world height
     * @custom.post human object's state set to parameters passed
     */
    public Human(final char aSpecies, final boolean aSick, final int aDayNum,
                 final Point aLocation) {
        super(aSpecies, aSick, aDayNum, aLocation);
    }
    
    /**
     * Defines the way that a Human can move in the simulation world. A human can move
     * between 1-3 spaces in a horizontal and/or vertical direction each timeslice.
     * 
     * @custom.post the human object moves to a new location
     */
    @Override
    public void move() {
        final int direction = SimParams.GENERATOR.nextInt(9);
        final Point newLocation = new Point();
        
        if (direction == 0) {           // North
            newLocation.setLocation(getMyLocation().x, (getMyLocation().y - 1)
                                    % SimParams.PANEL_PIX_HEIGHT);      
        } else if (direction == 1) {    // Northeast
            newLocation.setLocation((getMyLocation().x + SimParams.HUMAN_DIM) 
                                    % SimParams.PANEL_PIX_WIDTH,
                                    (getMyLocation().y - SimParams.HUMAN_DIM)
                                    % SimParams.PANEL_PIX_HEIGHT);
        } else if (direction == 2) {    // East
            newLocation.setLocation((getMyLocation().x + SimParams.HUMAN_DIM)
                                    % SimParams.PANEL_PIX_WIDTH,
                                    getMyLocation().y);
        } else if (direction == 3) {    // Southeast
            newLocation.setLocation((getMyLocation().x + SimParams.HUMAN_DIM)
                                    % SimParams.PANEL_PIX_WIDTH,
                                    (getMyLocation().y + SimParams.HUMAN_DIM)
                                    % SimParams.PANEL_PIX_HEIGHT);
        } else if (direction == 4) {    // South
            newLocation.setLocation(getMyLocation().x,
                                    (getMyLocation().y + SimParams.HUMAN_DIM)
                                    % SimParams.PANEL_PIX_HEIGHT);
        } else if (direction == 5) {    // Southwest
            newLocation.setLocation((getMyLocation().x - SimParams.HUMAN_DIM)
                                    % SimParams.PANEL_PIX_WIDTH,
                                    (getMyLocation().y + SimParams.HUMAN_DIM)
                                    % SimParams.PANEL_PIX_HEIGHT);
        } else if (direction == 6) {    // West
            newLocation.setLocation((getMyLocation().x - SimParams.HUMAN_DIM)
                                    % SimParams.PANEL_PIX_WIDTH,
                                    getMyLocation().y);
        } else if (direction == 7) {    // Northwest
            newLocation.setLocation((getMyLocation().x - SimParams.HUMAN_DIM)
                                    % SimParams.PANEL_PIX_WIDTH,
                                    (getMyLocation().y - SimParams.HUMAN_DIM)
                                    % SimParams.PANEL_PIX_HEIGHT);
        } else if (direction == 8) {    // Same
            newLocation.setLocation(getMyLocation());
        }
        
        if (newLocation.x < 0) {
            newLocation.x += SimParams.PANEL_PIX_WIDTH;
        }
        if (newLocation.y < 0) {
            newLocation.y += SimParams.PANEL_PIX_HEIGHT;
        }
        
        setMyLocation(newLocation);
    }
}
