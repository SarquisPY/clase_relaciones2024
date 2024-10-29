package py.edu.facitec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Anotacion@: Prove informacion adicional a una clase, atributo o metodo

//un elemento que atienda peticiones y envie respuesta necesitamos indicar @Controller

//Peticiones ---> request  lo que viene del cliente
//Respuestas ---> response lo que sale del servidor
@Controller
public class HomeController {

	//Pueden existier varios metodos para atender determinada peticiones que seria /...
	//Get --> se visualiza la escritura en el navegador.

	@GetMapping("/home")		//<---seria la peticion request
	public String home() {


		System.out.println("Llegue hasta el controlador");
		
		//static          /archivo/        index       .html
		//es por defecto  ap.propertives  controller   ap.propertive
		return "index";		//<---este seria response
	}
}

