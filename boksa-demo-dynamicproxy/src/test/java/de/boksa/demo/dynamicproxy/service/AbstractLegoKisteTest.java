package de.boksa.demo.dynamicproxy.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.boksa.demo.dynamicproxy.external.dao.LegoSteinDAO;
import de.boksa.demo.dynamicproxy.external.model.LegoKiste;

public abstract class AbstractLegoKisteTest {

	// build SUT
	protected abstract LegoKiste buildLegoKiste() throws ReflectiveOperationException;
	
	private LegoSteinDAO legoSteinDAO;

	private LegoKiste legoKiste;
	
	@Before
	public void init() throws ReflectiveOperationException {
		legoSteinDAO = new LegoSteinDAO();
		legoKiste = this.buildLegoKiste();
	}

	@Test
	public void trittDrauf() {
		legoSteinDAO.findAll().forEach(legoKiste::wirfSteinHinein);

		Assert.assertEquals(30, legoKiste.getLegosteine().size());
	}
	
}
