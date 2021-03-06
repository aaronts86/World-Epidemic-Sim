/**
 * Aaron Schraufnagel.
 */
package epidemic.progpracticum.model;

import java.awt.Point;

/**
 * The <code>Bird</code> class represents a Bird object that is a subclass of the abstract
 * class <code>AbstractEntity</code>. A bird can be healthy of sick, if it's sick, it
 * can infect humans.
 * 
 * @author Aaron Schraufnagel
 * @see java.awt.Point
 * 
 * @cust.inv aDayNum >= 0, the number of days the object has been sick
 *           aLocation 0 <= x < world width, 0 <= y < world height
 */
public class Bird extends AbstractEntity {
    /**
     * Parameterized constructor that sets the Bird object's state to parameters passed.
     * @param aSpecies is a character denoting the species
     * @param aSick is a boolean value denoting whether the bird object is sick or not
     * @param aDayNum >= 0, the number of days the bird object has been sick
     * @param aLocation is the location of the bird object in the world, 0 <= x < world width,
     *                  0 <= y < world height
     * @custom.post bird object's state set to parameters passed
     */
    public Bird(final char aSpecies, final boolean aSick, final int aDayNum,
                final Point aLocation) {
        super(aSpecies, aSick, aDayNum, aLocation);
    }

    /**
     * Defines the way that a Bird can move in the simulation world. A bird can move
     * between 1-3 spaces in a horizontal and/or vertical direction each timeslice.
     * 
     * @custom.post the bird object moves to a new location
     */
    @Override
    public void move() {
        final int horizontalDirection = SimParams.GENERATOR.nextInt(2);
        final int verticalDirection = SimParams.GENERATOR.nextInt(2);
        final int horizontalSpaces = (SimParams.GENERATOR.nextInt(3) + 1)
                * SimParams.HUMAN_DIM;
        final int verticalSpaces = (SimParams.GENERATOR.nextInt(3) + 1) * SimParams.HUMAN_DIM;
        final Point newLocation = new Point();
        
        if (horizontalDirection == 0 && verticalDirection == 0) {           // RIGHT --> UP
            newLocation.setLocation((getMyLocation().x + horizontalSpaces)
                                    % SimParams.PANEL_PIX_WIDTH,
                                    (getMyLocation().y - verticalSpaces)
                                    % SimParams.PANEL_PIX_HEIGHT);
        } else if (horizontalDirection == 1 && verticalDirection == 0) {    // LEFT --> UP
            newLocation.setLocation((getMyLocation().x - horizontalSpaces)
                                    % SimParams.PANEL_PIX_WIDTH,
                                    (getMyLocation().y - verticalSpaces)
                                    % SimParams.PANEL_PIX_HEIGHT);
        } else if (horizontalDirection == 0 && verticalDirection == 1) {    // RIGHT --> DOWN
            newLocation.setLocation((getMyLocation().x + verticalSpaces)
                                    % SimParams.PANEL_PIX_WIDTH,
                                    (getMyLocation().y + verticalSpaces)
                                    % SimParams.PANEL_PIX_HEIGHT);
        } else if (horizontalDirection == 1 && verticalDirection == 1) {    // LEFT --> DOWN
            newLocation.setLocation((getMyLocation().x - horizontalSpaces)
                                    % SimParams.PANEL_PIX_WIDTH,
                                    (getMyLocation().y + verticalSpaces)
                                    % SimParams.PANEL_PIX_HEIGHT);
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
