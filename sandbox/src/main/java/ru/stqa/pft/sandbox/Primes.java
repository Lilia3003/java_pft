package ru.stqa.pft.sandbox;

public class Primes {
  public static boolean isPrimeFast(int n){
  int m = (int) Math.sqrt(n);
    for (int i = 2; i < m / 2; i = i + 1) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static boolean isPrimeWhile(int n) {
    int i = 2;
    while (i < n) {
      if (n % i == 0) {
        return false;
      }
      i++;
    }
    return true;
  }

  public static boolean isPrime(long n) {
    for (int i = 2; i < n; i = i + 1) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
}



