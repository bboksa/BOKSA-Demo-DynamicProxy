package de.boksa.demo.dynamicproxy.external.dao;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import de.boksa.demo.dynamicproxy.external.model.BausteinFarbe;
import de.boksa.demo.dynamicproxy.external.model.BausteinForm;
import de.boksa.demo.dynamicproxy.external.model.HolzBaustein;

public class HolzBausteinDAO {

	private static final List<HolzBaustein> HOLZBAUSTEINE;
	static {
		// @formatter:off
		HOLZBAUSTEINE = IntStream.rangeClosed(1, 30)
			.mapToObj(i -> new HolzBaustein(UUID.randomUUID()))
			.peek(l -> l.setFarbe(LegoSteinDAO.randomEnumValue(BausteinFarbe.class)))
			.peek(l -> l.setForm(LegoSteinDAO.randomEnumValue(BausteinForm.class)))
			.collect(Collectors.toList());		 
		// @formatter:on
	}

	public List<HolzBaustein> findAll() {
		return HOLZBAUSTEINE;
	}

}
