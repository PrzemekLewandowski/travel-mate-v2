package com.myapp.travelmate.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "roles")
public enum Role implements Serializable {

    @Id
    private String id;

    private String name;
}
