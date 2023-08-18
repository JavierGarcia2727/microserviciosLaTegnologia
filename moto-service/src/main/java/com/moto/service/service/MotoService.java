package com.moto.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moto.service.entity.Moto;
import com.moto.service.repositorio.MotoRepository;

@Service
public class MotoService {

	@Autowired
	private MotoRepository motoRepositori;

	public List<Moto> getAll() {

		return motoRepositori.findAll();

	}

	public Moto getMotoById(int id) {

		return motoRepositori.findById(id).orElse(null);

	}

	public Moto save(Moto moto) {

		Moto motonuevo = motoRepositori.save(moto);
		return motonuevo;
	}

	public List<Moto> byUsuarioId(int usuarioId) {

		return motoRepositori.findByUsuarioId(usuarioId);
	}

}
