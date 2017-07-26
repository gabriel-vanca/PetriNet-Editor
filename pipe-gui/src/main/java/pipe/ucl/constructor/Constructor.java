package pipe.ucl.constructor;

import pipe.actions.gui.CreateAction;
import pipe.actions.gui.PipeApplicationModel;
import pipe.controllers.PetriNetController;
import pipe.controllers.application.PipeApplicationController;
import pipe.historyActions.component.AddPetriNetObject;
import uk.ac.imperial.pipe.models.petrinet.DiscretePlace;
import uk.ac.imperial.pipe.models.petrinet.PetriNet;
import uk.ac.imperial.pipe.models.petrinet.Place;

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

        AddState(new Point(150, 150));
        AddState(new Point(350, 350));
        AddState(new Point(150, 350));
        AddState(new Point(350, 150));
        AddState(new Point(550, 350));
    }

    private void AddState(Point point) {
       // Point point =


        String id = getNewPetriNetName(petriNetController);
        Place place = new DiscretePlace(id, id);
        place.setX(point.x);
        place.setY(point.y);

        PetriNet petriNet = petriNetController.getPetriNet();
        petriNet.addPlace(place);
//        PetriNet net = petriNetController.getPetriNet();

        //            registerUndoEvent(new AddPetriNetObject(place, net));


//            action.doAction(event, petriNetController);
//        }

//        Place place = newPlace(point, petriNetController);
//        PetriNet net = petriNetController.getPetriNet();
//
//
//        registerUndoEvent(new AddPetriNetObject(place, net));
    }

//    private Place newPlace(Point point, PetriNetController petriNetController) {
//        String id = getNewPetriNetName(petriNetController);
//        Place place = new DiscretePlace(id, id);
//        place.setX(point.x);
//        place.setY(point.y);
//
//        PetriNet petriNet = petriNetController.getPetriNet();
//        petriNet.addPlace(place);
//
//        return place;
//    }

    private String getNewPetriNetName(PetriNetController petriNetController) {
        return petriNetController.getUniquePlaceName();
    }

}
