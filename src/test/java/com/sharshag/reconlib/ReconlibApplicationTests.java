package com.sharshag.reconlib;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReconlibApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void exactMatchTestWithAmountOnly() {

		Date date = new Date();

		BaseTransaction bt = new BaseTransaction();
		bt.setFieldValue("date", date);
		bt.setFieldValue("bankamount", 200);
		bt.setFieldValue("comments", "");

		BaseTransaction ct = new BaseTransaction();
		ct.setFieldValue("date", date);
		ct.setFieldValue("cashamount", 200);
		ct.setFieldValue("comments", "");

		List<BaseTransaction> bankTrans = Collections.singletonList(bt);
		List<BaseTransaction> cashTrans = Collections.singletonList(ct);

		ReconciliationEngine engine = new ReconciliationEngine();
		
		BaseRule rule = new One2One();
		RuleCondition ruleCondition1 = new RuleCondition("bankamount", "cashamount", MatchType.MATCHES_EXACTLY);
		rule.getRuleConditions().add(ruleCondition1);
		
		engine.addRule(rule);

		List<MatchingPair> matchingPairs = engine.run(bankTrans, cashTrans);
		assertEquals(1, matchingPairs.size());
	}

	@Test
	public void exactMatchTestWithAmountAndComments() {

		Date date = new Date();

		BaseTransaction bt = new BaseTransaction();
		bt.setFieldValue("date", date);
		bt.setFieldValue("bankamount", 200);
		bt.setFieldValue("comments", "test");

		BaseTransaction ct = new BaseTransaction();
		ct.setFieldValue("date", date);
		ct.setFieldValue("cashamount", 200);
		ct.setFieldValue("comments", "test");

		List<BaseTransaction> bankTrans = Collections.singletonList(bt);
		List<BaseTransaction> cashTrans = Collections.singletonList(ct);

		ReconciliationEngine engine = new ReconciliationEngine();
		
		BaseRule rule = new One2One();
		RuleCondition ruleCondition1 = new RuleCondition("bankamount", "cashamount", MatchType.MATCHES_EXACTLY);
		RuleCondition ruleCondition2 = new RuleCondition("comments", "comments", MatchType.MATCHES_EXACTLY);
		rule.getRuleConditions().add(ruleCondition1);
		rule.getRuleConditions().add(ruleCondition2);
		
		engine.addRule(rule);

		List<MatchingPair> matchingPairs = engine.run(bankTrans, cashTrans);
		assertEquals(1, matchingPairs.size());
	}

	@Test
	public void exactMatch10KItemsTest() {
		int maxItems = 10000;

		List<BaseTransaction> bankTrans = new ArrayList<>();
		List<BaseTransaction> cashTrans = new ArrayList<>();

		Date date = new Date();

		for (int i = 1; i <= maxItems; i++) {

			BaseTransaction bt = new BaseTransaction();
			bt.setFieldValue("date", date);
			bt.setFieldValue("bankamount", i);
			bt.setFieldValue("comments", "");

			BaseTransaction ct = new BaseTransaction();
			ct.setFieldValue("date", date);
			ct.setFieldValue("cashamount", maxItems - i + 1);
			ct.setFieldValue("comments", "");
			bankTrans.add(bt);
			cashTrans.add(ct);
		}

		ReconciliationEngine engine = new ReconciliationEngine();
		BaseRule rule = new One2One();
		RuleCondition ruleCondition1 = new RuleCondition("bankamount", "cashamount", MatchType.MATCHES_EXACTLY);
		rule.getRuleConditions().add(ruleCondition1);
		engine.addRule(rule);

		long startMillis = System.currentTimeMillis();
		List<MatchingPair> matchingPairs = engine.run(bankTrans, cashTrans);
		long currentTimeMillis = System.currentTimeMillis();
		System.out.println("Time taken in millis" + (currentTimeMillis - startMillis));
		assertEquals(maxItems, matchingPairs.size());
	}

}
