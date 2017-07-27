package pipe.ucl.constructor;

import pipe.actions.gui.PipeApplicationModel;
import pipe.controllers.PetriNetController;
import pipe.controllers.application.PipeApplicationController;
import pipe.gui.imperial.pipe.models.petrinet.*;
import pipe.gui.imperial.state.StateType;
import pipe.ucl.models.TransAssertion;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Constructor {

    PipeApplicationController applicationController;
    PetriNetController petriNetController;
    PipeApplicationModel applicationModel;

    private ArrayList<TransAssertion> TransAssertionList;

    public Constructor(PipeApplicationController applicationController, PipeApplicationModel applicationModel) {
        this.applicationController = applicationController;
        this.applicationModel = applicationModel;
        this.petriNetController = applicationController.getActivePetriNetController ();

        InputParser inputParser = new InputParser ();
        TransAssertionList = inputParser.getTransAssertionList ();

        for (TransAssertion transAssertion : TransAssertionList) {

            AddState (transAssertion.getStartStateName (), transAssertion.getStartStateDate (), StateType.INTERMEDIARY);
            AddState (transAssertion.getEndStateName (), transAssertion.getEndStateDate (), StateType.INTERMEDIARY);
            AddGate ("name", transAssertion.getTime (), transAssertion.getSign (), transAssertion.getAction (), transAssertion.getAuthor ());
        }
    }

    private void AddState(String name, String time, StateType stateType) {
        try {
            int randomX, randomY;
            randomX = ThreadLocalRandom.current ().nextInt (10, 1270);
            randomY = ThreadLocalRandom.current ().nextInt (10, 675);

            String id = petriNetController.getUniquePlaceName ();
            Place place = new DiscretePlace (id, name, time, stateType);
            place.setX (randomX);
            place.setY (randomY);

            PetriNet petriNet = petriNetController.getPetriNet ();
            petriNet.addPlace (place);
        } catch(Exception e) {
            System.out.println ("ERROR: Could not add new state due to following error: " + e.toString ());
        }

    }

    private void AddGate(String name, String time, Boolean sign, String action, String author) {
        try{
            int randomX, randomY;
            randomX = ThreadLocalRandom.current ().nextInt (10, 1270);
            randomY = ThreadLocalRandom.current ().nextInt (10, 675);

            String id = petriNetController.getUniqueTransitionName();
            DiscreteTransition transition = new DiscreteTransition (id, name, author, action, time, sign);
            transition.setX(randomX);
            transition.setY(randomY);
            transition.setTimed(Boolean.TRUE);

            PetriNet petriNet = petriNetController.getPetriNet();
            petriNet.addTransition(transition);
        } catch(Exception e) {
            System.out.println ("ERROR: Could not add new gate due to following error: " + e.toString ());
        }
    }
}
