package dev.arman.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author mdarmanansari
 */

@Getter
@Setter
@Entity
public class Region extends BaseModel {
    private String name;
}
