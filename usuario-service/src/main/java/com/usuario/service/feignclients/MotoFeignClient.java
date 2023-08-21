package com.usuario.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



import com.usuario.service.modelos.Moto;
//@FeignClient(name = "moto-service",url ="http://localhost:8002/carro")// antes de tener eureka 
@FeignClient(name = "moto-service")
//@RequestMapping("/moto")
public interface MotoFeignClient {
	
	
		@PostMapping("/save")// en le video no pone la ruta pero kisa sporq es @RequesBody
		public Moto save(@RequestBody Moto moto);
			
		
	
		@GetMapping("/usuario/{usuarioId}")
		public List<Moto> getMoto(@PathVariable("usuarioId") int usuarioId);
			

}
