/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.domain;

import java.util.List;

public class Page<T> {

    /**
     * PAGESIZE 默认每页显示20行
     */
    public final static int PAGESIZE = 20;

    /**
     * pageSize 页面大小
     */
    private int pageSize = PAGESIZE;

    /**
     * items 查询结果列表
     */
    private List<T> items; // 查询结果

    /**
     * totalCount 记录的总数
     */
    private long totalCount; // 记录的总数

    /**
     * currentIndex  当前页数
     */
    private int currentIndex = 0; // 开始页数

    /**
     * <br/>Title: Page
     * <br/>Description: 构造函数
     *
     * @param items      查询结果列表
     * @param totalCount 记录的总数
     */
    public Page(List<T> items, long totalCount) {
        this(items, totalCount, 0);
    }

    /**
     * <br/>Title: Page
     * <br/>Description: 构造函数
     *
     * @param items        查询结果列表
     * @param totalCount   记录的总数
     * @param currentIndex 开始页数
     */
    public Page(List<T> items, long totalCount, int currentIndex) {
        this(items, totalCount, PAGESIZE, currentIndex);
    }

    /**
     * <br/>Title: Page
     * <br/>Description: 构造函数
     *
     * @param items        查询结果列表
     * @param totalCount   记录的总数
     * @param pageSize     页面大小
     * @param currentIndex 开始页数
     */
    public Page(List<T> items, long totalCount, int pageSize, int currentIndex) {
        setPageSize(pageSize);
        setTotalCount(totalCount);
        setItems(items);
        setCurrentIndex(currentIndex);
    }

    /**
     * <br/>Title: getItems
     * <br/>Description:  获取查询结果列表
     *
     * @return 查询结果列表
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * <br/>Title: setItems
     * <br/>Description:  设置查询结果列表
     *
     * @param items 查询结果列表
     */
    public void setItems(List<T> items) {
        this.items = items;
    }

    /**
     * <br/>Title: getPageSize
     * <br/>Description:  获取页面大小
     *
     * @return 页面大小
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * <br/>Title: setPageSize
     * <br/>Description:  设置页面大小
     *
     * @param pageSize 页面大小
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * <br/>Title: getTotalCount
     * <br/>Description:  获取记录的总数
     *
     * @return 记录的总数
     */
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * <br/>Title: setTotalCount
     * <br/>Description:  设置记录的总数，同时更新页号数组
     *
     * @param totalCount 记录的总数
     */
    public void setTotalCount(long totalCount) {
        if (totalCount > 0) {
            this.totalCount = totalCount;
        } else {
            this.totalCount = 0;
        }
    }

    /**
     * <br/>Title: getCurrentIndex
     * <br/>Description:  获取当前页数
     *
     * @return 当前页数
     */
    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * <br/>Title: setCurrentIndex
     * <br/>Description:  设置开始页数，如果无记录，设置为-1，否则，如果参数大于页面总数，则设置为最大页数，小于0则设置为0，
     * 其余设置为参数值
     *
     * @param index 开始页数
     */
    public void setCurrentIndex(int index) {
        if (index < 0)
            this.currentIndex = 0;
        else {
            this.currentIndex = index;
        }
    }
}
