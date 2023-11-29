package mockesimerkki;

 public class FakeHinnoittelija implements IHinnoittelija {
 private float alennus;
 public FakeHinnoittelija(float alennus) {
 this.alennus = alennus;
 }
 public float getAlennusProsentti(Asiakas asiakas, Tuote tuote) {
 return alennus;
 }
@Override
public void setAlennusProsentti(Asiakas asiakas, float f) {
   
}
@Override
public void aloita() {
   
}
@Override
public void lopeta() {
    
}
}

