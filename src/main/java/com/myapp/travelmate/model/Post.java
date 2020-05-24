package com.myapp.travelmate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Document
public class Post implements Serializable {

    @Id
    private String id;

    private String title;

    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime travelDateFrom;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime travelDateTo;

    private int budgetValueFrom;

    private int budgetValueTo;

    private String imageFileName;

    private User author;

    private List<Country> countries;

    private Long numberOfViews;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime editedAt;
}
