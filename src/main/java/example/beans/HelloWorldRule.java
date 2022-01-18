package example.beans;

import example.models.Company;
import example.models.CompanyType;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.springframework.stereotype.Component;

@Component
@Rule(name = "Hello World rule", description = "Always say hello world")
public class HelloWorldRule {

    @Condition
    public boolean when(@Fact(value = "company") Company company) {
        return company.getType().equals(CompanyType.PRIVATE);
    }

    @Action
    public void then() {
        System.out.println("hello private company");
    }

}