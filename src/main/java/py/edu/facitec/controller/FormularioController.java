package py.edu.facitec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import py.edu.facitec.model.Suscrito;
import py.edu.facitec.repository.SuscritoRepository;

	@Controller
	public class FormularioController {
	
		@RequestMapping(value = "/form2",method = RequestMethod.GET)
		public String formulario() {
			System.out.println("Ingreso a formulario");
			//modelAnd.setViewName("form Suscrito");
			
			//def    ap.p                  ap, p
			//static/archivo/suscrito/form.html
			return "suscrito2/form2";
		}
	
	}