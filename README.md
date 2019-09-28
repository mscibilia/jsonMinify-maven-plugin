A simple maven plugin that minifies json files in source directory and writes them to the output directory.

Example usage in pom.xml

	<plugin>
		<groupId>com.ms</groupId>
		<artifactId>jsonMinify-maven-plugin</artifactId>
		<version>0.0.1-SNAPSHOT</version>
		<configuration>
			<srcDirectory>${project.basedir}\src\main\resources</srcDirectory>
			<outputDirectory>${project.basedir}\target\project\WEB-INF\classes</outputDirectory>
		</configuration>
		<executions>
			<execution>
				<goals>
					<goal>minify</goal>
				</goals>
			</execution>
		</executions>
	</plugin>