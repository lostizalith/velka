package com.github.lostizalith.velka.category.entity;

import com.github.lostizalith.velka.global.entity.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "internal_category")
public class InternalCategoryEntity extends Auditable {

    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue
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
    @Column(name = "ic_deactivated", updatable = false, insertable = false)
    private LocalDateTime deactivated;
}
