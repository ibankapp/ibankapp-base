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

import org.ibankapp.base.persistence.validation.constraint.Unique;
import org.ibankapp.base.persistence.validation.constraint.Uniques;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEST_MODELTESTWITHUNIQUES")
@Uniques(constraints = {@Unique(properties = {"name"}, message = "姓名重复"),
        @Unique(properties = "type", message = "类型重复")})
class TestModelWithUniques {

    private String name;

    private String type;

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

    void setType(String type) {
        this.type = type;
    }
}