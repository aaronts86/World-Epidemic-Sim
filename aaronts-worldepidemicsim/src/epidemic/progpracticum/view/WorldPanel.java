/**
 * Aaron Schraufnagel.
 */
package epidemic.progpracticum.view;

import epidemic.progpracticum.model.AbstractEntity;
import epidemic.progpracticum.model.Bird;
import epidemic.progpracticum.model.Human;
import epidemic.progpracticum.model.SimParams;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * The <code>WorldPanel</code> class is apart of the SimulationFrame and holds the graphical
 * representation of the simulation world.
 * 
 * @author Aaron Schraufnagel
 * 
 * @see epidemic.progpracticum.model.AbstractEntity
 * @see epidemic.progpracticum.model.Bird
 * @see epidemic.progpracticum.model.Human
 * @see epidemic.progpracticum.model.SimParams
 * @see java.awt.Color
 * @see java.awt.Dimension
 * @see java.awt.Graphics
 * @see java.awt.Graphics2D
 * @see java.awt.geom.Ellipse2D
 * @see java.util.ArrayList
 * @see java.util.List
 * @see javax.swing.JPanel
 * 
 * @cust.inv myDays >= 0, the days passed from the beginning of the simulation
 */
@SuppressWarnings("serial")
public class WorldPanel extends JPanel {
    /**
     * Represents the color of healthy humans.
     */
    private final Color my_healthy_human = Color.GREEN;
    /**
     * Represents the color of infected humans.
     */
    private final Color my_infected_human = Color.RED;
    /**
     * Represents the color of healthy birds.
     */
    private final Color my_healthy_bird = Color.GRAY;
    /**
     * Represents the color of infected birds.
     */
    private final Color my_infected_bird = Color.ORANGE;
    /**
     * List containing collection of AbstractEntity objects.
     */
    private List<AbstractEntity> myEntityList = new ArrayList<>();
    /**
     * Represents the days passed from the beginning of the simulation.
     */
    private int myDays;
    
    /**
     * Default constructor for the World Panel that initializes the simulation world's
     * graphical representation.
     * 
     * @custom.post the world panel is displayed to the user
     */
    public WorldPanel() {
        super();
        setPreferredSize(new Dimension(SimParams.PANEL_PIX_WIDTH, SimParams.PANEL_PIX_HEIGHT));
        setBackground(Color.WHITE);
    }
    
    /**
     * Overrides the paintComponent method to draw the population on the panel.
     * 
     * @param aGraphics the graphics object used to draw on the paintpanel
     * @custom.post the panel is drawn with components
     */
    @Override
    public void paintComponent(final Graphics aGraphics) {
        super.paintComponent(aGraphics);
        final Graphics2D g2d = (Graphics2D) aGraphics;
     
        for (AbstractEntity entity: myEntityList) {
            if (entity instanceof Human) {
                if (entity.isSick()) {
                    g2d.setPaint(my_infected_human);
                } else if (!entity.isSick()) {
                    g2d.setPaint(my_healthy_human);
                }
                g2d.fill(new Ellipse2D.Double(entity.getMyLocation().getX(),
                                              entity.getMyLocation().getY(),
                                              SimParams.HUMAN_DIM, SimParams.HUMAN_DIM));
            } else if (entity instanceof Bird) {
                if (entity.isSick()) {
                    g2d.setPaint(my_infected_bird);
                } else if (!entity.isSick()) {
                    g2d.setPaint(my_healthy_bird);
                }
                g2d.fill(new Ellipse2D.Double(entity.getMyLocation().getX(),
                                              entity.getMyLocation().getY(),
                                              SimParams.BIRD_DIM, SimParams.BIRD_DIM));
            }
        }
    }
    
    /**
     * Sets the list of entities with a new list.
     * 
     * @param anEntityList list containing collection of AbstractEntity objects
     * @custom.post the list of entities is replaced with a new list
     */
    public void setMyEntityList(List<AbstractEntity> anEntityList) {
        this.myEntityList = anEntityList;
    }
    
    /**
     * List of AbstractEntity objects is returned.
     * 
     * @return myEntityList list containing collection of AbstractEntity objects
     */
    public List<AbstractEntity> getMyEntityList() {
        return myEntityList;
    }
    
    /**
     * The days passed is returned.
     * 
     * @return the days passed from the beginning of the simulation
     */
    public int getMyDays() {
        return myDays;
    }

    /**
     * Advances the day by one.
     * 
     * @custom.post myDays is incremented by 1
     */
    public void advanceDay() {
        myDays++;
    }
    
    /**
     * The simulation world is reset to default values.
     * 
     * @custom.post the list is cleared, days are reset and the panel is cleared
     */
    public void resetWorld() {
        myEntityList.clear();
        myDays = 0;
        repaint();
    }
}
