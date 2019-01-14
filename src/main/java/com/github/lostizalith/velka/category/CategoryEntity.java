package com.github.lostizalith.velka.category;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "category")
public class CategoryEntity {

    @EqualsAndHashCode.Exclude
    @Id
    @Column(name = "c_id", nullable = false)
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
    @CreatedDate
    @Column(name = "c_created", updatable = false)
    private LocalDateTime created;

    @EqualsAndHashCode.Exclude
    @LastModifiedDate
    @Column(name = "c_modified")
    private LocalDateTime modified;

    @EqualsAndHashCode.Exclude
    @Column(name = "c_deactivated", updatable = false, insertable = false)
    private LocalDateTime deactivated;
}
