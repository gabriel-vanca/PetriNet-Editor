package pipe.ucl.constructor;

import pipe.actions.gui.CreateAction;
import pipe.actions.gui.PipeApplicationModel;
import pipe.actions.manager.ComponentCreatorManager;
import pipe.controllers.PetriNetController;
import pipe.controllers.application.PipeApplicationController;
import pipe.gui.imperial.pipe.layout.Layout;
import pipe.gui.imperial.pipe.models.petrinet.*;
import pipe.gui.imperial.state.StateType;
import pipe.ucl.models.TransAssertion;
import pipe.views.PipeApplicationBuilder;
import pipe.views.PipeApplicationView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Constructor {

    PipeApplicationController applicationController;
    PetriNetController petriNetController;
    PipeApplicationModel applicationModel;
    PipeApplicationView applicationView;
    PipeApplicationBuilder pipeApplicationBuilder;
    ComponentCreatorManager componentCreatorManager;

    private ArrayList<TransAssertion> TransAssertionList;

    public Constructor(PipeApplicationController applicationController, PipeApplicationModel applicationModel, PipeApplicationBuilder pipeApplicationBuilder, PipeApplicationView applicationView) {
        this.applicationController = applicationController;
        this.applicationModel = applicationModel;
        this.petriNetController = applicationController.getActivePetriNetController ();
        this.pipeApplicationBuilder = pipeApplicationBuilder;
        this.applicationView = applicationView;
        this.componentCreatorManager = applicationView.getComponentCreatorManager ();

        InputParser inputParser = new InputParser ();
        TransAssertionList = inputParser.getTransAssertionList ();

        for (TransAssertion transAssertion : TransAssertionList) {

            DiscretePlace startState = GetState (transAssertion.getStartStateId (), transAssertion.getStartStateName (), transAssertion.getStartStateDate (), StateType.INTERMEDIARY);
            DiscretePlace endState = GetState (transAssertion.getEndStateId (), transAssertion.getEndStateName (), transAssertion.getEndStateDate (), StateType.INTERMEDIARY);
            DiscreteTransition gate = AddGate ("name", transAssertion.getTime (), transAssertion.getSign (), transAssertion.getAction (), transAssertion.getAuthor ());
            AddArc (startState, gate);
            AddArc (gate, endState);
        }

        Layout ();
    }

    private void Layout() {
        Layout.layoutHierarchical (petriNetController.getPetriNet (), 40,
                50,50, 350, SwingConstants.NORTH);
//        componentCreatorManager..changed(petriNet);
    }

    private DiscretePlace GetState(String id, String name, String time, StateType stateType) {
        DiscretePlace place = null;

        try {
            place = petriNetController.getPetriNet ().getPlace (id);
        } catch (Exception e) {
            System.out.println ("ERROR: Could not get existing state due to following error: " + e.toString ());
        }

        if(place == null) {
            place = AddState (id, name, time, stateType);
        }

        return place;
    }

    private DiscretePlace AddState(String id, String name, String time, StateType stateType) {
        DiscretePlace place = null;

        try {
            int randomX, randomY;
            randomX = ThreadLocalRandom.current ().nextInt (10, 1270);
            randomY = ThreadLocalRandom.current ().nextInt (10, 675);

            if(id == null || id == "")
            {
                id = petriNetController.getUniquePlaceName ();
            }
            place = new DiscretePlace (id, name, time, stateType);
            place.setX (randomX);
            place.setY (randomY);

            PetriNet petriNet = petriNetController.getPetriNet ();
            petriNet.addPlace (place);
        } catch (Exception e) {
            System.out.println ("ERROR: Could not add new state due to following error: " + e.toString ());
        }

        return place;
    }

    private DiscreteTransition AddGate(String name, String time, Boolean sign, String action, String author) {
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
            return transition;
        } catch(Exception e) {
            System.out.println ("ERROR: Could not add new gate due to following error: " + e.toString ());
        }
        return null;
    }

    private void AddArc(AbstractConnectable connectableSource, AbstractConnectable connectableDestination) {

        CreateAction selectedAction = componentCreatorManager.getArcAction();
        selectedAction.doConnectableAction(connectableSource, petriNetController);
        selectedAction.doConnectableAction(connectableDestination, petriNetController);

    }
}
