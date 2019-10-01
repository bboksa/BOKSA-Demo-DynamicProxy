package de.boksa.demo.dynamicproxy.service;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.boksa.demo.dynamicproxy.external.model.LegoKiste;
import de.boksa.demo.dynamicproxy.external.model.LegoStein;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;

public class LegoKisteProxyFactory {

	public static LegoKiste createProxy() throws ReflectiveOperationException, IllegalArgumentException, SecurityException {
		ProxyFactory factory = new ProxyFactory();
		factory.setSuperclass(LegoKiste.class);
		factory.setFilter(method -> "wirfSteinHinein".equals(method.getName()));
		Class<?> legoKisteProxyClass = factory.createClass();
		LegoKiste legoKiste = (LegoKiste) legoKisteProxyClass.getConstructor()
				.newInstance();
		((Proxy) legoKiste).setHandler(new WirfSteinHineinMethodHandler());
		return legoKiste;
	}
	
	private static final class WirfSteinHineinMethodHandler implements MethodHandler {
		
		private final Logger log = LoggerFactory.getLogger(LegoKiste.class);
		
		public Object invoke(Object self, Method method, Method proceed, Object[] args) throws Throwable {
			LegoKiste legoKiste = (LegoKiste) self;
			log.debug("•");
			legoKiste.getLegosteine().add((LegoStein) args[0]);			
			return null;
		}
		
	}

}