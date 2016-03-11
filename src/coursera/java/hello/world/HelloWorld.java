package coursera.java.hello.world;

import edu.duke.URLResource;

public class HelloWorld {
	public void runHello () {
		//FileResource resource = new FileResource("hello_unicode.txt");
		URLResource resource = new URLResource("http://www.dukelearntoprogram.com/java/somefile.txt");
		for (String line : resource.words()) {
			System.out.println(line);
		}
	}
}
