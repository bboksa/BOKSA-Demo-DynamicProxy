package de.boksa.demo.dynamicproxy.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Optional;

import de.boksa.demo.dynamicproxy.external.model.HolzBaustein;
import de.boksa.demo.dynamicproxy.external.model.LegoStein;
import de.boksa.demo.dynamicproxy.model.Baustein;
import javassist.Modifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

public class BausteinProxyFactory {

	public static Baustein createProxy(Object object) {
		// @formatter:off
		return (Baustein) Proxy.newProxyInstance(
			BausteinProxyFactory.class.getClassLoader(),
			new Class[] { Baustein.class },
			new BausteinInvocationHandler<Object>(object, BausteinAdditionalMethods.class)
		);
		// @formatter:on
	}

	@RequiredArgsConstructor
	private static class BausteinInvocationHandler<T> implements InvocationHandler {

		private final Object target;
		private final Class<?> dispatcherClass;
		
		@Override
		public Object invoke(Object proxy, Method methodOnProxy, Object[] args) throws Throwable {
			Object result = null;

			// @formatter:off
			Optional<Method> dispatchedMethod = Arrays.asList(dispatcherClass.getDeclaredMethods()).stream()
				.filter(method -> Modifier.isStatic(method.getModifiers()))
				.filter(method -> methodOnProxy.getName().equals(method.getName()))
				.findFirst();
			// @formatter:on
			
			if (dispatchedMethod.isPresent()) {
				result = dispatchedMethod.get().invoke(null, this.target);
			} else {
				Optional<Method> methodOnTarget = BausteinInvocationHandler.findMethodOnObject(target,
						methodOnProxy);
				Method resolvedMethod = methodOnTarget.orElse(methodOnProxy);
				result = resolvedMethod.invoke(target, args);
			}
			return result;
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

	@Slf4j
	private static final class BausteinAdditionalMethods {

		@SuppressWarnings("unused")
		public static Class<?> typeOf(Object target) {
			return target.getClass();
		}

		@SuppressWarnings("unused")
		public static Void trittDrauf(Object target) {
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

			return null;
		}
	}

}
