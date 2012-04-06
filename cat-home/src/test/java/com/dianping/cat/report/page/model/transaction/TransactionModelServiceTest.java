package com.dianping.cat.report.page.model.transaction;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.dianping.cat.Cat;
import com.dianping.cat.report.page.model.spi.ModelRequest;
import com.dianping.cat.report.page.model.spi.ModelResponse;
import com.dianping.cat.report.page.model.spi.ModelService;
import com.site.lookup.ComponentTestCase;

@RunWith(JUnit4.class)
public class TransactionModelServiceTest extends ComponentTestCase {
	@Before
	public void before() throws Exception {
		if (!Cat.isInitialized()) {
			Cat.initialize(null);
		}
	}

	@Test
	public void testLookup() throws Exception {
		ModelService<?> local = lookup(ModelService.class, "transaction-local");
		ModelService<?> composite = lookup(ModelService.class, "transaction");

		Assert.assertEquals(LocalTransactionService.class, local.getClass());
		Assert.assertEquals(CompositeTransactionService.class, composite.getClass());
	}

	@Test
	public void testLocal() throws Exception {
		LocalTransactionService local = (LocalTransactionService) lookup(ModelService.class, "transaction-local");
		ModelResponse<?> response = local.invoke(ModelRequest.from("Cat", "CURRENT"));

		Assert.assertEquals("null", String.valueOf(response.getModel()));
	}
}
