package com.github.lostizalith.velka.account.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.lostizalith.velka.record.entity.RecordEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "account")
@EntityListeners(AuditingEntityListener.class)
public class AccountEntity implements Persistable<UUID> {

    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue
    @Column(name = "a_id", nullable = false)
    private UUID id = UUID.randomUUID();

    @Size(max = 64)
    @Column(name = "a_display_name")
    private String displayName;

    @Size(max = 256)
    @Column(name = "a_short_description")
    private String shortDescription;

    @NotNull
    @Column(name = "a_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "a_current_balance")
    private Double currentBalance;

    @NotNull
    @Column(name = "a_currency")
    @Enumerated(EnumType.STRING)
    private AccountCurrency currency;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<RecordEntity> records = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @CreatedDate
    @Column(name = "a_created", updatable = false)
    private LocalDateTime created;

    @EqualsAndHashCode.Exclude
    @LastModifiedDate
    @Column(name = "a_modified")
    private LocalDateTime modified;

    @EqualsAndHashCode.Exclude
    @Column(name = "a_deactivated", updatable = false, insertable = false)
    private LocalDateTime deactivated;

    @JsonIgnore
    @Override
    public boolean isNew() {
        return this.id == null;
    }
}
