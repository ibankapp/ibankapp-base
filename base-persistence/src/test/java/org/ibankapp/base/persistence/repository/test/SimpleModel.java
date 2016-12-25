/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.repository.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SIMPLE_MODEL")
class SimpleModel {

    private String name;

    private String id;

    @Id
    @Column(length = 36)
    String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    @Column
    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }
}
