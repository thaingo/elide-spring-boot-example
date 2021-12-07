package example.models;

import com.yahoo.elide.annotation.Include;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Include
@Data
public class PickList {

    @Id
    private String id;

    private String value;
}
