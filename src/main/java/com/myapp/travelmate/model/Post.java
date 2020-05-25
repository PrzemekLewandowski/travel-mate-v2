package com.myapp.travelmate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Document
public class Post {

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

    @DBRef
    private User author;

    @DBRef
    private Set<User> participants;

    @DBRef
    private Set<String> countries;

    private Long numberOfViews;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime editedAt;
}
