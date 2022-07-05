package me.wawwior.core;

import me.wawwior.core.util.ResourceUtil;

import java.io.IOException;
import java.net.URISyntaxException;

public class ResourceUtilTest {
	
	public static void main(String[] args) throws URISyntaxException, IOException {
		
		ResourceUtil util = new ResourceUtil("./extract");
		
		util.copyFromJar("./extract", "");
		
	}
	
}
