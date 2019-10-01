package de.boksa.demo.dynamicproxy.model;

import java.util.UUID;

import de.boksa.demo.dynamicproxy.external.model.BausteinFarbe;
import de.boksa.demo.dynamicproxy.external.model.BausteinForm;

public interface Baustein {

	UUID getId();
	BausteinFarbe getFarbe();
	BausteinForm getForm();
	
	void trittDrauf();

	Class<?> typeOf();
	
}
