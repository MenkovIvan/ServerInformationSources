package ru.menkov.informsources.model.product;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "source",schema = "inf_sources")
public class Source {
    private Integer source_id;

    private String name;
    private String description;
    private Integer user_id;
    private Integer catalog_id;
    private String value;

    public Integer getSource_id() {
        return source_id;
    }

    public void setSource_id(Integer source_id) {
        this.source_id = source_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getCatalog_id() {
        return catalog_id;
    }

    public void setCatalog_id(Integer catalog_id) {
        this.catalog_id = catalog_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
