package com.istl.app.domain.mobileapp;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * A Channels.
 */
@Entity
@Table(name = "channels")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Channels implements Serializable {

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

    @Size(max = 15)
    @Column(name = "channel", length = 15)
    private String channel;

    @Size(max = 50)
    @Column(name = "description", length = 50)
    private String description;

    @Size(max = 6)
    @Column(name = "bin", length = 6)
    private String bin;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Channels id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannel() {
        return this.channel;
    }

    public Channels channel(String channel) {
        this.setChannel(channel);
        return this;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDescription() {
        return this.description;
    }

    public Channels description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBin() {
        return this.bin;
    }

    public Channels bin(String bin) {
        this.setBin(bin);
        return this;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Channels)) {
            return false;
        }
        return getId() != null && getId().equals(((Channels) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Channels{" +
            "id=" + getId() +
            ", channel='" + getChannel() + "'" +
            ", description='" + getDescription() + "'" +
            ", bin='" + getBin() + "'" +
            "}";
    }
}
