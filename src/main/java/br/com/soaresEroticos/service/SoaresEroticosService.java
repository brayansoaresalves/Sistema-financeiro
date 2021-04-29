package br.com.soaresEroticos.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

//http://localhost:8080/SoaresEroticos/rest/soaresEroticos
@Path("soaresEroticos")
public class SoaresEroticosService {
	@GET
	public String exibir(){
		return "Brenda Alves Soares";
	}

}
