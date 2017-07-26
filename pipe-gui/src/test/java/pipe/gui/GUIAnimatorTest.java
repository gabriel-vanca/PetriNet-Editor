package pipe.gui;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pipe.controllers.GUIAnimator;
import pipe.controllers.application.PipeApplicationController;
import pipe.historyActions.AnimationHistory;
import pipe.gui.imperial.pipe.animation.Animator;
import pipe.gui.imperial.pipe.models.petrinet.Transition;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GUIAnimatorTest {

    private GUIAnimator animator;

    @Mock
    private AnimationHistory mockHistory;

    @Mock
    private Animator mockAnimator;

    @Mock
    private PipeApplicationController applicationController;

    @Before
    public void setUp() {
        animator = new GUIAnimator(mockAnimator, mockHistory, applicationController);
    }

    @Test
    public void firingAddsToHistoryAndFires() {
        Transition transition = mock(Transition.class);
        animator.fireTransition(transition);

        InOrder inOrder = inOrder(mockHistory);
        inOrder.verify(mockHistory, times(1)).clearStepsForward();
        inOrder.verify(mockHistory, times(1)).addHistoryItem(transition);
        verify(mockAnimator).fireTransition(transition);
    }

    @Test
    public void ifStepForwardAnimatesTransition() {
        when(mockHistory.isStepForwardAllowed()).thenReturn(true);
        when(mockHistory.getCurrentPosition()).thenReturn(1);
        Transition transition = mock(Transition.class);
        when(mockHistory.getTransition(2)).thenReturn(transition);

        animator.stepForward();
        verify(mockAnimator).fireTransition(transition);
        verify(mockHistory).stepForward();
    }

    @Test
    public void ifCannotStepForwardDoesNotAnimateTransition() {
        when(mockHistory.isStepForwardAllowed()).thenReturn(false);
        when(mockHistory.getCurrentPosition()).thenReturn(1);
        Transition transition = mock(Transition.class);
        when(mockHistory.getTransition(2)).thenReturn(transition);

        animator.stepForward();
        verify(mockAnimator, never()).fireTransition(transition);
        verify(mockHistory, never()).stepForward();
    }

    @Test
    public void ifStepBackwardAnimatesTransition() {
        when(mockHistory.isStepBackAllowed()).thenReturn(true);
        Transition transition = mock(Transition.class);
        when(mockHistory.getCurrentTransition()).thenReturn(transition);

        animator.stepBack();
        verify(mockAnimator).fireTransitionBackwards(transition);
        verify(mockHistory).stepBackwards();
    }

    @Test
    public void ifCannotStepBackwardDoesNotAnimateTransition() {
        when(mockHistory.isStepBackAllowed()).thenReturn(true);
        Transition transition = mock(Transition.class);
        when(mockHistory.getCurrentTransition()).thenReturn(transition);

        animator.stepForward();
        verify(mockAnimator, never()).fireTransitionBackwards(transition);
        verify(mockHistory, never()).stepBackwards();
    }

    @Test
    public void doRandomFiringClearsForwardsThenAddsToHistory() {
        Transition transition = mock(Transition.class);
        when(mockAnimator.getRandomEnabledTransition()).thenReturn(transition);
        animator.doRandomFiring();
        InOrder inOrder = inOrder(mockHistory);
        inOrder.verify(mockHistory, times(1)).clearStepsForward();
        inOrder.verify(mockHistory, times(1)).addHistoryItem(transition);
    }

    @Test
    public void doRandomFiringFiresPetriNet() {
        Transition transition = mock(Transition.class);
        when(mockAnimator.getRandomEnabledTransition()).thenReturn(transition);
        animator.doRandomFiring();
        verify(mockAnimator).fireTransition(transition);
    }

    @Test
    public void restoresOriginalTokensWhenFinished() {
        animator.startAnimation();
        animator.finish();

        verify(mockAnimator).reset();
    }
}
