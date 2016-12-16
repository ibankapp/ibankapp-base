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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Unique(properties = "status",message = "ENUM重复")
@Table
public class TestModelWithEumAndUnique extends Model {

    private InheritanceType status;

    @Column
    @Enumerated(EnumType.STRING)
    public InheritanceType getStatus() {
        return status;
    }

    void setStatus(InheritanceType status) {
        this.status = status;
    }
}
