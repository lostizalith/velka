package com.github.lostizalith.velka.record;

import com.github.lostizalith.velka.category.InternalCategoryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "record")
public class RecordEntity {

    @EqualsAndHashCode.Exclude
    @Id
    @Column(name = "record_id", nullable = false)
    private UUID id;

    @Size(max = 256)
    @Column(name = "record_memo")
    private String memo;

    // TODO: record type instead and some field like value
    private String income;

    private String expense;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "int_category_id")
    private InternalCategoryEntity internalCategory;

    @EqualsAndHashCode.Exclude
    @CreatedDate
    @Column(name = "record_created", updatable = false)
    private LocalDateTime created;

    @EqualsAndHashCode.Exclude
    @LastModifiedDate
    @Column(name = "record_modified")
    private LocalDateTime modified;

    @EqualsAndHashCode.Exclude
    @Column(name = "record_deactivated", updatable = false, insertable = false)
    private LocalDateTime deactivated;
}
