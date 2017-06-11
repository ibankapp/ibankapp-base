/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.domain;

import java.io.Serializable;

/**
 * 分页请求配置类
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:codelder@ibankapp.org">codelder</a>
 * @since 1.0.0
 */
public class Pageable implements Serializable {

    /**
     * 开始页数
     */
    private int page;

    /**
     * 每页记录数量
     */
    private int size;

    /**
     * 构造函数
     *
     * @param page 开始页数
     * @param size 每页记录数量
     */
    public Pageable(int page, int size) {
        this.page = page;
        this.size = size;
    }

    /**
     * 获取 开始页数
     *
     * @return 开始页数
     */
    public int getPage() {
        return page;
    }

    /**
     * 设置 开始页数
     *
     * @param page 开始页数
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * 获取 每页记录数量
     *
     * @return 每页记录数量
     */
    public int getSize() {
        return size;
    }

    /**
     * 设置 每页记录数量
     *
     * @param size 每页记录数量
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 获取 开始记录条数
     *
     * @return 开始记录条数
     */
    public int getOffset() {
        return getPage() * getSize();
    }
}
