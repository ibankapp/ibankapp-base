/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.repository.test;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class CompositeId implements Serializable {

  private String id1;

  private String id2;

  public CompositeId() {
  }

  public CompositeId(String id1, String id2) {
    this.id1 = id1;
    this.id2 = id2;
  }

  public String getId1() {
    return id1;
  }

  public void setId1(String id1) {
    this.id1 = id1;
  }

  public String getId2() {
    return id2;
  }

  public void setId2(String id2) {
    this.id2 = id2;
  }
}
