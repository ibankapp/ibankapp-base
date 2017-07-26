/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.ibankapp.base.validation.exception.BaseValidationException;

/**
 * 证件号码验证器
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:codelder@ibankapp.org">codelder</a>
 * @since 1.0.0
 */
public class IdentifierValidation {

  /**
   * 校验传入的cardNo是否为合法的身份证号码格式.
   * <p>只检查身份证号码格式是否合法，并不进行联网核查</p>
   *
   * @param cardNo 待验证的号码
   * @return 是否合法的身份证号码格式
   */
  public static boolean isIdCardNo(String cardNo) {

    cardNo = cardNo.trim().toUpperCase();

    if (cardNo.length() != 18) {
      return false;
    }

    if (!StringUtils.isNumeric(cardNo.substring(0, 17))) {
      return false;
    }

    if (!Character.isDigit(cardNo.charAt(17)) && cardNo.charAt(17) != 'X') {
      return false;
    }

    Set<String> zones = new HashSet<String>();

    Collections.addAll(zones, "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33",
        "34", "35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53",
        "54", "61", "62", "63", "64", "71", "81", "82", "91");

    if (!zones.contains(cardNo.substring(0, 2))) {
      return false;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    sdf.setLenient(false);

    try {
      Date birthday = sdf.parse(cardNo.substring(6, 14));
      if (birthday.after(new Date())) {
        return false;
      }
    } catch (ParseException e) {
      return false;
    }

    return cardNo.charAt(17) == getIdCardNoCheckBit(cardNo.substring(0, 17));

  }

  /**
   * 校验传入的occ是否为合法的组织机构代码格式.
   * <p>只检查组织机构代码格式是否合法，并不进行联网核查</p>
   *
   * @param occ 待验证的号码
   * @return 是否合法的组织机构代码格式
   */
  public static boolean isOcc(String occ) {
    occ = occ.trim().toUpperCase();

    return occ.length() == 9 && !(!Character.isDigit(occ.charAt(8)) && occ.charAt(8) != 'X')
        && occ.charAt(8) == getOccCheckBit(occ.substring(0, 8));

  }

  /**
   * 校验传入的uscic是否为合法的统一法人信用代码格式.
   * <p>只检查统一法人信用代码格式是否合法，并不进行联网核查</p>
   *
   * @param uscic 待验证的号码
   * @return 是否合法的统一法人信用代码格式
   */
  public static boolean isUscic(String uscic) {

    uscic = uscic.trim().toUpperCase();

    if (uscic.length() != 18) {
      return false;
    }

    Set<Character> set = new HashSet<Character>();

    Collections.addAll(set, '1', '5', '9', 'Y');

    if (!set.contains(uscic.charAt(0))) {
      return false;
    }

    set = new HashSet<Character>();
    if (uscic.charAt(0) == '1' || uscic.charAt(0) == '5') {
      Collections.addAll(set, '1', '2', '3', '9');
    } else if (uscic.charAt(0) == '9') {
      Collections.addAll(set, '1', '2', '3');
    } else {
      Collections.addAll(set, '1');
    }

    return set.contains(uscic.charAt(1)) && StringUtils.isNumeric(uscic.substring(2, 8))
        && isOcc(uscic.substring(8, 17)) && getUscicCheckBit(uscic.substring(0, 17)) == uscic
        .charAt(17);

  }

  /**
   * 获取身份证号校验位.
   *
   * @param code 除校验位外身份证其他部分
   * @return 校验位
   */
  public static char getIdCardNoCheckBit(String code) {

    code = code.trim();

    if (code.length() != 17) {
      throw new BaseValidationException("E-BASE-VALIDATION-000001", "号码长度错误");
    }

    if (!StringUtils.isNumeric(code)) {
      throw new BaseValidationException("E-BASE-VALIDATION-000001", "号码包含非数字字符");
    }

    int[] wi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    char[] check = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

    int sum = 0;

    for (int i = 0; i < 17; i++) {
      sum += (code.charAt(i) - 48) * wi[i];
    }

    int mod = sum % 11;

    return check[mod];

  }

  /**
   * 根据传入的代码前八位计算组织机构代码的校验位.
   *
   * @param code 组织机构代码前八位
   * @return 校验位
   */
  public static char getOccCheckBit(String code) {

    code = code.trim().toUpperCase();

    if (code.length() != 8) {
      throw new BaseValidationException("E-BASE-VALIDATION-000001", "号码长度错误");
    }

    int[] wi = {3, 7, 9, 10, 5, 8, 4, 2};

    char[] ch = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
        'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    int sum = 0;

    for (int i = 0; i < 8; i++) {
      int ci = 37;
      for (int j = 0; j < ch.length; j++) {
        if (ch[j] == code.charAt(i)) {
          ci = j;
          break;
        }
      }
      if (ci == 37) {
        throw new BaseValidationException("E-BASE-VALIDATION-000002", "号码中");
      }
      sum += wi[i] * ci;
    }

    int c9 = 11 - (sum % 11);

    char checkBit;

    if (c9 == 10) {
      checkBit = 'X';
    } else if (c9 == 11) {
      checkBit = '0';
    } else {
      checkBit = (char) (c9 + 48);
    }

    return checkBit;
  }

  /**
   * 根据传入的代码前八位计算统一法人信用代码的校验位.
   *
   * @param code 统一法人信用代码前八位
   * @return 校验位
   */
  public static char getUscicCheckBit(String code) {

    code = code.trim();

    if (code.length() != 17) {
      throw new BaseValidationException("E-BASE-VALIDATION-000001", "号码长度错误");
    }

    int[] wi = {1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28};

    char[] ch = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
        'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'T', 'U', 'W', 'X', 'Y'};

    int sum = 0;

    for (int i = 0; i < 17; i++) {
      int ci = 32;
      for (int j = 0; j < ch.length; j++) {
        if (ch[j] == code.charAt(i)) {
          ci = j;
          break;
        }
      }
      if (ci == 32) {
        throw new BaseValidationException("E-BASE-VALIDATION-000002", "号码中");
      }
      sum += wi[i] * ci;
    }

    int c18 = 31 - (sum % 31);

    char checkBit;

    if (c18 == 31) {
      checkBit = '0';
    } else {
      checkBit = ch[c18];
    }

    return checkBit;
  }
}
