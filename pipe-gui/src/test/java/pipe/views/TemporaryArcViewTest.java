package pipe.views;

import org.junit.Before;
import org.junit.Test;
import pipe.gui.imperial.pipe.models.petrinet.ArcPoint;
import pipe.gui.imperial.pipe.models.petrinet.DiscretePlace;
import pipe.gui.imperial.pipe.models.petrinet.Place;

import java.awt.Rectangle;
import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

public class TemporaryArcViewTest {
    TemporaryArcView<Place> temporaryArcView;

    Place place;

    @Before
    public void setUp() {
        place = new DiscretePlace("P0", "P0");
        place.setX(100);
        place.setY(100);
        temporaryArcView = new TemporaryArcView<>(place, new NormalHead());
    }

    @Test
    public void initialisesBoundsToSource() {
        Point2D centre = place.getCentre();
        Rectangle expected = new Rectangle(0, 0, (int) centre.getX(), (int) centre.getY());

        Rectangle actual = temporaryArcView.getBounds();
        assertEquals(expected, actual);
    }

    @Test
    public void updatesBoundsIfMouseLargerThanPlace() {
        Point2D mouse = new Point2D.Double(300, 300);
        temporaryArcView.setEnd(mouse);

        Rectangle expected = new Rectangle(0, 0, (int) mouse.getX(), (int) mouse.getY());
        Rectangle actual = temporaryArcView.getBounds();
        assertEquals(expected, actual);
    }

    @Test
    public void updatesBoundsWithArcPoint() {
        Point2D mouse = new Point2D.Double(300, 300);
        temporaryArcView.addIntermediatePoint(new ArcPoint(mouse, true));

        Rectangle expected = new Rectangle(0, 0, (int) mouse.getX(), (int) mouse.getY());
        Rectangle actual = temporaryArcView.getBounds();
        assertEquals(expected, actual);
    }

    @Test
    public void keepsLargestBoundsIfMouseLessThanLargest() {
        Point2D mouse = new Point2D.Double(10, 20);
        temporaryArcView.setEnd(mouse);

        Point2D centre = place.getCentre();
        Rectangle expected = new Rectangle(0, 0, (int) centre.getX(), (int) centre.getY());
        Rectangle actual = temporaryArcView.getBounds();
        assertEquals(expected, actual);
    }

    @Test
    public void ensuresLargestIfXIsNewAndYIsExisting() {
        Point2D mouse = new Point2D.Double(10, 200);
        temporaryArcView.setEnd(mouse);

        Point2D centre = place.getCentre();
        Rectangle expected = new Rectangle(0, 0, (int) centre.getX(), (int) mouse.getY());
        Rectangle actual = temporaryArcView.getBounds();
        assertEquals(expected, actual);
    }
}
