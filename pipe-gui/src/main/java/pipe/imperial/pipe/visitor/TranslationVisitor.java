package pipe.imperial.pipe.visitor;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import pipe.imperial.pipe.models.petrinet.Annotation;
import pipe.imperial.pipe.models.petrinet.AnnotationVisitor;
import pipe.imperial.pipe.models.petrinet.Arc;
import pipe.imperial.pipe.models.petrinet.ArcPoint;
import pipe.imperial.pipe.models.petrinet.ArcPointVisitor;
import pipe.imperial.pipe.models.petrinet.ArcVisitor;
import pipe.imperial.pipe.models.petrinet.InboundArc;
import pipe.imperial.pipe.models.petrinet.OutboundArc;
import pipe.imperial.pipe.models.petrinet.Place;
import pipe.imperial.pipe.models.petrinet.PlaceVisitor;
import pipe.imperial.pipe.models.petrinet.Transition;
import pipe.imperial.pipe.models.petrinet.TransitionVisitor;

public final class TranslationVisitor implements ArcVisitor, ArcPointVisitor, PlaceVisitor, TransitionVisitor, AnnotationVisitor {
   private final Point translation;
   private final Collection selected;

   public TranslationVisitor(Point translation, Collection selected) {
      this.translation = translation;
      this.selected = selected;
   }

   public void visit(Place place) {
      place.setX(place.getX() + this.translation.x);
      place.setY(place.getY() + this.translation.y);
   }

   public void visit(Transition transition) {
      transition.setX(transition.getX() + this.translation.x);
      transition.setY(transition.getY() + this.translation.y);
   }

   public void visit(ArcPoint arcPoint) {
      double x = (double)arcPoint.getX() + this.translation.getX();
      double y = (double)arcPoint.getY() + this.translation.getY();
      arcPoint.setPoint(new Double(x, y));
   }

   public void visit(Annotation annotation) {
      annotation.setX(annotation.getX() + (int)this.translation.getX());
      annotation.setY(annotation.getY() + (int)this.translation.getY());
   }

   public void visit(InboundArc inboundArc) {
      this.visitArc(inboundArc);
   }

   public void visit(OutboundArc outboundArc) {
      this.visitArc(outboundArc);
   }

   private void visitArc(Arc arc) {
      if (this.selected.contains(arc.getSource()) && this.selected.contains(arc.getTarget())) {
         List points = arc.getArcPoints();
         Iterator i$ = points.iterator();

         while(i$.hasNext()) {
            ArcPoint arcPoint = (ArcPoint)i$.next();
            Point2D point = arcPoint.getPoint();
            Point2D newPoint = new Double(point.getX() + this.translation.getX(), point.getY() + this.translation.getY());
            arcPoint.setPoint(newPoint);
         }
      }

   }
}
