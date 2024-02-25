package com.example.pruebaCrud;

import com.example.pruebaCrud.exceptions.NaveNotFoundException;
import com.example.pruebaCrud.model.NaveEspacial;
import com.example.pruebaCrud.repository.NaveEspacialRepository;
import com.example.pruebaCrud.service.NaveEspacialService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

@SpringBootTest
class PruebaCrudApplicationTests {

	@Autowired
	private NaveEspacialService naveEspacialService;

	@MockBean
	private NaveEspacialRepository naveEspacialRepository;

	@Test
	void obtenerNaveEspacialIdTestOk() {

		Long validId = 1L;
		NaveEspacial mockNave = new NaveEspacial(validId, "Nave Test", "Serie Test", "Película Test");
		when(naveEspacialRepository.findById(validId)).thenReturn(Optional.of(mockNave));

		Optional<NaveEspacial> result = naveEspacialService.obtenerNaveEspacialId(validId);

		assertTrue(result.isPresent());
		assertEquals(mockNave, result.get());
	}

	@Test
	void obtenerNaveEspacialIdThrowsIllegalArgumentException() {

		Long invalidId = -1L;

		assertThrows(IllegalArgumentException.class,
				() -> naveEspacialService.obtenerNaveEspacialId(invalidId));
	}

	@Test
	void obtenerNaveEspacialIdThrowsNaveNotFoundException() {

		Long nonExistentId = 99L;
		when(naveEspacialRepository.findById(nonExistentId)).thenReturn(Optional.empty());

		assertThrows(NaveNotFoundException.class,
				() -> naveEspacialService.obtenerNaveEspacialId(nonExistentId));
	}
}
