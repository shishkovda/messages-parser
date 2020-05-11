package com.solution.messageparser.entity;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "templates")
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column
    private Long id;

    @Column
    private String requestor;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Column
    private String template;

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Transient
    @Formula("select")
    private List<Attribute> attributes;


    @Column
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Template{" +
                "id=" + id +
                ", requestor='" + requestor + '\'' +
                ", template='" + template + '\'' +
                ", attributes=" + attributes +
                ", name='" + name + '\'' +
                '}';
    }
}
