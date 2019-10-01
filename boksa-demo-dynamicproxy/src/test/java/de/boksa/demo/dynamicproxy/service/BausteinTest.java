package de.boksa.demo.dynamicproxy.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.boksa.demo.dynamicproxy.model.Baustein;

public class BausteinTest {

	// SUT
	private BausteinService bausteinService;
	
	@Before
	public void init() {
		bausteinService = new BausteinService();
	}
	
	@Test
	public void trittDrauf() {
		bausteinService.findAll().forEach(Baustein::trittDrauf);
		
		Assert.assertTrue(true);
	}

}