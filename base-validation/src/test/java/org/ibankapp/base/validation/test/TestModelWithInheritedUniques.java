/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.validation.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TEST_MODELWITHINHERITEDUNIQUES")
public class TestModelWithInheritedUniques extends TestModelWithUniques {

    private String no;

    @Column
    public String getNo() {
        return no;
    }

    public void setNo(String type) {
        this.no = no;
    }
}
