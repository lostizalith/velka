package com.github.lostizalith.velka.category.entity;

import com.github.lostizalith.velka.global.entity.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "category")
public class CategoryEntity extends Auditable {

    @EqualsAndHashCode.Exclude
    @Id
    @Column(name = "c_id", nullable = false)
    @GeneratedValue
    private UUID id;

    @Size(max = 64)
    @Column(name = "c_display_name")
    private String displayName;

    @Column(name = "c_icon")
    private String icon;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<InternalCategoryEntity> internalCategories = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @Column(name = "c_deactivated", updatable = false, insertable = false)
    private LocalDateTime deactivated;
}
