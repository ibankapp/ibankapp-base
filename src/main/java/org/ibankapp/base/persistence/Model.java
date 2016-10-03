package org.ibankapp.base.persistence;

import java.io.Serializable;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 所有实体类的基类
 *
 *
 * @version        1.0.0, 16/09/21
 * @author         codelder
 */
@MappedSuperclass
public class Model implements Serializable {

    /** 实体类主键 */
    private String id;

    /**
     * 默认构造函数,使用UUID来生成主键
     *
     */
    public Model() {
        this.id = UUID.randomUUID().toString();
    }

    /**
     * 获取实体的主键
     *
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
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }
}
