package com.juan.api.biblioteca.controllers;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.juan.api.biblioteca.entities.Libro;
import com.juan.api.biblioteca.services.ILibroService;

import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@RestController
@RequestMapping(value = "/api/v1")
public class LibroController {
	
//	@Value("${user.role}")
//	private String user;

	@Autowired
	ILibroService libroService;

	private final WebClient.Builder webClientBuilder;

	public LibroController(WebClient.Builder webClientBuilder) {
		this.webClientBuilder = webClientBuilder;
	}

	HttpClient httpClient = HttpClient.create()
			.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
			.option(ChannelOption.SO_KEEPALIVE, true)
			.option(EpollChannelOption.TCP_KEEPIDLE, 300)
			.option(EpollChannelOption.TCP_KEEPINTVL, 60)
			.responseTimeout(Duration.ofMillis(5000))
			.doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
					.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

	@GetMapping(value = "/libros")
	public ResponseEntity<?> getLibros() {
		List<Libro> libros = libroService.findAll();
		if (libros.isEmpty()) {
			return new ResponseEntity("No existen libros registrados", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Libro>>(libros, HttpStatus.OK);
	}

	@GetMapping(value = "/libros/{id}")
	public Libro getLibro(@PathVariable long id) {
//		return libroService.findById(id);
		return new Libro();
	}

	@PostMapping(value = "/libros")
	public Libro createLibro(@RequestBody Libro libro) {
		return libroService.createLibro(libro);
	}

	@PutMapping(value = "/libros/{id}")
	public Libro updateLibro(@PathVariable long id, @RequestBody Libro libro) {
		return libroService.updateLibro(id, libro);
	}

	@DeleteMapping("/libros/{id}")
	public ResponseEntity<?> deleteLibro(@PathVariable long id) {
		libroService.deleteLibroById(id);
		return new ResponseEntity<String>("body de respuesta", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/usuarioclient/{id}")
	public String pruebaBorradorUsuarioClient(@PathVariable String id) {
		return getUsuarioName(id);
	}

	private String getUsuarioName(String id) {
		WebClient client = webClientBuilder
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.baseUrl("http://biblioteca-users/api/v1/usuario")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultUriVariables(Collections.singletonMap("url", "http://biblioteca-users/api/v1/usuario"))
				.build();
		JsonNode block = client.method(HttpMethod.GET).uri("/" + id).retrieve().bodyToMono(JsonNode.class).block();
		String user = block.get("user").asText();
		return user;
	}
	
}
