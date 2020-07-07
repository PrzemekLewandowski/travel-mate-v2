package com.myapp.travelmate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@Document
public final class Post {

    private final String title;
    private final String description;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDateTime travelDateFrom;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDateTime travelDateTo;
    private final int budgetValueFrom;
    private final int budgetValueTo;
    private final String imageFileName;
    @DBRef
    private final User author;
    private final Set<String> countries;
    @DBRef
    private final Set<User> participants;
    private final Long numberOfViews;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDateTime createdAt;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDateTime editedAt;
    @Id
    private String id;

    private Post(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.travelDateFrom = builder.travelDateFrom;
        this.travelDateTo = builder.travelDateTo;
        this.budgetValueFrom = builder.budgetValueFrom;
        this.budgetValueTo = builder.budgetValueTo;
        this.imageFileName = builder.imageFileName;
        this.author = builder.author;
        this.countries = builder.countries;
        this.participants = builder.participants;
        this.numberOfViews = builder.numberOfViews;
        this.createdAt = builder.createdAt;
        this.editedAt = builder.editedAt;
    }

    public static class Builder {
        private String title;
        private String description;
        private LocalDateTime travelDateFrom;
        private LocalDateTime travelDateTo;
        private int budgetValueFrom;
        private int budgetValueTo;
        private String imageFileName;
        private User author;
        private Set<String> countries;
        private Set<User> participants = Collections.emptySet();
        private Long numberOfViews = 0L;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime editedAt = LocalDateTime.now();

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder travelDateFrom(LocalDateTime travelDateFrom) {
            this.travelDateFrom = travelDateFrom;
            return this;
        }

        public Builder travelDateTo(LocalDateTime travelDateTo) {
            this.travelDateTo = travelDateTo;
            return this;
        }

        public Builder budgetValueFrom(int budgetValueFrom) {
            this.budgetValueFrom = budgetValueFrom;
            return this;
        }

        public Builder budgetValueTo(int budgetValueTo) {
            this.budgetValueTo = budgetValueTo;
            return this;
        }

        public Builder imageFileName(String imageFileName) {
            this.imageFileName = imageFileName;
            return this;
        }

        public Builder author(User author) {
            this.author = author;
            return this;
        }

        public Builder countries(Set<String> countries) {
            this.countries = countries;
            return this;
        }

        public Builder participants(Set<User> participants) {
            this.participants = participants;
            return this;
        }

        public Builder numberOfViews(long numberOfViews) {
            this.numberOfViews = numberOfViews;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder editedAt(LocalDateTime editedAt) {
            this.editedAt = editedAt;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}
