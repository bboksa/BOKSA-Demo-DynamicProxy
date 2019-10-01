package de.boksa.demo.dynamicproxy.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.boksa.demo.dynamicproxy.external.dao.LegoSteinDAO;
import de.boksa.demo.dynamicproxy.external.model.LegoKiste;

public class LegoKisteTest {

	private LegoSteinDAO legoSteinDAO;

	// SUT
	private LegoKiste legoKiste;
	
	@Before
	public void init() throws IllegalArgumentException, SecurityException, ReflectiveOperationException {
		legoSteinDAO = new LegoSteinDAO();
		legoKiste = new LegoKiste();
//		legoKiste = new LeiseLegoKiste();
//		legoKiste = LegoKisteProxyFactory.createProxy();
	}
	
	@Test
	public void trittDrauf() {
		legoSteinDAO.findAll().forEach(legoKiste::wirfSteinHinein);
		
		Assert.assertEquals(30, legoKiste.getLegosteine().size());
	}
}