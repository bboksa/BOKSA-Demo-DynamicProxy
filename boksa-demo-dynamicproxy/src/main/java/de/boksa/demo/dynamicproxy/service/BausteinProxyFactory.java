package de.boksa.demo.dynamicproxy.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Optional;

import de.boksa.demo.dynamicproxy.external.model.HolzBaustein;
import de.boksa.demo.dynamicproxy.external.model.LegoStein;
import de.boksa.demo.dynamicproxy.model.Baustein;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

public class BausteinProxyFactory {

	public static Baustein createProxy(Object object) {
		// @formatter:off
		return (Baustein) Proxy.newProxyInstance(
			BausteinProxyFactory.class.getClassLoader(),
			new Class[] { Baustein.class },
			new BausteinCommonMethodsInvocationHandler(object)
		);
		// @formatter:on
	}

	@RequiredArgsConstructor
	@Slf4j
	private static class BausteinCommonMethodsInvocationHandler implements InvocationHandler {

		private final Object target;

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Object result = null;
			if (method.getName().equals("trittDrauf")) {
				this.trittDrauf();
			} else {
				Method resolvedMethod = this.findMethodOnTarget(method).orElse(method);
				result = resolvedMethod.invoke(target, args);
			}
			return result;
		}

		private void trittDrauf() {
			if (HolzBaustein.class.isAssignableFrom(target.getClass())) {
				log.info("autsch!");
			} else if (LegoStein.class.isAssignableFrom(target.getClass())) {
				StringBuilder sb = new StringBuilder();
				int anzahlNoppen = ((LegoStein) target).getAnzahlNoppen();
				for (int i = 0; i < anzahlNoppen; i++) {
					sb.append("au");
				}
				log.info(sb.toString());
			}
		}

		private Optional<Method> findMethodOnTarget(Method method) {
			Method resultMethod = null;
			try {
				resultMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());
			} catch (NoSuchMethodException | SecurityException ex) {
			}
			return Optional.ofNullable(resultMethod);
		}

	}
}
