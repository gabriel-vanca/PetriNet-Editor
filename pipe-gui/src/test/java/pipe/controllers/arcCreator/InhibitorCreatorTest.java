package pipe.controllers.arcCreator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pipe.controllers.PetriNetController;
import pipe.controllers.application.PipeApplicationController;
import pipe.gui.PetriNetTab;
import pipe.views.PipeApplicationView;
import pipe.gui.imperial.pipe.models.petrinet.ArcPoint;
import pipe.gui.imperial.pipe.models.petrinet.InboundArc;
import pipe.gui.imperial.pipe.models.petrinet.InboundInhibitorArc;
import pipe.gui.imperial.pipe.models.petrinet.DiscretePlace;
import pipe.gui.imperial.pipe.models.petrinet.Place;
import pipe.gui.imperial.pipe.models.petrinet.DiscreteTransition;
import pipe.gui.imperial.pipe.models.petrinet.Transition;
import pipe.gui.imperial.pipe.models.petrinet.PetriNet;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InhibitorCreatorTest {

    @Mock
    PipeApplicationView mockView;

    @Mock
    PipeApplicationController mockController;

    @Mock
    PetriNetController mockPetriNetController;

    @Mock
    PetriNet mockNet;

    @Mock
    PetriNetTab mockTab;

    InhibitorCreator creator;

    @Before
    public void setUp() {
        when(mockController.getActivePetriNetController()).thenReturn(mockPetriNetController);
        when(mockPetriNetController.getPetriNet()).thenReturn(mockNet);
        when(mockView.getCurrentTab()).thenReturn(mockTab);
        creator = new InhibitorCreator();
    }

    @Test
    public void createsCorrectArc() {
        Place source = new DiscretePlace("", "");
        Transition transition = new DiscreteTransition("", "");
        InboundArc actual = creator.createInboundArc(source, transition,
                new LinkedList<ArcPoint>());

        InboundArc expected = new InboundInhibitorArc(source, transition);
        assertEquals(expected, actual);
    }

    @Test
    public void canCreatePlaceToTransition() {
        Transition transition = new DiscreteTransition("T0");
        Place place = new DiscretePlace("P0");
        assertTrue(creator.canCreate(place, transition));
    }

    @Test
    public void canCreateTransitionToPlace() {
        Transition transition = new DiscreteTransition("T0");
        Place place = new DiscretePlace("P0");
        assertFalse(creator.canCreate(transition, place));
    }

}
