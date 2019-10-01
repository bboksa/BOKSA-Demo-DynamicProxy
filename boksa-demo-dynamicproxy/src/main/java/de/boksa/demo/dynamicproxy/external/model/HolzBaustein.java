package de.boksa.demo.dynamicproxy.external.model;

import java.util.UUID;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class HolzBaustein {

	private final UUID id;
	private BausteinFarbe farbe;
	private BausteinForm form;

}
