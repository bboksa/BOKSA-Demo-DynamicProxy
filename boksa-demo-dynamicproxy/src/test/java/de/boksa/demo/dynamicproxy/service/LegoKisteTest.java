package de.boksa.demo.dynamicproxy.service;

import de.boksa.demo.dynamicproxy.external.model.LegoKiste;

public class LegoKisteTest extends AbstractLegoKisteTest {

	@Override
	protected LegoKiste buildLegoKiste() {
		return new LegoKiste();
	}

}