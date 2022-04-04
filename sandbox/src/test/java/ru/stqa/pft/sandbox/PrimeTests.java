package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {

    @Test
    public void testPrime(){
        Assert.assertTrue(Primes.isPrime(13));
    }

    @Test
    public void testNonPrime(){
        Assert.assertFalse(Primes.isPrime(10));
    }
}
