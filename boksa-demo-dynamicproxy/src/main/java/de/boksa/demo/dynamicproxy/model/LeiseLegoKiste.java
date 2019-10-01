package de.boksa.demo.dynamicproxy.model;

import de.boksa.demo.dynamicproxy.external.model.LegoKiste;
import de.boksa.demo.dynamicproxy.external.model.LegoStein;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LeiseLegoKiste extends LegoKiste {

	@Override
	public void wirfSteinHinein(LegoStein legoStein) {
		log.debug(".");
		this.getLegosteine().add(legoStein);
	}

	
}
