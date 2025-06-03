package com.istl.app.domain.mobileapp;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * A MessageTemplate.
 */
@Entity
@Table(name = "message_template")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MessageTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    // @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    // @SequenceGenerator(name = "sequenceGenerator")
    // @Column(name = "id")
    // private Long id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    private Long id;

    @Size(max = 50)
    @Column(name = "messagetype", length = 50)
    private String messagetype;

    @Size(max = 200)
    @Column(name = "description", length = 200)
    private String description;

    @Size(max = 4000)
    @Column(name = "messageenglish", length = 4000)
    private String messageenglish;

    @Size(max = 4000)
    @Column(name = "messagesomali", length = 4000)
    private String messagesomali;

    @Column(name = "createdon")
    private Instant createdon;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public MessageTemplate id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessagetype() {
        return this.messagetype;
    }

    public MessageTemplate messagetype(String messagetype) {
        this.setMessagetype(messagetype);
        return this;
    }

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }

    public String getDescription() {
        return this.description;
    }

    public MessageTemplate description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessageenglish() {
        return this.messageenglish;
    }

    public MessageTemplate messageenglish(String messageenglish) {
        this.setMessageenglish(messageenglish);
        return this;
    }

    public void setMessageenglish(String messageenglish) {
        this.messageenglish = messageenglish;
    }

    public String getMessagesomali() {
        return this.messagesomali;
    }

    public MessageTemplate messagesomali(String messagesomali) {
        this.setMessagesomali(messagesomali);
        return this;
    }

    public void setMessagesomali(String messagesomali) {
        this.messagesomali = messagesomali;
    }

    public Instant getCreatedon() {
        return this.createdon;
    }

    public MessageTemplate createdon(Instant createdon) {
        this.setCreatedon(createdon);
        return this;
    }

    public void setCreatedon(Instant createdon) {
        this.createdon = createdon;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MessageTemplate)) {
            return false;
        }
        return getId() != null && getId().equals(((MessageTemplate) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MessageTemplate{" +
            "id=" + getId() +
            ", messagetype='" + getMessagetype() + "'" +
            ", description='" + getDescription() + "'" +
            ", messageenglish='" + getMessageenglish() + "'" +
            ", messagesomali='" + getMessagesomali() + "'" +
            ", createdon='" + getCreatedon() + "'" +
            "}";
    }
}
