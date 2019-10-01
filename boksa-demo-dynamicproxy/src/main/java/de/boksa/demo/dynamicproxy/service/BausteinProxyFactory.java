package de.boksa.demo.dynamicproxy.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import de.boksa.demo.dynamicproxy.external.model.HolzBaustein;
import de.boksa.demo.dynamicproxy.external.model.LegoStein;
import de.boksa.demo.dynamicproxy.model.Baustein;
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

	@Slf4j
	private static class BausteinCommonMethodsInvocationHandler implements InvocationHandler {

		private final Object target;

		private final Map<String, Supplier<?>> methodDispatcher = new HashMap<>();

		public BausteinCommonMethodsInvocationHandler(Object target) {
			this.target = target;
			
			methodDispatcher.put("trittDrauf", () -> { this.trittDrauf(); return null; });			
			methodDispatcher.put("typeOf", this::typeOf);			
		}

		@Override
		public Object invoke(Object proxy, Method methodOnProxy, Object[] args) throws Throwable {
			Object result = null;

			Optional<Supplier<?>> dispatchedMethod = Optional.ofNullable(this.methodDispatcher.get(methodOnProxy.getName()));

			if (dispatchedMethod.isPresent()) {
				result = dispatchedMethod.get().get();
			} else {			
				Optional<Method> methodOnTarget = BausteinCommonMethodsInvocationHandler.findMethodOnObject(target, methodOnProxy);
				Method resolvedMethod = methodOnTarget.orElse(methodOnProxy);
				result = resolvedMethod.invoke(target, args);
			}
			return result;
		}

		private Class<?> typeOf() {
			return target.getClass();
		}

		public void trittDrauf() {
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

		private static Optional<Method> findMethodOnObject(Object object, Method method) {
			Method resultMethod = null;
			try {
				resultMethod = object.getClass().getMethod(method.getName(), method.getParameterTypes());
			} catch (NoSuchMethodException | SecurityException ex) {
			}
			return Optional.ofNullable(resultMethod);
		}

	}
}
