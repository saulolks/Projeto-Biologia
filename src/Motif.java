
public class Motif {
   private int hamdis;
   private int posinic;
   private String motif;

public Motif() {
 
}
public Motif(int hamdis, int posinic, String motif) {
	this.hamdis = hamdis;
	this.posinic = posinic;
	this.motif = motif;
}
public int getHamdis() {
	return hamdis;
}
public void setHamdis(int hamdis) {
	this.hamdis = hamdis;
}
public int getPosinic() {
	return posinic;
}
public void setPosinic(int posinic) {
	this.posinic = posinic;
}
public String getMotif() {
	return motif;
}
public void setMotif(String motif) {
	this.motif = motif;
}
@Override
public String toString() {
	return "(Motif= " + motif + ", Hamming Distance Total= " + hamdis +  ")\n";
}
@Override
public boolean equals(Object obj) {
	Motif other = (Motif) obj;
	if (hamdis != other.hamdis)
		return false;
	if (motif == null) {
		if (other.motif != null)
			return false;
	} else if (!motif.equals(other.motif))
		return false;
	return true;
} 
}
