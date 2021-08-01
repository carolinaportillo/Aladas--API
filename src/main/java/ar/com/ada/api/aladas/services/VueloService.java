package ar.com.ada.api.aladas.services;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.aladas.entities.Aeropuerto;
import ar.com.ada.api.aladas.entities.Vuelo;
import ar.com.ada.api.aladas.repos.VueloRepository;

@Service
public class VueloService {

    @Autowired
    private VueloRepository repo;

    @Autowired
    private AeropuertoService aeropService;

    public void crear(Vuelo vuelo){
        repo.save(vuelo);
    }

    public Vuelo crear(Date fecha, Integer capacidad, String aeropuertoOrigenIATA, String aeropuertoDestinoIATA,
     BigDecimal precio, String codigoMoneda){
     
        Vuelo vuelo = new Vuelo();
        
        vuelo.setFecha(fecha);
        vuelo.setCapacidad(capacidad);

        Aeropuerto aeropuertoOrigen = aeropService.buscarPorCodigoIATA(aeropuertoOrigenIATA);
        Aeropuerto aeropuertoDestino = aeropService.buscarPorCodigoIATA(aeropuertoDestinoIATA);

        vuelo.setAeropuertoOrigen(aeropuertoOrigen.getAeropuertoId());
        vuelo.setAeropuertoDestino(aeropuertoDestino.getAeropuertoId());

        vuelo.setPrecio(precio);
        vuelo.setCodigoMoneda(codigoMoneda);

        repo.save(vuelo);
        
        return vuelo;
    }
    

    public boolean validarPrecio(Vuelo vuelo){

        if(vuelo.getPrecio() == null){

            return false;
        }
        if(vuelo.getPrecio().doubleValue()> 0){

            return true;
        }
        return false;

    }

    
}
