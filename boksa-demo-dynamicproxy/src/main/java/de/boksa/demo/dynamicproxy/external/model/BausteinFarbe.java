package de.boksa.demo.dynamicproxy.external.model;

import java.awt.Color;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BausteinFarbe {

	// @formatter:off
	ROT(Color.red),
	GRUEN(Color.green),
	BLAU(Color.blue);
	// @formatter:on

	private final Color color;

}
