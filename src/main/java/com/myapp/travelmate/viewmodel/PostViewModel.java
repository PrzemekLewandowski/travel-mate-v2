package com.myapp.travelmate.viewmodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myapp.travelmate.model.User;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class PostViewModel {

    @JsonIgnore
    private final LocalDateTime editedAt = LocalDateTime.now();

    @Id
    private String id;

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
    private Set<String> countries;

    private Long numberOfViews;
}
