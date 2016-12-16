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
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Unique(properties = {"name"},message = "姓名重复")
class TestModelWithUnique extends Model {

    private String name;

    @Column
    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

}