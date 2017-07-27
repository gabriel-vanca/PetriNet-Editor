package pipe.ucl.constructor;

import pipe.actions.gui.PipeApplicationModel;
import pipe.controllers.PetriNetController;
import pipe.controllers.application.PipeApplicationController;
import pipe.gui.imperial.pipe.models.petrinet.DiscretePlace;
import pipe.gui.imperial.pipe.models.petrinet.PetriNet;
import pipe.gui.imperial.pipe.models.petrinet.Place;
import pipe.gui.imperial.state.StateType;

import java.awt.*;

public class Constructor {

    PipeApplicationController applicationController;
    PetriNetController petriNetController;
    PipeApplicationModel applicationModel;

    public Constructor(PipeApplicationController applicationController, PipeApplicationModel applicationModel) {
        this.applicationController = applicationController;
        this.applicationModel = applicationModel;
        this.petriNetController = applicationController.getActivePetriNetController();

        InputParser inputParser = new InputParser();

        AddState("State 1", null, StateType.START, new Point(150, 150));
        AddState("State 2", null, StateType.START, new Point(150, 150));
    }

    private void AddState(String name, String time, StateType stateType, Point point) {

        String id = petriNetController.getUniquePlaceName();
        Place place = new DiscretePlace(id, name, time, stateType);
        place.setX(point.x);
        place.setY(point.y);

        PetriNet petriNet = petriNetController.getPetriNet();
        petriNet.addPlace(place);

    }
}
