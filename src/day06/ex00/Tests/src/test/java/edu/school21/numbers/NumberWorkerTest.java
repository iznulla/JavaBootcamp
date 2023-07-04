package edu.school21.numbers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

class NumberWorkerTest {

  private final NumberWorker numberWorker = new NumberWorker();

  @ParameterizedTest
  @ValueSource(ints = {3, 19, 43})
  void isPrimeForPrimes(int candidate) {
    assertTrue(numberWorker.isPrime(candidate));
  }
  @ParameterizedTest
  @ValueSource(ints = {8, 4, 12})
  void isPrimeForNotPrimes(int candidate) {
    assertFalse(numberWorker.isPrime(candidate));
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 0, -2})
  void isPrimeForIncorrectNumbers(int candidate) {
    assertThrows(IllegalNumberExeption.class, () ->
      numberWorker.isPrime(candidate));
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/data.csv")
  void digitsSum(int input, int sum) {
    assertEquals(numberWorker.digitsSum(input), sum);
  }
}