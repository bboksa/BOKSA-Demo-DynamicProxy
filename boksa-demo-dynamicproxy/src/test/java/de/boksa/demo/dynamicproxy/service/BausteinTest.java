package de.boksa.demo.dynamicproxy.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.boksa.demo.dynamicproxy.model.Baustein;

public class BausteinTest {

	// SUT
	List<Baustein> bausteine;
	
	@Before
	public void init() {
		bausteine = (new BausteinService()).findAll();
	}
	
	@Test
	public void trittDrauf() {		
		bausteine.forEach(Baustein::trittDrauf);
		
		Assert.assertTrue(true);
	}

}