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


package org.ibankapp.base.validations.test.model;

import org.ibankapp.base.models.Model;
import org.ibankapp.base.validations.constraints.Unique;
import org.ibankapp.base.validations.constraints.Uniques;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TEST_MODELTESTWITHUNIQUES")
@Uniques(constraints = {@Unique(properties = {"name"}, message = "姓名重复"),
        @Unique(properties = "type", message = "类型重复")})
public class TestModelWithUniques extends Model{

    private String name;

    private String type;

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}