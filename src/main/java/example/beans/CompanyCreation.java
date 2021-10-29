package example.beans;

import com.yahoo.elide.annotation.LifeCycleHookBinding;
import com.yahoo.elide.core.lifecycle.LifeCycleHook;
import com.yahoo.elide.core.security.ChangeSpec;
import com.yahoo.elide.core.security.RequestScope;

import example.models.Company;
import example.models.CompanyType;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CompanyCreation implements LifeCycleHook<Company> {

    @Autowired
    private RulesEngine rulesEngine;
    @Autowired
    private HelloWorldRule helloWorldRule;

    @Override
    public void execute(LifeCycleHookBinding.Operation operation,
                        LifeCycleHookBinding.TransactionPhase transactionPhase, Company company,
                        RequestScope requestScope, Optional<ChangeSpec> changeSpec) {
        if (company.getType().equals(CompanyType.PRIVATE)) {
            final Facts facts = new Facts();
            Rules rules = new Rules();
            rules.register(helloWorldRule);
            rulesEngine.fire(rules, facts);
        }
    }
}
