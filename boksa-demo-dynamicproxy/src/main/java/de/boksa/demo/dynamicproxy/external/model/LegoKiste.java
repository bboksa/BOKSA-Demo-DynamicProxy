package de.boksa.demo.dynamicproxy.external.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class LegoKiste {

	private List<LegoStein> legosteine = new ArrayList<>();
	
	public void wirfSteinHinein(LegoStein legoStein) {
		log.error("KLAPPPPPPPPPPER");
		legosteine.add(legoStein);
	}
	
}