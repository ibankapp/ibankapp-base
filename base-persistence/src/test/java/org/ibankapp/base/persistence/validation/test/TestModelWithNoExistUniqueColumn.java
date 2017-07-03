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

package org.ibankapp.base.persistence.validation.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.ibankapp.base.persistence.validation.constraint.Unique;

@Entity
@Table(name = "TEST_MODEL")
@Unique(properties = {"type"}, message = "姓名重复")
public class TestModelWithNoExistUniqueColumn {

  private String id;

  private String name;

  @Id
  @Column(length = 36)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Column
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
