package example.models;

import com.yahoo.elide.annotation.Include;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Include
public class Company {

    @Id
    private String id;

    private CompanyType type = CompanyType.PRIVATE;

    private String desc = "";

}
