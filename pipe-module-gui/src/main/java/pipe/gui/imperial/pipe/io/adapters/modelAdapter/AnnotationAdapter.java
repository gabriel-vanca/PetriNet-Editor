package pipe.gui.imperial.pipe.io.adapters.modelAdapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import pipe.gui.imperial.pipe.io.adapters.model.AdaptedAnnotation;
import pipe.gui.imperial.pipe.models.petrinet.Annotation;
import pipe.gui.imperial.pipe.models.petrinet.AnnotationImpl;

public class AnnotationAdapter extends XmlAdapter {
   public final AnnotationImpl unmarshal(AdaptedAnnotation adaptedAnnotation) {
      return new AnnotationImpl(adaptedAnnotation.getX(), adaptedAnnotation.getY(), adaptedAnnotation.getText(), adaptedAnnotation.getWidth(), adaptedAnnotation.getHeight(), adaptedAnnotation.hasBoarder());
   }

   public final AdaptedAnnotation marshal(Annotation annotation) {
      AdaptedAnnotation adaptedAnnotation = new AdaptedAnnotation();
      adaptedAnnotation.setText(annotation.getText());
      adaptedAnnotation.setX(annotation.getX());
      adaptedAnnotation.setY(annotation.getY());
      adaptedAnnotation.setBorder(annotation.hasBorder());
      adaptedAnnotation.setWidth(annotation.getWidth());
      adaptedAnnotation.setHeight(annotation.getHeight());
      return adaptedAnnotation;
   }
}
