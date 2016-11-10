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


package org.ibankapp.base.validation.test.model;

import org.ibankapp.base.validation.constraints.Unique;
import org.ibankapp.base.validation.constraints.Uniques;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEST_MODELTESTWITHUNIQUES")
@Uniques(constraints = {@Unique(properties = {"name"}, message = "姓名重复"),
        @Unique(properties = "type", message = "类型重复")})
public class TestModelWithUniques {

    private String id;

    private String name;

    private String type;

    @Id
    @Column(length = 32)
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

    @Column
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}