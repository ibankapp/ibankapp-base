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

import javax.persistence.*;

@Entity
@Unique(properties = "status",message = "ENUM重复")
@Table
public class TestModelWithEumAndUnique{

    private String id;

    private InheritanceType status;

    @Id
    @Column(length =32)
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

    public void setStatus(InheritanceType status) {
        this.status = status;
    }
}
