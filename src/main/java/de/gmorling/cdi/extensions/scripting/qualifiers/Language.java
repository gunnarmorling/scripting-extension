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

package de.gmorling.cdi.extensions.scripting.qualifiers;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import javax.script.ScriptEngineManager;

/**
 * Specifies the language name of a <a
 * href="http://jcp.org/en/jsr/detail?id=223">JSR 223</a>
 * ("Scripting for the Java<sup>TM</sup> Platform") scripting engine to be
 * injected.
 * 
 * @author Gunnar Morling
 */
@Qualifier
@Retention(RUNTIME)
@Target({ TYPE, METHOD, FIELD, PARAMETER })
@Documented
public @interface Language {

	/**
	 * The language name of a <a href="http://jcp.org/en/jsr/detail?id=223">JSR
	 * 223</a> ("Scripting for the Java<sup>TM</sup> Platform") scripting engine
	 * as expected by {@link ScriptEngineManager#getEngineByName(String)}.
	 * 
	 * @return A JSR 223 scripting language name.
	 */
	@Nonbinding
	String value();

}
