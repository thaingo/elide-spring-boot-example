package example.beans;

import com.yahoo.elide.annotation.LifeCycleHookBinding;
import com.yahoo.elide.core.lifecycle.LifeCycleHook;
import com.yahoo.elide.core.security.ChangeSpec;
import com.yahoo.elide.core.security.RequestScope;
import com.yahoo.elide.datastores.jpa.transaction.AbstractJpaTransaction;

import example.models.AssignmentRule;
import example.models.Company;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.mvel.MVELRule;
import org.mvel2.ParserContext;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Slf4j
public class CompanyCreation implements LifeCycleHook<Company> {

    @Autowired
    private RulesEngine rulesEngine;

    @Autowired
    private Rules rules;

    @Autowired
    private ParserContext parserContext;

    @Override
    public void execute(LifeCycleHookBinding.Operation operation,
                        LifeCycleHookBinding.TransactionPhase transactionPhase, Company company,
                        RequestScope scope, Optional<ChangeSpec> changeSpec) {

        final EntityManager em = scope.getTransaction().getProperty(AbstractJpaTransaction.ENTITY_MANAGER_PROPERTY);
        final Query ruleQuery = em.createQuery("select ar from AssignmentRule ar");
        final AssignmentRule rule = (AssignmentRule) ruleQuery.getSingleResult();

        final MVELRule jxelRule = new MVELRule(parserContext)
                .when(rule.getExpr())
                .then(rule.getAction());

        final Facts facts = new Facts();
        facts.put("company", company);

        rules.register(jxelRule);
        rulesEngine.fire(rules, facts);
    }
}
