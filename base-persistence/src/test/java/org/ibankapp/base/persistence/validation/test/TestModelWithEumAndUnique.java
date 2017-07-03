/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.validation.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import org.ibankapp.base.persistence.validation.constraint.Unique;

@Entity
@Unique(properties = "status", message = "ENUM重复")
@Table
public class TestModelWithEumAndUnique {

  private InheritanceType status;

  private String id;

  @Id
  @Column(length = 36)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Column
  @Enumerated(EnumType.STRING)
  public InheritanceType getStatus() {
    return status;
  }

  void setStatus(InheritanceType status) {
    this.status = status;
  }
}
