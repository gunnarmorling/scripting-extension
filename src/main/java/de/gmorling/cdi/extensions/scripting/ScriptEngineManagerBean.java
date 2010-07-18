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

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import de.gmorling.cdi.extensions.scripting.qualifiers.Extension;
import de.gmorling.cdi.extensions.scripting.qualifiers.Language;
import de.gmorling.cdi.extensions.scripting.qualifiers.MimeType;

/**
 * A managed bean allowing JSR 223 script engines to be retrieved using CDI.
 * 
 * @author Gunnar Morling
 *
 */
@ApplicationScoped
public class ScriptEngineManagerBean {

	private @Inject
	ScriptEngineManager sem;

	@Produces
	@Language("")
	public ScriptEngine getEngineByName(InjectionPoint injectionPoint) {

		String languageName = injectionPoint.getAnnotated()
				.getAnnotation(Language.class).value();

		ScriptEngine engine;
		
		synchronized (sem) {
			engine = sem.getEngineByName(languageName);
		}
		
		if(engine != null) {
			return engine;
		}
		else {
			throw new IllegalArgumentException("No scripting engine found for language name " + languageName);
		}
		
	}
	
	@Produces
	@Extension("")
	public ScriptEngine getEngineByExtension(InjectionPoint injectionPoint) {

		String extension = injectionPoint.getAnnotated()
				.getAnnotation(Extension.class).value();

		ScriptEngine engine;
		
		synchronized (sem) {
			engine = sem.getEngineByExtension(extension);
		}
		
		if(engine != null) {
			return engine;
		}
		else {
			throw new IllegalArgumentException("No scripting engine found for extension " + extension);
		}
	}
	
	@Produces
	@MimeType("")
	public ScriptEngine getEngineByMimeType(InjectionPoint injectionPoint) {

		String mimeType = injectionPoint.getAnnotated()
				.getAnnotation(MimeType.class).value();

		ScriptEngine engine;
		
		synchronized (sem) {
			engine = sem.getEngineByMimeType(mimeType);
		}
		
		if(engine != null) {
			return engine;
		}
		else {
			throw new IllegalArgumentException("No scripting engine found for MIME type " + mimeType);
		}
	}

}
