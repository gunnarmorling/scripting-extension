# What is it?

This project provides a CDI ("Contexts and Dependency Injection for the Java<sup>TM</sup>
EE platform", defined by [JSR 299](http://jcp.org/en/jsr/detail?id=299)) portable
extension, which allows to retrieve [JSR 223](http://jcp.org/en/jsr/detail?id=223)
("Scripting for the Java<sup>TM</sup> Platform") script engines via dependency injection.

Just annotate any injection points of type `javax.script.ScriptEngine` with one of the
qualifier annotations `@Language`, `@Extension` or `@MimeType`. The following shows an
example of a JavaScript engine (for example the [Rhino](http://www.mozilla.org/rhino/)
engine shipping with Java 6) being injected into some managed bean, where it can be used
for arbitrary script evaluations:

	@RequestScoped
	public class MyBean {

		@Inject 
		@Language("javascript") 
		private ScriptEngine jsEngine;

		// ...

		public void foo() throws ScriptException {

			assert 42.0d == (Double)jsEngine.eval("2 * 21");
			// ...
		}

	}


