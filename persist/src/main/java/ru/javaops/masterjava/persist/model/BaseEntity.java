package ru.javaops.masterjava.persist.model;

/**
 * Created by rolep on 07/04/17.
 */
abstract public class BaseEntity {

    protected Integer id;

    protected BaseEntity() {};

    protected BaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id : 0;
    }
}
