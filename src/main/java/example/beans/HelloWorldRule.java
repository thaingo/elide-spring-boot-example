package example.beans;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.springframework.stereotype.Component;

@Component
@Rule(name = "Hello World rule", description = "Always say hello world")
public class HelloWorldRule {

    @Condition
    public boolean when() {
        return true;
    }

    @Action
    public void then() {
        System.out.println("hello world");
    }

}