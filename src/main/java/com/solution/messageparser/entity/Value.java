package com.solution.messageparser.entity;


import javax.persistence.*;

@Entity
@Table(name = "values")
public class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long templateId;

    @Column
    private Long attrId;

    @Column
    private String value;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    @Column
    private Long messageId;

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long template_id) {
        this.templateId = template_id;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attr_id) {
        this.attrId = attr_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", templateId=" + templateId +
                ", attrId=" + attrId +
                ", value='" + value + '\'' +
                ", messageId=" + messageId +
                '}';
    }
}
