package com.github.lostizalith.velka.record.entity;

import com.github.lostizalith.velka.account.entity.AccountEntity;
import com.github.lostizalith.velka.category.entity.InternalCategoryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "record")
public class RecordEntity {

    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue
    @Column(name = "r_id", nullable = false)
    private UUID id;

    @Size(max = 256)
    @Column(name = "r_memo")
    private String memo;

    @NotNull
    @Column(name = "r_type")
    @Enumerated(EnumType.STRING)
    private RecordType recordType;

    @Column(name = "r_flow")
    private Double flow;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "ic_id")
    private InternalCategoryEntity internalCategory;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "a_id")
    private AccountEntity account;

    @EqualsAndHashCode.Exclude
    @CreatedDate
    @Column(name = "r_created", updatable = false)
    private LocalDateTime created;

    @EqualsAndHashCode.Exclude
    @LastModifiedDate
    @Column(name = "r_modified")
    private LocalDateTime modified;

    @EqualsAndHashCode.Exclude
    @Column(name = "r_deactivated", updatable = false, insertable = false)
    private LocalDateTime deactivated;
}
