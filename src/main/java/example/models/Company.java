package example.models;

import com.yahoo.elide.annotation.Include;
import com.yahoo.elide.annotation.LifeCycleHookBinding;

import example.beans.CompanyCreation;

import lombok.Data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Include
@LifeCycleHookBinding(hook = CompanyCreation.class, operation = LifeCycleHookBinding.Operation.CREATE)
@Data
public class Company {

    @Id
    private String id;

    private String type;
    private Date crAt;

}
