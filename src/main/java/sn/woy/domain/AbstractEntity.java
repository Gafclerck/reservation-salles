package sn.woy.domain;

public class AbstractEntity {
    protected AbstractEntity() {
    }

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}