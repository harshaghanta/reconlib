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
	public void exactMatchTest() {

		Date date = new Date();

		BaseTransaction bt = new BankTransaction(date, 200, "");
		BaseTransaction ct = new CashTransaction(date, 200, "");

		List<BaseTransaction> bankTrans = Collections.singletonList(bt);
		List<BaseTransaction> cashTrans = Collections.singletonList(ct);

		ReconciliationEngine engine = new ReconciliationEngine();
		BaseRule rule = new ExactMatchRule("bankamount", "cashamount");
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

			BaseTransaction bt = new BankTransaction(date, i, "");
			BaseTransaction ct = new CashTransaction(date, maxItems - i + 1, "");
			bankTrans.add(bt);
			cashTrans.add(ct);
		}

		ReconciliationEngine engine = new ReconciliationEngine();
		BaseRule rule = new ExactMatchRule("bankamount", "cashamount");
		engine.addRule(rule);

		long startMillis = System.currentTimeMillis();
		List<MatchingPair> matchingPairs = engine.run(bankTrans, cashTrans);
		long currentTimeMillis = System.currentTimeMillis();
		System.out.println("Time taken in millis" + (currentTimeMillis - startMillis));
		assertEquals(maxItems, matchingPairs.size());
	}

}
