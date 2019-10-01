package de.boksa.demo.dynamicproxy.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.boksa.demo.dynamicproxy.external.model.BausteinFarbe;
import de.boksa.demo.dynamicproxy.model.Baustein;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BausteinServiceTest {

	// SUT
	private BausteinService bausteinService;
	
	@Before
	public void init() {
		bausteinService = new BausteinService();
	}
	
	@Test
	public void findByFarbe() {
		// @formatter:off
		Map<BausteinFarbe,List<Baustein>> bausteineByFarbe = Arrays.asList(BausteinFarbe.values()).stream()
			.map(bausteinService::findByFarbe)
			.flatMap(List::stream)
			.collect(Collectors.groupingBy(Baustein::getFarbe));
		// @formatter:on
		
		// @formatter:off
		int anzahlBausteine = bausteineByFarbe.entrySet().stream()
			.peek(bausteineByFarbeEntry -> {
				log.debug("{} -> {}",
					bausteineByFarbeEntry.getKey(),
					bausteineByFarbeEntry.getValue().size()
				);
			})
			.map(Entry::getValue)
			.mapToInt(List::size)
			.sum();
		// @formatter:on
		
		Assert.assertEquals(60, anzahlBausteine);
	}

}