package de.boksa.demo.dynamicproxy.external.dao;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import de.boksa.demo.dynamicproxy.external.model.BausteinFarbe;
import de.boksa.demo.dynamicproxy.external.model.BausteinForm;
import de.boksa.demo.dynamicproxy.external.model.LegoStein;

public class LegoSteinDAO {

	private static final SecureRandom random = new SecureRandom();

	private static final List<LegoStein> LEGOSTEINE;
	static {
		// @formatter:off
		LEGOSTEINE = IntStream.rangeClosed(1, 30)
			.mapToObj(i -> new LegoStein(UUID.randomUUID()))
			.peek(l -> l.setFarbe(randomEnumValue(BausteinFarbe.class)))
			.peek(l -> l.setForm(randomEnumValue(BausteinForm.class)))
			.peek(l -> l.setAnzahlNoppen(random.nextInt(8) + 1))
			.collect(Collectors.toList());		 
		// @formatter:on
	}

	public List<LegoStein> findAll() {
		return LEGOSTEINE;
	}

	public static <T extends Enum<?>> T randomEnumValue(Class<T> clazz) {
		int index = random.nextInt(clazz.getEnumConstants().length);
		return clazz.getEnumConstants()[index];
	}

}
