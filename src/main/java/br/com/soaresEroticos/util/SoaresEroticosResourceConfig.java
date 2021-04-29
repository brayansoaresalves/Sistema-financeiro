package br.com.soaresEroticos.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

//http://localhost:8080/SoaresEroticos/rest
@ApplicationPath("rest")
public class SoaresEroticosResourceConfig extends ResourceConfig {
	public SoaresEroticosResourceConfig(){
		packages("br.com.soaresEroticos.service");
	}

}
