package de.boksa.demo.dynamicproxy.external.model;

import java.util.UUID;

import lombok.Data;

@Data
public class HolzBaustein {

	private final UUID id;
	private BausteinFarbe farbe;
	private BausteinForm form;

}
