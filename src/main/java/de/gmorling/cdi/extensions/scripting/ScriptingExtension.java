/**
 *  Copyright 2010 Gunnar Morling (http://www.gunnarmorling.de/)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package de.gmorling.cdi.extensions.scripting;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.enterprise.util.AnnotationLiteral;
import javax.script.ScriptEngineManager;

public class ScriptingExtension implements Extension {

	void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {

		AnnotatedType<ScriptEngineManager> at = bm
				.createAnnotatedType(ScriptEngineManager.class);


		final InjectionTarget<ScriptEngineManager> it = bm
				.createInjectionTarget(at);

		abd.addBean(new Bean<ScriptEngineManager>() {

			@Override
			public Class<?> getBeanClass() {

				return ScriptEngineManager.class;
			}

			@Override
			public Set<InjectionPoint> getInjectionPoints() {

				return it.getInjectionPoints();
			}

			@Override
			public String getName() {

				return "scriptEngineManager";

			}

			@SuppressWarnings("serial")
			@Override
			public Set<Annotation> getQualifiers() {

				Set<Annotation> qualifiers = new HashSet<Annotation>();

				qualifiers.add(new AnnotationLiteral<Default>() {});
				qualifiers.add(new AnnotationLiteral<Any>() {});

				return qualifiers;
			}

			@Override
			public Class<? extends Annotation> getScope() {

				return ApplicationScoped.class;

			}

			@Override
			public Set<Class<? extends Annotation>> getStereotypes() {

				return Collections.emptySet();
			}

			@Override
			public Set<Type> getTypes() {

				Set<Type> types = new HashSet<Type>();

				types.add(ScriptEngineManager.class);
				types.add(Object.class);

				return types;

			}

			@Override
			public boolean isAlternative() {

				return false;
			}

			@Override
			public boolean isNullable() {

				return false;
			}

			@Override
			public ScriptEngineManager create(
					CreationalContext<ScriptEngineManager> ctx) {

				return it.produce(ctx);
			}

			@Override
			public void destroy(ScriptEngineManager instance, CreationalContext<ScriptEngineManager> ctx) {

				it.dispose(instance);
				ctx.release();
			}

		});

	}
}
