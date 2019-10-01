package de.boksa.demo.dynamicproxy.service;

import de.boksa.demo.dynamicproxy.external.model.LegoKiste;
import de.boksa.demo.dynamicproxy.model.LeiseLegoKiste;

public class LeiseLegoKisteTest extends AbstractLegoKisteTest {

	@Override
	protected LegoKiste buildLegoKiste() {
		return new LeiseLegoKiste();
	}

}