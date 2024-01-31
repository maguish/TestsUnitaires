package tests;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import main.Calculator;

class CalculatorTest {
	
	private Calculator calculatorUnderTest;
	
	private static Instant startTime;
	
	
	// "@BeforeAll" pour dire que cette méthode sera appelée avant tous les tests
	@BeforeAll
	public static void initStardedTime() {
		System.out.println("Méthode appelée avant tous les tests");
		// Supposons qu'on veulle calculer la durée d'exécution de tous les tests
		// On initialise le temps de début des tests
		startTime = Instant.now();
	}
	
	
	// "@BeforeEach" pour dire que cette méthode sera appelée avant chaque test
	@BeforeEach
	public void initCalculator() {
        calculatorUnderTest = new Calculator();
        System.out.println("Méthode appelée avant chaque test");
	}
	

	// "@AftereEach" pour dire que cette méthode sera appelée après chaque test
	@AfterEach
	public void methodeAppeleeApresChaqueTest() {
		//On déaffecte la valeur du calculateur pour éviter toute réutilisation
		calculatorUnderTest = null;
        System.out.println("Méthode appelée après chaque test");
	}
	

	// "@AfterAll" pour dire que cette méthode sera appelée à la fin de tous les tests
	@AfterAll
	public static void calculTestsDuration() {
		System.out.println("Méthode appelée à la fin de tous les tests");
		Instant endTime = Instant.now();
		long duration = Duration.between(startTime, endTime).toMillis();
		System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
	}
	
	
	//"mvn test" pour éxécuter plusieurs tests en ligne de commande
	
	
	// "@Test" au-dessus de chaque méthode indique que la méthode doit être lancée comme un test
	@Test
	void testAddTwoPositivesNumbers() {
		//Arrange
		final int a = 1;
		final int b = 2;
		
		//Act
		final int somme = calculatorUnderTest.addTwoPositivesNumbers(a,b);
		
		//Assert
		//assertEquals(3,somme);
		assertThat(somme).isEqualTo(3);
	}
	
	@Test
	void testMultiplyTwoPositivesNumbers() {
		//Arrange
		final int a = 2;
		final int b = 3;
		
		//Act
		final int produit = calculatorUnderTest.mutiplyTwoPositivesNumbers(a,b);
		
		//Assert
		//assertEquals(6,produit);
		assertThat(produit).isEqualTo(6);
	}
	
	
	// "@ParameterizedTest" pour dire que c'est un test paramétré
	// ie la méthode de test sera exécutée plusieurs fois avec différentes valeurs d'entrée
	// "@ValueSource" pour spécifier une source de valeurs pour les paramètres du test
	@ParameterizedTest(name = "{0} x 0 doit être égal à 0") // "name" est utilisé pour spécifier un modèle de nom pour les tests générés
	@ValueSource(ints = { 1, 2, 42, 1011, 5089 })
	void multiply_shoudReturnZero_withMultipleIntegers(int arg) {
		//Arrange -- Rien à faire ici
		
		//Act -- Multiplier par 0
		final int actualRes = calculatorUnderTest.mutiplyTwoPositivesNumbers(arg,0);
		
		//Assert -- Ça vaut toujours 0
		//assertEquals(0,actualRes);
		assertThat(actualRes).isEqualTo(0);
	}
	
	
	// Methode pour injecter plusieurs paramètres (un jeu de triplets de nombres)
	@ParameterizedTest(name = "{0} + {1} should equal to {2}")
	@CsvSource({ "1,1,2", "2,3,5", "42,57,99" })
	public void add_shouldReturnTheSum_ofMultipleIntegers(int arg1, int arg2, int expectResult) {
		// Arrange -- Rien à faire ici

		// Act
		int actualRes = calculatorUnderTest.addTwoPositivesNumbers(arg1, arg2);

		// Assert
		//assertEquals(expectResult, actualRes);
		assertThat(actualRes).isEqualTo(expectResult);
	}
	
	
	// "@Timeout" est utilisée pour spécifier une limite de temps d'exécution pour un test. 
	// Cela permet de s'assurer qu'un test ne s'exécute pas indéfiniment et de définir une 
	// limite de temps au-delà de laquelle le test est considéré comme échoué.
	@Timeout(1) // Puisque la limite de temps est de 1s (1000 ms) le test echouera
	@Test
	public void longCalcul_shouldComputeInLessThan1Second() {
		// Arrange

		// Act
		calculatorUnderTest.longCalculation();  // La méthode "longCalculation" ne débute qu'après 2s
		
		// Assert
		// ...
	}
	
	
	@Test
	public void listDigits_shouldReturnsTheListOfDigits_ofPositiveInteger() {
		// Given
		int number = 95897;

		// When
		Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

		// Then
		/*Set<Integer> expectedDigits = Stream.of(5, 7, 8, 9).collect(Collectors.toSet());
		assertEquals(expectedDigits, actualDigits);*/
		assertThat(actualDigits).containsExactlyInAnyOrder(5, 7, 8, 9);
	}
	
	
	@Test
	public void listDigits_shouldReturnsTheListOfDigits_ofNegativeInteger() {
		// Given
		int number = -124432;

		// When
		Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

		// Then
		assertThat(actualDigits).containsExactlyInAnyOrder(1,2,3,4);
	}
	
	
	@Test
	public void listDigits_shouldReturnsTheListOfZero_ofZero() {
		// Given
		int number = 0;

		// When
		Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

		// Then
		assertThat(actualDigits).containsExactly(0);
	}

}
