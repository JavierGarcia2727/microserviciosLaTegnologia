package com.usuario.service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entity.Usuario;
import com.usuario.service.feignclients.CarroFeignClient;
import com.usuario.service.feignclients.MotoFeignClient;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.repositorio.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CarroFeignClient carroFeignClient;

	@Autowired
	private MotoFeignClient motoFeignClient;

	////////////////////////////////////////

	public List<Usuario> getAll() {

		return usuarioRepository.findAll();

	}

	public Usuario getUsuarioById(int id) {

		return usuarioRepository.findById(id).orElse(null);

	}

	public Usuario save(Usuario usuario) {

		Usuario usuarionuevo = usuarioRepository.save(usuario);
		return usuarionuevo;
	}

	// Metodos de RestTemplate
	public List<Carro> getCarro(int usuarioId) {

		List<Carro> carros = restTemplate.getForObject("http://localhost:8002/carro/usuario/" + usuarioId, List.class);

		return carros;

	}

	public List<Moto> getMoto(int usuarioId) {

		List<Moto> motos = restTemplate.getForObject("http://localhost:8003/moto/usuario/" + usuarioId, List.class);

		return motos;

	}

	// Metodos de Feign

	public Carro saveCarro(int usuarioId, Carro carro) {
		carro.setUsuarioId(usuarioId);
		Carro nuevoCarro = carroFeignClient.save(carro);

		return nuevoCarro;
	}

	public Moto saveMoto(int usuarioId, Moto moto) {

		moto.setUsuarioId(usuarioId);
		Moto nuevaMoto = motoFeignClient.save(moto);

		return nuevaMoto;
	}

	public Map<String, Object> getUsuarioAndVehiculos(int usuarioId) {
		System.out.println("$$$$$$$$$ 2000  $$$$$$$$   xx  $$$$$$$$$$$$$$$$$$ "+ usuarioId);
		
		Map<String, Object> resultado = new HashMap<>();

		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

		if (usuario == null) {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 1 d "+usuarioId);
			resultado.put("mensaje", "El usuario no existe");
		} else {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 2 d "+usuarioId);

			resultado.put("Usuario", usuario);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 2.1 ");
		
			
			List<Carro> carros = carroFeignClient.VerCarros(usuarioId);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 2.2 ");
			resultado.put("Carros", carros);
			
			
			if (carros.isEmpty()) {
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 3 ");
				
				resultado.put("Carros", "El usuario no tiene Carros");
			} else {
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 4 ");
				resultado.put("Carros", carros);
			}

			List<Moto> Motos = motoFeignClient.getMoto(usuarioId);
			
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! "+Motos.isEmpty());
			if (Motos.isEmpty()) {
			
				
				resultado.put("Motos", "El usuario no tiene Motos");
			} else {
				resultado.put("Motos", Motos);
			}
		}
		resultado.put("Resultado", "Muy dificil ");
		return resultado;

	}

}
