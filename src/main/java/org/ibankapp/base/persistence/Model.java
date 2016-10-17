/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence;

import java.io.Serializable;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 所有实体类的基类
 *
 * @author codelder
 * @version 1.0.0, 16/09/21
 */
@MappedSuperclass
public class Model implements Serializable {

    /**
     * 实体类主键
     */
    private String id;

    /**
     * 获取实体的主键
     *
     * @return 主键
     */
    @Id
    @Column(length = 36)
    public String getId() {
        return id;
    }

    /**
     * 设置实体类的主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 按UUID生成实体的主键
     *
     * @return 主键
     */
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
