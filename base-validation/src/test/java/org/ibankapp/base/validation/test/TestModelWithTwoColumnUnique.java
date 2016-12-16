/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.validation.test;


import org.ibankapp.base.persistence.model.Model;
import org.ibankapp.base.validation.constraint.Unique;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Unique(properties = {"name","type"},message = "姓名及类型重复")
public class TestModelWithTwoColumnUnique extends Model {

    private String name;

    private int type;

    @Column
    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    @Column
    public int getType() {
        return type;
    }

    void setType(int type) {
        this.type = type;
    }
}