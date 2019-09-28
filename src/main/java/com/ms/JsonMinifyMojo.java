package com.ms;

import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 */
@Mojo(name = "minify", defaultPhase = LifecyclePhase.PACKAGE)
public class JsonMinifyMojo extends AbstractMojo {
	@Parameter(defaultValue = "${project.build.outputDirectory}")
	private String outputDirectory;

	@Parameter(defaultValue = "${project.build.sourceDirectory}")
	private String srcDirectory;

	public void execute() throws MojoExecutionException {
		try {
			JsonMinifier.minifyJsonFilesInDir(srcDirectory, outputDirectory);
		} catch (IOException e) {
			throw new MojoExecutionException(e.getMessage());
		}
	}

	public String getOutputDirectory() {
		return outputDirectory;
	}

	public String getSrcDirectory() {
		return srcDirectory;
	}
}