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

import static org.junit.Assert.*;

import java.io.File;

import javax.inject.Inject;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.gmorling.cdi.extensions.scripting.qualifiers.Extension;
import de.gmorling.cdi.extensions.scripting.qualifiers.Language;
import de.gmorling.cdi.extensions.scripting.qualifiers.MimeType;

/**
 * Test for {@link ScriptException}.
 * 
 * @author Gunnar Morling
 * 
 */
@RunWith(Arquillian.class)
public class ScriptingExtensionTest {

	@Inject
	@Language("javascript")
	private ScriptEngine engineInjectedByName;
	
	@Inject
	@Extension("js")
	private ScriptEngine engineInjectedByExtension;
	
	@Inject
	@MimeType("application/javascript")
	private ScriptEngine engineInjectedByMimeType;

	@Deployment
	public static JavaArchive createTestArchive() throws Exception {
		return 
			ShrinkWrap.create("test.jar", JavaArchive.class)
				.addManifestResource(new File("src/main/resources/META-INF/services/javax.enterprise.inject.spi.Extension"))
				.addPackage(ScriptingExtension.class.getPackage())
				.addPackage(Language.class.getPackage());
	}

	@Test
	public void engineInjectedByName() throws ScriptException {
		assertNotNull(engineInjectedByName);
		assertEquals(8.0d, engineInjectedByName.eval("2 * 4"));
	}
	
	@Test
	public void engineInjectedByExtension() throws ScriptException {
		assertNotNull(engineInjectedByExtension);
		assertEquals(8.0d, engineInjectedByExtension.eval("2 * 4"));
	}
	
	@Test
	public void engineInjectedByMimeType() throws ScriptException {
		assertNotNull(engineInjectedByMimeType);
		assertEquals(8.0d, engineInjectedByMimeType.eval("2 * 4"));
	}

}
