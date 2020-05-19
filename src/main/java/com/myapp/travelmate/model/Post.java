package com.myapp.travelmate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDateTime travelDateFrom;

    @NotNull
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDateTime travelDateTo;

    @NotNull
    private int budgetValueFrom;

    @NotNull
    private int budgetValueTo;

    @Lob
    @NotNull
    private Byte[] image;

    @NotNull
    @ManyToOne()
    @JoinColumn(updatable = false)
    private User author;

    @NotNull
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "POST_COUNTRIES", joinColumns = {@JoinColumn(name = "POST_ID")}, inverseJoinColumns = {@JoinColumn(name = "COUNTRY_ID")})
    private List<Country> countries;

    private Long numberOfViews;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime editedAt;
}
