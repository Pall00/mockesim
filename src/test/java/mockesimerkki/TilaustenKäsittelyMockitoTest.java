package mockesimerkki;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TilaustenKäsittelyMockitoTest {
@Mock
 IHinnoittelija hinnoittelijaMock;
 @BeforeEach
 public void setup() {
MockitoAnnotations.openMocks(this);
 }

 @Test
public void testKäsittelyHintaYli100Mockito() {
    // Arrange
    float alkuSaldo = 200.0f;
    float listaHinta = 120.0f; 
    float alennus = 10.0f; 
    float adjustedAlennus = alennus + 5; 
    
    float loppuSaldo = alkuSaldo - (listaHinta * (1 - (adjustedAlennus / 100)));

    Asiakas asiakas = new Asiakas(alkuSaldo);
    Tuote tuote = new Tuote("Advanced TDD", listaHinta);
    
    // Record
    when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote)).thenReturn(alennus, adjustedAlennus);

    // Act
    TilaustenKäsittely käsittelijä = new TilaustenKäsittely();
    käsittelijä.setHinnoittelija(hinnoittelijaMock);
    käsittelijä.käsittele(new Tilaus(asiakas, tuote));

    // Assert
    assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);
    verify(hinnoittelijaMock).setAlennusProsentti(asiakas, adjustedAlennus);
}



 @Test
public void testaaKäsittelijäWithMockitoHinnoittelija() {
    // Arrange
    float alkuSaldo = 100.0f;
    float listaHinta = 30.0f;
    float alennus = 20.0f;
    float loppuSaldo = alkuSaldo - (listaHinta * (1 - alennus / 100));
    Asiakas asiakas = new Asiakas(alkuSaldo);
    Tuote tuote = new Tuote("TDD in Action", listaHinta);
    // Record
    when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote))
        .thenReturn(alennus);
    // Act
    TilaustenKäsittely käsittelijä = new TilaustenKäsittely();
    käsittelijä.setHinnoittelija(hinnoittelijaMock);
    käsittelijä.käsittele(new Tilaus(asiakas, tuote));
    // Assert
    assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);
    verify(hinnoittelijaMock, times(2)).getAlennusProsentti(asiakas, tuote); // Expecting two calls
}
@Test
public void testKäsittelyHintaAlle100Mockito() {
    // Arrange
    float alkuSaldo = 100.0f;
    float listaHinta = 90.0f; 
    float alennus = 20.0f; 
    
    float loppuSaldo = alkuSaldo - (listaHinta * (1 - (alennus / 100)));

    Asiakas asiakas = new Asiakas(alkuSaldo);
    Tuote tuote = new Tuote("Basic TDD", listaHinta);

   
    when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote))
        .thenReturn(alennus);

    // Act
    TilaustenKäsittely käsittelijä = new TilaustenKäsittely();
    käsittelijä.setHinnoittelija(hinnoittelijaMock);
    käsittelijä.käsittele(new Tilaus(asiakas, tuote));

    // Assert
    assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);
    verify(hinnoittelijaMock, times(2)).getAlennusProsentti(asiakas, tuote); // Expecting two calls
    verify(hinnoittelijaMock, never()).setAlennusProsentti(any(Asiakas.class), anyFloat()); // No call to setAlennusProsentti
}

}

