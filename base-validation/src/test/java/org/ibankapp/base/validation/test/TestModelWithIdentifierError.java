/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.validation.test;

import org.ibankapp.base.validation.constraint.Identifier;
import org.ibankapp.base.validation.type.Idtp;

@Identifier(typefield = "idtp", codefield = "idno1")
class TestModelWithIdentifierError {

  private Idtp idtp;

  private String idno;

  public Idtp getIdtp() {
    return idtp;
  }

  void setIdtp(Idtp idtp) {
    this.idtp = idtp;
  }

  public String getIdno() {
    return idno;
  }

  void setIdno(String idno) {
    this.idno = idno;
  }
}
