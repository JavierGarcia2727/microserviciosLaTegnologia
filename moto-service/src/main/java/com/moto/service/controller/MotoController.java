package com.moto.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.service.entity.Moto;
import com.moto.service.service.MotoService;


@RestController
@RequestMapping("/moto")
public class MotoController {

	@Autowired
	private MotoService motoService;
	
	
	@GetMapping("/")
	public ResponseEntity<List<Moto>> ListarMoto() {

		List<Moto> ListaMoto = motoService.getAll();

		if (ListaMoto.isEmpty()) { // isEmpty siginifica si esta vacia no tiene contenido
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(ListaMoto);

	}

	@GetMapping("/vermotos/{id}")
	public ResponseEntity<Moto> obtenerMoto(@PathVariable("id") int id) {

		Moto Moto = motoService.getMotoById(id);

		if (Moto == null) {

			return ResponseEntity.notFound().build();// notFound se usa cuando no se encuentra

		}

		return ResponseEntity.ok(Moto);
	}

	@PostMapping("/save")
	public ResponseEntity<Moto> GuardarMoto(@RequestBody Moto moto) {

		Moto nuevamoto = motoService.save(moto);

		return ResponseEntity.ok(nuevamoto);

	}

	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Moto>> listaMotosPorUsuarioId(@PathVariable("usuarioId") int usuarioId) {
		List<Moto> motos = motoService.byUsuarioId(usuarioId);
		
		System.out.println("#################### MOTO si ##################################  1  "+motos.isEmpty());

		if (motos.isEmpty()) { // isEmpty siginifica si esta vacia no tiene contenido
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(motos);

	}
}
