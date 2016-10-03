package org.ibankapp.base.persistence.test;

import org.ibankapp.base.persistence.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TEST_MODEL")
public class TestModel extends Model {

    private String name;

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
