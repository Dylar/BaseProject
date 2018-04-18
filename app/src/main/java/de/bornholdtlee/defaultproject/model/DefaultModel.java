package de.bornholdtlee.defaultproject.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DefaultModel {

    @Id
    private long id;
    private String name;
}