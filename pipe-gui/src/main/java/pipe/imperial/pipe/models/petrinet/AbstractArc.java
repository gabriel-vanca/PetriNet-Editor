package pipe.imperial.pipe.models.petrinet;

import uk.ac.imperial.pipe.models.petrinet.Arc;
import uk.ac.imperial.pipe.models.petrinet.ArcPoint;
import uk.ac.imperial.pipe.models.petrinet.ArcType;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class AbstractArc extends AbstractPetriNetPubSub implements Arc {
   private Connectable source;
   private Connectable target;
   private String id;
   private boolean tagged;
   protected Map tokenWeights = new HashMap();
   private final uk.ac.imperial.pipe.models.petrinet.ArcType type;
   private final List arcPoints = new LinkedList();
   private final uk.ac.imperial.pipe.models.petrinet.ArcPoint sourcePoint;
   private final uk.ac.imperial.pipe.models.petrinet.ArcPoint targetPoint;
   private final PropertyChangeListener intermediateListener = new AbstractArc.ArcPointChangeListener(null);

   public AbstractArc(Connectable source, Connectable target, Map tokenWeights, uk.ac.imperial.pipe.models.petrinet.ArcType type) {
      this.source = source;
      this.target = target;
      this.tokenWeights = tokenWeights;
      this.type = type;
      this.id = source.getId() + " TO " + target.getId();
      this.tagged = false;
      this.sourcePoint = new uk.ac.imperial.pipe.models.petrinet.ArcPoint(this.getStartPoint(), false, false);
      this.targetPoint = new uk.ac.imperial.pipe.models.petrinet.ArcPoint(this.getEndPoint(), false, false);
      this.arcPoints.add(this.sourcePoint);
      this.arcPoints.add(this.targetPoint);
      source.addPropertyChangeListener(new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            String name = evt.getPropertyName();
            if (name.equals("x") || name.equals("y") || name.equals("angle")) {
               AbstractArc.this.sourcePoint.setPoint(AbstractArc.this.getStartPoint());
               AbstractArc.this.targetPoint.setPoint(AbstractArc.this.getEndPoint());
            }

         }
      });
      target.addPropertyChangeListener(new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            String name = evt.getPropertyName();
            if (name.equals("x") || name.equals("y") || name.equals("angle")) {
               AbstractArc.this.sourcePoint.setPoint(AbstractArc.this.getStartPoint());
               AbstractArc.this.targetPoint.setPoint(AbstractArc.this.getEndPoint());
            }

         }
      });
   }

   public Map getTokenWeights() {
      return this.tokenWeights;
   }

   public Connectable getSource() {
      return this.source;
   }

   public void setSource(Connectable source) {
      Connectable old = this.source;
      this.source = source;
      this.changeSupport.firePropertyChange("source", old, source);
   }

   public Connectable getTarget() {
      return this.target;
   }

   public void setTarget(Connectable target) {
      Connectable old = this.target;
      this.target = target;
      this.changeSupport.firePropertyChange("target", old, target);
   }

   public boolean isSelectable() {
      return true;
   }

   public boolean isDraggable() {
      return true;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      String old = this.id;
      this.id = id;
      this.changeSupport.firePropertyChange("id", old, id);
   }

   public String getName() {
      return this.id;
   }

   public boolean isTagged() {
      return this.tagged;
   }

   public void setTagged(boolean tagged) {
      this.tagged = tagged;
   }

   public String getWeightForToken(String token) {
      return this.tokenWeights.containsKey(token) ? (String)this.tokenWeights.get(token) : "0";
   }

   public void setWeight(String tokenId, String weight) {
      Map old = new HashMap(this.tokenWeights);
      this.tokenWeights.put(tokenId, weight);
      this.changeSupport.firePropertyChange("weight", old, this.tokenWeights);
   }

   public boolean hasFunctionalWeight() {
      Iterator i$ = this.tokenWeights.values().iterator();

      while(i$.hasNext()) {
         String weight = (String)i$.next();

         try {
            Integer.parseInt(weight);
         } catch (NumberFormatException var4) {
            return true;
         }
      }

      return false;
   }

   public ArcType getType() {
      return this.type;
   }

   public void addIntermediatePoints(Iterable points) {
      Iterator i$ = points.iterator();

      while(i$.hasNext()) {
         uk.ac.imperial.pipe.models.petrinet.ArcPoint point = (uk.ac.imperial.pipe.models.petrinet.ArcPoint)i$.next();
         this.addIntermediatePoint(point);
      }

   }

   public void addIntermediatePoint(uk.ac.imperial.pipe.models.petrinet.ArcPoint point) {
      int penultimateIndex = this.arcPoints.size() - 1;
      this.arcPoints.add(penultimateIndex, point);
      point.addPropertyChangeListener(this.intermediateListener);
      this.recalculateStartPoint();
      this.recalculateEndPoint();
      this.changeSupport.firePropertyChange("newIntermediatePoint", (Object)null, point);
   }

   private void recalculateStartPoint() {
      Point2D startCoords = this.getStartPoint();
      this.sourcePoint.setPoint(startCoords);
   }

   private void recalculateEndPoint() {
      Point2D lastPoint = ((uk.ac.imperial.pipe.models.petrinet.ArcPoint)this.arcPoints.get(this.arcPoints.size() - 2)).getPoint();
      double angle = this.getAngleBetweenTwoPoints(lastPoint, this.target.getCentre());
      Point2D newPoint = this.target.getArcEdgePoint(angle);
      this.targetPoint.setPoint(newPoint);
   }

   public List getArcPoints() {
      return this.arcPoints;
   }

   public void removeIntermediatePoint(uk.ac.imperial.pipe.models.petrinet.ArcPoint point) {
      this.arcPoints.remove(point);
      point.removePropertyChangeListener(this.intermediateListener);
      this.recalculateStartPoint();
      this.recalculateEndPoint();
      this.changeSupport.firePropertyChange("deleteIntermediatePoint", point, (Object)null);
   }

   public uk.ac.imperial.pipe.models.petrinet.ArcPoint getNextPoint(uk.ac.imperial.pipe.models.petrinet.ArcPoint arcPoint) {
      int index = this.arcPoints.indexOf(arcPoint);
      if (index != this.arcPoints.size() - 1 && index >= 0) {
         return (uk.ac.imperial.pipe.models.petrinet.ArcPoint)this.arcPoints.get(index + 1);
      } else {
         throw new RuntimeException("No next point");
      }
   }

   public final Double getStartPoint() {
      double angle;
      if (this.arcPoints.size() > 1) {
         angle = this.getAngleBetweenTwoPoints(((uk.ac.imperial.pipe.models.petrinet.ArcPoint)this.arcPoints.get(1)).getPoint(), this.source.getCentre());
      } else {
         angle = this.getAngleBetweenTwoPoints(this.target.getCentre(), this.source.getCentre());
      }

      return this.source.getArcEdgePoint(angle);
   }

   public final Point2D getEndPoint() {
      return this.target.getArcEdgePoint(this.getEndAngle());
   }

   public double getEndAngle() {
      return this.arcPoints.size() > 1 ? this.getAngleBetweenTwoPoints(((ArcPoint)this.arcPoints.get(this.arcPoints.size() - 2)).getPoint(), this.target.getCentre()) : this.getAngleBetweenTwoPoints(this.source.getCentre(), this.target.getCentre());
   }

   private double getAngleBetweenTwoPoints(Point2D first, Point2D second) {
      double deltax = second.getX() - first.getX();
      double deltay = second.getY() - first.getY();
      return Math.atan2(deltay, deltax);
   }

   public int hashCode() {
      int result = this.source.hashCode();
      result = 31 * result + this.target.hashCode();
      result = 31 * result + this.id.hashCode();
      result = 31 * result + (this.tagged ? 1 : 0);
      result = 31 * result + this.tokenWeights.hashCode();
      result = 31 * result + this.arcPoints.hashCode();
      return result;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         AbstractArc arc = (AbstractArc)o;
         if (this.tagged != arc.tagged) {
            return false;
         } else if (!this.id.equals(arc.id)) {
            return false;
         } else if (!this.arcPoints.equals(arc.arcPoints)) {
            return false;
         } else if (!this.source.equals(arc.source)) {
            return false;
         } else {
            return this.target.equals(arc.target);
         }
      } else {
         return false;
      }
   }

   public void removeAllTokenWeights(String tokenId) {
      this.tokenWeights.remove(tokenId);
   }

   private class ArcPointChangeListener implements PropertyChangeListener {
      private ArcPointChangeListener() {
      }

      public void propertyChange(PropertyChangeEvent evt) {
         String name = evt.getPropertyName();
         if (name.equals("updateLocation")) {
            AbstractArc.this.recalculateEndPoint();
            AbstractArc.this.recalculateStartPoint();
         }

      }

      // $FF: synthetic method
      ArcPointChangeListener(Object x1) {
         this();
      }
   }
}