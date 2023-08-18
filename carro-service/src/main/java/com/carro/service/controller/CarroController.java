package com.carro.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carro.service.entity.Carro;
import com.carro.service.service.CarroService;


@RestController
@RequestMapping("/carro")
public class CarroController {

	@Autowired
	private CarroService carroService;

	@GetMapping("/")
	public ResponseEntity<List<Carro>> ListarCarro() {

		List<Carro> ListaCarro = carroService.getAll();

		if (ListaCarro.isEmpty()) { // isEmpty siginifica si esta vacia no tiene contenido
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(ListaCarro);

	}

	@GetMapping("/vercarro/{id}")
	public ResponseEntity<Carro> obtenerMoto(@PathVariable("id") int id) {
		Carro Carro = carroService.getCarroById(id);

		if (Carro == null) {

			return ResponseEntity.notFound().build();// notFound se usa cuando no se encuentra

		}
		
		return ResponseEntity.ok(Carro);
	}

	@PostMapping("/save")
	public ResponseEntity<Carro> GuardarMoto(@RequestBody Carro carro) {

		Carro nuevocarro = carroService.save(carro);

		return ResponseEntity.ok(nuevocarro);

	}

	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Carro>> listaCarrosPorUsuarioId(@PathVariable("usuarioId") int usuarioId) {
		
		
		List<Carro> carros = carroService.byUsuarioId(usuarioId);

		if (carros.isEmpty()) { // isEmpty siginifica si esta vacia no tiene contenido
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(carros);

	}

}
