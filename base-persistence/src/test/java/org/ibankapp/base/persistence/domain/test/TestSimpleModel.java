/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.domain.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TestSimpleModel {

  private String name;

  private int age;

  @Id
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column
  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
