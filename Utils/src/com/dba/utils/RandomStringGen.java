package com.dba.utils;

import java.security.SecureRandom;
import java.math.BigInteger;

  public final class RandomStringGen {
	  
  private SecureRandom random = new SecureRandom();

  public String getRandomString () {
    return new BigInteger(130, random).toString(32);
  } 
}
