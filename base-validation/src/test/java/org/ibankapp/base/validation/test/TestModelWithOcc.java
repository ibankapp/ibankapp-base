/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.validation.test;

import org.ibankapp.base.validation.constraint.Occ;

public class TestModelWithOcc {

  private String occ;

  @Occ
  public String getOcc() {
    return occ;
  }

  public void setOcc(String occ) {
    this.occ = occ;
  }
}
