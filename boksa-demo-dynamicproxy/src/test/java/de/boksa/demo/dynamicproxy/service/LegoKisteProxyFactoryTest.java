package de.boksa.demo.dynamicproxy.service;

import de.boksa.demo.dynamicproxy.external.model.LegoKiste;

public class LegoKisteProxyFactoryTest extends AbstractLegoKisteTest {

	@Override
	protected LegoKiste buildLegoKiste() throws ReflectiveOperationException {
		return LegoKisteProxyFactory.createProxy();
	}

}