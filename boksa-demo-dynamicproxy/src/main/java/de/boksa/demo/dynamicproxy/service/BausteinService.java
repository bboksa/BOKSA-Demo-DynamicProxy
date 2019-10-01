package de.boksa.demo.dynamicproxy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.boksa.demo.dynamicproxy.external.dao.HolzBausteinDAO;
import de.boksa.demo.dynamicproxy.external.dao.LegoSteinDAO;
import de.boksa.demo.dynamicproxy.external.model.BausteinFarbe;
import de.boksa.demo.dynamicproxy.model.Baustein;

public class BausteinService {

	private HolzBausteinDAO holzBausteinDAO = new HolzBausteinDAO();
	private LegoSteinDAO legoSteinDAO = new LegoSteinDAO();

	
	public List<Baustein> findAll() {
		List<Baustein> allBausteine = new ArrayList<>();
		allBausteine.addAll(this.findAllHolzBausteineAsBaustein());
		allBausteine.addAll(this.findAllLegoSteineAsBaustein());
		return allBausteine;
	}
	
	public List<Baustein> findByFarbe(BausteinFarbe farbe) {
		List<Baustein> allBausteine = this.findAll();
		
		// @formatter:off
		List<Baustein> bausteineWithFarbe = allBausteine.stream()
			.filter(baustein -> farbe.equals(baustein.getFarbe()))
			.collect(Collectors.toList());
		// @formatter:on
		
		return bausteineWithFarbe;
	}
	
	private List<Baustein> findAllLegoSteineAsBaustein() {
		// @formatter:off
		return legoSteinDAO.findAll().stream()
			.map(BausteinProxyFactory::createProxy)
			.collect(Collectors.toList());
		// @formatter:on
	}

	private List<Baustein> findAllHolzBausteineAsBaustein() {
		// @formatter:off
		return holzBausteinDAO.findAll().stream()
			.map(BausteinProxyFactory::createProxy)
			.collect(Collectors.toList());
		// @formatter:on
	}

}
