package ar.com.ada.api.aladas;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.ada.api.aladas.entities.Vuelo;
import ar.com.ada.api.aladas.services.AeropuertoService;
import ar.com.ada.api.aladas.services.VueloService;

@SpringBootTest
class AladasApplicationTests{

    @Autowired
	VueloService vueloService;

    @Autowired
	AeropuertoService aeropuertoService;

    @Test
	void vueloTestPrecioNegativo(){

		Vuelo vueloConPrecioNegativo = new Vuelo();
		vueloConPrecioNegativo.setPrecio(new BigDecimal(-100));
		
		assertFalse(vueloService.validarPrecio(vueloConPrecioNegativo));

	}


	@Test
	void vueloTestPrecioOk(){
		
		Vuelo vueloConPrecioOk = new Vuelo();
		vueloConPrecioOk.setPrecio(new BigDecimal(100));

		assertTrue(vueloService.validarPrecio(vueloConPrecioOk));

	}
}
