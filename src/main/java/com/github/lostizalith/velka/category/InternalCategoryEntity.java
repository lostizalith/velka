package com.github.lostizalith.velka.category;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "internal_category")
public class InternalCategoryEntity {

    @EqualsAndHashCode.Exclude
    @Id
    @Column(name = "ic_id", nullable = false)
    private UUID id;

    @Column(name = "ic_display_name")
    private String displayName;

    @Column(name = "ic_icon")
    private String icon;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_id")
    private CategoryEntity category;

    @EqualsAndHashCode.Exclude
    @CreatedDate
    @Column(name = "ic_created", updatable = false)
    private LocalDateTime created;

    @EqualsAndHashCode.Exclude
    @LastModifiedDate
    @Column(name = "ic_modified")
    private LocalDateTime modified;

    @EqualsAndHashCode.Exclude
    @Column(name = "ic_deactivated", updatable = false, insertable = false)
    private LocalDateTime deactivated;
}
