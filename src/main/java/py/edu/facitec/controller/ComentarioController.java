package py.edu.facitec.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import py.edu.facitec.model.Comentario;
import py.edu.facitec.repository.ComentarioRepository;

//Aplicar la arquitectura rest
//Representational State TRansfer
//Solicitudes sin alm. de estado.
@RestController
@RequestMapping("/api")
//api afecta a todos los metodos de la clase
public class ComentarioController {

	//Autowired de Comentario Repository
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	//api 
	@GetMapping("/comentarios")
	public ResponseEntity<List<Comentario>> getComentario(){

		List<Comentario> comentarios = new ArrayList<>();
		//Buscar todos
		comentarios = comentarioRepository.findAll();
		
		return new ResponseEntity<List<Comentario>>(comentarios, HttpStatus.OK);
		}
	//Post se utiliza para crear un elemento
	@PostMapping("/comentario")
	public ResponseEntity<Comentario>
			//JSon --> JAVA 
	guardarComentario(@RequestBody Comentario comentario){
		comentarioRepository.save(comentario);
								//devolver el objeto creado
								// id nuevo
		return new ResponseEntity<Comentario>(comentario,HttpStatus.OK);
		}
	//api/comentario/{id}
	// buscar un comentario por id 

	@GetMapping("/comentario/{codigo}")
	public ResponseEntity<Comentario>
							// Recibir por parametro el valor
	getOneComentario(@PathVariable Long codigo){
		Optional<Comentario>comentario=
		comentarioRepository.findById(codigo);
					//comparar si se encontro
		if(comentario.isPresent()) {
			return new ResponseEntity<Comentario>
			(comentario.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		
		}
		//api/comentario/codigo
		//eliminar un comentario por codigo Delete
		@DeleteMapping("/comentario/{codigo}")
		public ResponseEntity<Comentario>	eliminarOneComentario(@PathVariable Long codigo){
			Optional<Comentario>comentario=
			comentarioRepository.findById(codigo);
						//comparar si se encontro
			if(comentario.isPresent()) {
						//elimina un comentario
				comentarioRepository.deleteById(codigo);
				return new ResponseEntity<>
				(HttpStatus.NO_CONTENT);
				
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		
	}

}
