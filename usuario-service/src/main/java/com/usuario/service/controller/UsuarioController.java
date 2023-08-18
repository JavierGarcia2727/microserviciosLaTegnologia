package com.usuario.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entity.Usuario;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/")
	public ResponseEntity<List<Usuario>> ListarUsuario() {

		List<Usuario> ListaUsuario = usuarioService.getAll();

		if (ListaUsuario.isEmpty()) { // isEmpty siginifica si esta vacia no tiene contenido
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(ListaUsuario);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id) {
		System.out.println("$$$$$$$$$$$$                 cf  $$$$$$$$$$$$$$$$$$$$$$$ ");
		Usuario usuario = usuarioService.getUsuarioById(id);

		if (usuario == null) {

			return ResponseEntity.notFound().build();// notFound se usa cuando no se encuentra

		}

		return ResponseEntity.ok(usuario);
	}

	@PostMapping("/save")
	public ResponseEntity<Usuario> GuardarUsuario(@RequestBody Usuario usuario) {

		Usuario nuevousuario = usuarioService.save(usuario);

		return ResponseEntity.ok(nuevousuario);

	}

	/// Metodos Con REstTEmplate
	@GetMapping("/carros/{usuarioId}")
	public ResponseEntity<List<Carro>> ListarCarros(@PathVariable("usuarioId") int usuarioId) {

		Usuario usuario = usuarioService.getUsuarioById(usuarioId);

		if (usuario == null) {

			ResponseEntity.notFound().build();
		}

		List<Carro> listadoCarros = usuarioService.getCarro(usuarioId);

		return ResponseEntity.ok(listadoCarros);

	}

	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<List<Moto>> ListarMotos(@PathVariable("usuarioId") int usuarioId) {

		Usuario usuario = usuarioService.getUsuarioById(usuarioId);

		if (usuario == null) {

			ResponseEntity.notFound().build();
		}

		List<Moto> listadoMotos = usuarioService.getMoto(usuarioId);

		return ResponseEntity.ok(listadoMotos);

	}

	/// metodo con Feign

	@PostMapping("/guardarCarro/{usuarioId}")
	public ResponseEntity<Carro> guiardarCarro(@PathVariable("usuarioId") int usuarioId, @RequestBody Carro carro) {
		
	
		
		Carro nuevoCarro = usuarioService.saveCarro(usuarioId, carro);

		return ResponseEntity.ok(nuevoCarro);
	}
	
	
	@PostMapping("/guardarMoto/{usuarioId}")
	public ResponseEntity<Moto> guiardarMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody Moto moto) {
		Moto nuevaMoto = usuarioService.saveMoto(usuarioId, moto);

		return ResponseEntity.ok(nuevaMoto);
	}
	

@GetMapping("/todos/{usuarioId}")
	public ResponseEntity<Map<String, Object>> ListarTodosLosVehiculos(@PathVariable("usuarioId") int usuarioId){
	System.out.println("________________________ x 1____________________ ");
		Map<String, Object> resultado=usuarioService.getUsuarioAndVehiculos(usuarioId);
		return ResponseEntity.ok(resultado);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
