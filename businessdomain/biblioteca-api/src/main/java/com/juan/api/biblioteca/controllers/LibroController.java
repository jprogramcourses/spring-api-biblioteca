package com.juan.api.biblioteca.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.juan.api.biblioteca.dto.LibroDto;
import com.juan.api.biblioteca.entities.Libro;
import com.juan.api.biblioteca.services.ILibroService;

import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/libros")
public class LibroController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(LibroController.class);
	
//	@Value("${user.role}")
//	private String user;

	@Autowired
	ILibroService libroService;

//	private final WebClient.Builder webClientBuilder;
//
//	public LibroController(WebClient.Builder webClientBuilder) {
//		this.webClientBuilder = webClientBuilder;
//	}

	HttpClient httpClient = HttpClient.create()
			.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
			.option(ChannelOption.SO_KEEPALIVE, true)
			.option(EpollChannelOption.TCP_KEEPIDLE, 300)
			.option(EpollChannelOption.TCP_KEEPINTVL, 60)
			.responseTimeout(Duration.ofMillis(5000))
			.doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
					.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

	
	@GetMapping
	public ResponseEntity<?> getLibros() {
		List<LibroDto> libros = null;
		try{
			libros = libroService.findAll();
		}catch(Exception ex) {
			LOGGER.info("Error en la recuperación de libros");
			ex.printStackTrace();
		}
		
		if (CollectionUtils.isEmpty(libros)) {
			return new ResponseEntity("No existen libros registrados", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<LibroDto>>(libros, HttpStatus.OK);
	}
	
	@GetMapping(value = "/page/{page}")
	public ResponseEntity<?> getLibros(@PathVariable int page) {
		Pageable pageable = PageRequest.of(page, 5);
		
		Page<LibroDto> libros = null;
		try{
			libros = libroService.findAll(pageable);
		}catch(Exception ex) {
			LOGGER.info("Error en la recuperación de libros");
			ex.printStackTrace();
		}
		
		if (CollectionUtils.isEmpty(libros.getContent())) {
			return new ResponseEntity("No existen libros registrados", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<LibroDto>>(libros, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public Libro getLibro(@PathVariable long id) {
//		return libroService.findById(id);
		return new Libro();
	}

	@PostMapping(value = "/", consumes = {"application/json"})
	public ResponseEntity<?> createLibro(@Valid @RequestBody LibroDto libro, BindingResult result) {
		LOGGER.debug("Creación de libro");
		
		LibroDto libroResponse = new LibroDto();
		Map<String, Object> response = new HashMap<>();
		
		if(libro == null || result.hasErrors()) {
			response.put("message", "El libro que se intenta dar de alta no es válido");
			
			if(result.hasErrors()) {
				Map<String, String> mapFieldErrors = new HashMap<>();
				result.getFieldErrors().stream().forEach(fieldError -> {
					mapFieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
				});
				response.put("errors", mapFieldErrors);
			}
			
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			libroResponse = libroService.createLibro(libro);
			response.put("message", "Libro creado correctamente");
			response.put("libro", libroResponse);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}catch(Exception e) {
			response.put("message", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@PutMapping(value = "/{id}")
	public Libro updateLibro(@PathVariable long id, @Valid @RequestBody Libro libro) {
		return libroService.updateLibro(id, libro);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLibro(@PathVariable long id) {
		libroService.deleteLibroById(id);
		return new ResponseEntity<String>("body de respuesta", HttpStatus.NO_CONTENT);
	}
	
//	@GetMapping(value = "/usuarioclient/{id}")
//	public String pruebaBorradorUsuarioClient(@PathVariable String id) {
//		return getUsuarioName(id);
//	}

//	private String getUsuarioName(String id) {
//		WebClient client = webClientBuilder
//				.clientConnector(new ReactorClientHttpConnector(httpClient))
//				.baseUrl("http://biblioteca-users/api/v1/usuario")
//				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//				.defaultUriVariables(Collections.singletonMap("url", "http://biblioteca-users/api/v1/usuario"))
//				.build();
//		JsonNode block = client.method(HttpMethod.GET).uri("/" + id).retrieve().bodyToMono(JsonNode.class).block();
//		String user = block.get("user").asText();
//		return user;
//	}
	
	@PostMapping(value = "/uploadPortada")
	public ResponseEntity<?> uploadPortada(@RequestParam("filePortada") MultipartFile filePortada, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Libro libro = libroService.findById(id);
		
		if(!filePortada.isEmpty()) {
			String nombrePortada = UUID.randomUUID().toString() + "_" + filePortada.getOriginalFilename();
			Path rutaPortada = Paths.get("C:\\WS_Spring\\ws_spring_angular_2_libros\\fotos_portadas_libros").resolve(nombrePortada).toAbsolutePath();
			
			try {
				if(rutaPortada.toFile().exists()) {
					response.put("message", "El fichero ya existe");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
				}
				Files.copy(filePortada.getInputStream(), rutaPortada);
			} catch (IOException e) {
				e.printStackTrace();
				response.put("message", "Error al subir la imagen");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String portadaAnterior = libro.getPortada();
			if(!StringUtils.isEmpty(portadaAnterior)) {
				Path rutaPortadaAnterior = Paths.get("C:\\\\WS_Spring\\\\ws_spring_angular_2_libros\\\\fotos_portadas_libros").resolve(portadaAnterior).toAbsolutePath();
				File filePortadaAnterior = rutaPortadaAnterior.toFile();
				if(filePortadaAnterior.exists() && filePortadaAnterior.canRead()) {
					filePortadaAnterior.delete();
				}
			}
			
			libro.setPortada(nombrePortada);
			
			libroService.updateLibro(id, libro);
		}
//		response.put("libro", libro);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/upload/portada/{nombrePortada:.+}")
	public ResponseEntity<Resource> verPortada(@PathVariable String nombrePortada){
		
		Path rutaPortada = Paths.get("C:\\WS_Spring\\ws_spring_angular_2_libros\\fotos_portadas_libros").resolve(nombrePortada).toAbsolutePath();
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaPortada.toUri());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar la imagen");
		}
		
		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, header, HttpStatus.OK);
	}
	
	
}
