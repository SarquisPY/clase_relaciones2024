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

import py.edu.facitec.model.Post;
import py.edu.facitec.repository.PostRepository;

//Aplicar la arquitectura rest
//Representational State TRansfer
//Solicitudes sin alm. de estado.
@RestController
@RequestMapping("/api")
//api afecta a todos los metodos de la clase
public class PostController {

	//Autowired de Post Repository
	@Autowired
	private PostRepository postRepository;
	
	//api 
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getPost(){

		List<Post> posts = new ArrayList<>();
		//Buscar todos
		posts = postRepository.findAll();
		
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
		}
	//Post se utiliza para crear un elemento
	@PostMapping("/post")
	public ResponseEntity<Post>
			//JSon --> JAVA 
	guardarPost(@RequestBody Post post){
		postRepository.save(post);
								//devolver el objeto creado
								// id nuevo
		return new ResponseEntity<Post>(post,HttpStatus.OK);
		}
	//api/post/{id}
	// buscar un post por id 

	@GetMapping("/post/{codigo}")
	public ResponseEntity<Post>
							// Recibir por parametro el valor
	getOnePost(@PathVariable Long codigo){
		Optional<Post>post=
		postRepository.findById(codigo);
					//comparar si se encontro
		if(post.isPresent()) {
			return new ResponseEntity<Post>
			(post.get(), HttpStatus.OK);
		}else { 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		
		}
		//api/post/codigo
		//eliminar un post por codigo Delete
		@DeleteMapping("/post/{codigo}")
		public ResponseEntity<Post>	eliminarOnePost(@PathVariable Long codigo){
			Optional<Post>post=
			postRepository.findById(codigo);
						//comparar si se encontro
			if(post.isPresent()) {
						//elimina un post
				postRepository.deleteById(codigo);
				return new ResponseEntity<>
				(HttpStatus.NO_CONTENT);
				
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		
	}

}
