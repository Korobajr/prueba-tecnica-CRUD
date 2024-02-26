package com.example.pruebaCrud.repository;

import com.example.pruebaCrud.model.NaveEspacial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NaveEspacialRepository extends JpaRepository<NaveEspacial, Long> {

    Page<NaveEspacial> findAll(Pageable pageable);
    Optional<NaveEspacial> findById(Long id);
    List<NaveEspacial> findByNombreContaining(String nombre);
    NaveEspacial save(NaveEspacial nuevaNave);
    void deleteById(Long id);

}
