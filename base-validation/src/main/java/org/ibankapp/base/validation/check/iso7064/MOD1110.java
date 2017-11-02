package org.ibankapp.base.validation.check.iso7064;


import org.ibankapp.base.validation.check.Digit;

public class MOD1110 implements Digit {

  @Override
  public String encode(String digits) {
    int c = compute(digits);
    return digits + c;
  }

  @Override
  public boolean verify(String digits) {
    return compute(getNumber(digits)) == getDigit(digits) - '0';
  }

  @Override
  public int compute(String digits) {

    int i, Ai, Si, MSi, Pi, MPi, A;

    Ai = digits.charAt(0) - '0';

    //when i=1
    Si = 10 + Ai;
    MSi = Si % 10;
    if (MSi == 0)
      Pi = 10 * 2;
    else
      Pi = MSi * 2;

    MPi = Pi % 11;

    //when i> 1
    for (i = 1; i < digits.length(); i++) {
      Si = MPi + (digits.charAt(i)) - '0';
      MSi = Si % 10;
      if (MSi == 0)
        Pi = 10 * 2;
      else
        Pi = MSi * 2;

      MPi = Pi % 11;
    }

    switch (MPi) {
      case 0:
        A = 1;
        break;
      case 1:
        A = 0;
        break;
      default:
        A = 11 - MPi;
    }

    return A;
  }

  @Override
  public int getDigit(String digits) {
    return digits.charAt(digits.length() - 1);
  }

  @Override
  public String getNumber(String digits) {
    return digits.substring(0, digits.length() - 1);
  }
}
