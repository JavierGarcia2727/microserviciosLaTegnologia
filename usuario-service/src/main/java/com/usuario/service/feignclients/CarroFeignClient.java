package com.usuario.service.feignclients;



import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.usuario.service.modelos.Carro;

//@FeignClient(name = "carro-service",url ="http://localhost:8002/carro")// antes de tener eureka 
@FeignClient(name = "carro-service")
//@RequestMapping("/carro")
public interface CarroFeignClient {
	
	@PostMapping("/save")// en le video no pone la ruta pero kisa sporq es @RequesBody
	public Carro save(@RequestBody Carro carro);
		
		

	@GetMapping("/usuario/{usuarioId}")
	public List<Carro> VerCarros(@PathVariable("usuarioId") int usuarioId);
	
}
