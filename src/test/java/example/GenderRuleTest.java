package example;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.reader.YamlRuleDefinitionReader;
import org.junit.Test;
import org.mvel2.ParserContext;

import java.io.File;
import java.io.FileReader;

public class GenderRuleTest {

	@Test
	public void testGenderRules() throws Exception {
		File rulesDescriptor = new File("/Users/thai.ngo/Documents/mine/elide-spring-boot-example/src/test/resources/gender-rule.yml");
		ParserContext parserContext = new ParserContext();
		parserContext.addImport(Gender.class);

		MVELRuleFactory factory = new MVELRuleFactory(new YamlRuleDefinitionReader(), parserContext);
		Rules rules = factory.createRules(new FileReader(rulesDescriptor));

		Facts facts = new Facts();
		facts.put("person", new Person("foo", Gender.MALE));

		RulesEngine rulesEngine = new DefaultRulesEngine();
		rulesEngine.fire(rules, facts);
		facts.put("person", new Person("bar", Gender.FEMALE));
		rulesEngine.fire(rules, facts);
	}
	
	public static class Person {
		private String name;
		private Gender gender;
		private boolean male;

		public Person(String name, Gender gender) {
			this.name = name;
			this.gender = gender;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Gender getGender() {
			return gender;
		}

		public void setGender(Gender gender) {
			this.gender = gender;
		}

		public boolean isMale() {
			return male;
		}

		public void setMale(boolean male) {
			this.male = male;
		}
	}
	
	public enum Gender {
		MALE, FEMALE
	}
}