package com.myapp.travelmate.viewmodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myapp.travelmate.model.Country;
import com.myapp.travelmate.model.User;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PostViewModel {

    @JsonIgnore
    private final LocalDateTime editedAt = LocalDateTime.now();

    @Id
    private long id;

    @NotNull
    @NonNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime travelDateFrom;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime travelDateTo;

    @NotNull
    private int budgetValueFrom;

    @NotNull
    private int budgetValueTo;

    @NotNull
    private Byte[] image;

    @NotNull
    private User author;

    @NotNull
    private List<Country> countries;

    private Long numberOfViews;
}
