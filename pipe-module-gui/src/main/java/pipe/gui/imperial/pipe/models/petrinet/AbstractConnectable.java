package pipe.gui.imperial.pipe.models.petrinet;

public abstract class AbstractConnectable extends AbstractPetriNetPubSub implements Connectable {
   protected double x = 0.0D;
   protected double y = 0.0D;
   protected String id;
   protected String name = null;
   protected String time = null;
   protected double nameXOffset = -5.0D;
   protected double nameYOffset = 35.0D;

   protected AbstractConnectable(String id, String name, String time) {
      this.id = id;
      this.name = name;
      this.time = time;
   }

   protected AbstractConnectable(String id, String name) {
      this.id = id;
      this.name = name;
      this.time = null;
   }

   protected AbstractConnectable(AbstractConnectable connectable) {
      this.id = connectable.id;
      this.name = connectable.name;
      this.time = connectable.time;
      this.x = connectable.x;
      this.y = connectable.y;
      this.nameXOffset = connectable.nameXOffset;
      this.nameYOffset = connectable.nameYOffset;
   }

   public int hashCode() {
      long temp = Double.doubleToLongBits(this.x);
      int result = (int)(temp ^ temp >>> 32);
      temp = Double.doubleToLongBits(this.y);
      result = 31 * result + (int)(temp ^ temp >>> 32);
      result = 31 * result + this.id.hashCode();
      result = 31 * result + this.name.hashCode();
      temp = Double.doubleToLongBits(this.nameXOffset);
      result = 31 * result + (int)(temp ^ temp >>> 32);
      temp = Double.doubleToLongBits(this.nameYOffset);
      result = 31 * result + (int)(temp ^ temp >>> 32);
      return result;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         AbstractConnectable that = (AbstractConnectable)o;
         if (Double.compare(that.nameXOffset, this.nameXOffset) != 0) {
            return false;
         } else if (Double.compare(that.nameYOffset, this.nameYOffset) != 0) {
            return false;
         } else if (Double.compare(that.x, this.x) != 0) {
            return false;
         } else if (Double.compare(that.y, this.y) != 0) {
            return false;
         } else if (!this.id.equals(that.id)) {
            return false;
         } else {
            return this.name.equals(that.name);
         }
      } else {
         return false;
      }
   }

   public String toString() {
      String string = this.id;
      if(this.name != null && this.name.length() > 0)
         string += ":" + this.name;
      if(this.time != null && this.time.length() > 0)
         time += ":" + this.time;

      return string;
   }

   public final double getNameXOffset() {
      return this.nameXOffset;
   }

   public final void setNameXOffset(double nameXOffset) {
      double oldValue = this.nameXOffset;
      this.nameXOffset = nameXOffset;
      this.changeSupport.firePropertyChange("nameXOffset", oldValue, nameXOffset);
   }

   public final double getNameYOffset() {
      return this.nameYOffset;
   }

   public final void setNameYOffset(double nameYOffset) {
      double oldValue = this.nameYOffset;
      this.nameYOffset = nameYOffset;
      this.changeSupport.firePropertyChange("nameYOffset", oldValue, this.nameXOffset);
   }

   public final String getName() {
      return this.name;
   }

   public final void setName(String name) {
      String old = this.name;
      this.name = name;
      this.changeSupport.firePropertyChange("name", old, name);
   }

   public final String getId() {
      return this.id;
   }

   public final void setId(String id) {
      String old = this.id;
      this.id = id;
      this.changeSupport.firePropertyChange("id", old, id);
   }

   public final int getX() {
      return (int)this.x;
   }

   public final void setX(int x) {
      double oldValue = this.x;
      this.x = (double)x;
      this.changeSupport.firePropertyChange("x", oldValue, x);
   }

   public final int getY() {
      return (int)this.y;
   }

   public final void setY(int y) {
      double oldValue = this.y;
      this.y = (double)y;
      this.changeSupport.firePropertyChange("y", oldValue, y);
   }
}
