package example.models;

import com.yahoo.elide.annotation.Include;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Include
@Data
public class AssignmentRule {

    @Id
    private String id;

    // using JEXL e.g. condition == PRIVATE
    private String expr;
    private String action;
}
