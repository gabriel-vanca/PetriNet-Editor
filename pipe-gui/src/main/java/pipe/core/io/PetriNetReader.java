package pipe.core.io;

import java.io.FileNotFoundException;
import javax.xml.bind.JAXBException;
import pipe.core.models.petrinet.PetriNet;

public interface PetriNetReader {
   PetriNet read(String var1) throws JAXBException, FileNotFoundException;
}
