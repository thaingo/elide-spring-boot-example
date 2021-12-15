package example.models;

import com.yahoo.elide.annotation.Include;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Include
public class Company {

    @Id
    private String id;

    private CompanyType type;

    private String desc = "";

    private Date date = new Date();

}
