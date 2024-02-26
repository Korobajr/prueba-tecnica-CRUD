package com.example.pruebaCrud.service;

import com.example.pruebaCrud.exceptions.NaveNotFoundException;
import com.example.pruebaCrud.model.NaveEspacial;
import com.example.pruebaCrud.repository.NaveEspacialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;


@Service
public class NaveEspacialService {

    @Autowired
    private NaveEspacialRepository naveEspacialRepository;

    public Page<NaveEspacial> obtenerNavesConPaginacion(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return naveEspacialRepository.findAll(pageable);
    }
    @Cacheable("navesCache")
    public Optional<NaveEspacial> obtenerNaveEspacialId(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID de nave no puede ser negativo");
        }
        return Optional.ofNullable(naveEspacialRepository.findById(id)
                .orElseThrow(() -> new NaveNotFoundException("No se encontró la nave con ID: " + id)));
    }

    public List<NaveEspacial> obtenerNavesPorNombreContent(String string) {
        List<NaveEspacial> list = naveEspacialRepository.findByNombreContaining(string);

        if (list.isEmpty()) {
            throw new NaveNotFoundException("No se encontraron naves espaciales con el nombre que contiene: " + string);
        }
        return list;
        }

    public NaveEspacial guardarNuevaNave( NaveEspacial nuevaNave) {
        validarCampos(nuevaNave);
        return naveEspacialRepository.save(nuevaNave);
    }

    public NaveEspacial modificarNaveExistente(Long id, NaveEspacial naveModificada) {

        NaveEspacial naveExistente = naveEspacialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la nave con ID: " + id));

        validarCampos(naveModificada);
        naveExistente.setNombre(naveModificada.getNombre());
        naveExistente.setSerie(naveModificada.getSerie());
        naveExistente.setPelicula(naveModificada.getPelicula());

        return naveEspacialRepository.save(naveExistente);
    }

    public NaveEspacial borrarNavePorId(Long id) {
        NaveEspacial naveEliminada = naveEspacialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la nave con ID: " + id));
        naveEspacialRepository.deleteById(id);
        return naveEliminada;
    }

    private void validarCampos(NaveEspacial nave) {
        if (StringUtils.isEmpty(nave.getNombre()) || (nave.getSerie() == null && nave.getPelicula() == null)) {
            throw new IllegalArgumentException("Los campos han sido rellenados incorrectamente o falta alguno por rellenar");
        }
    }
}

