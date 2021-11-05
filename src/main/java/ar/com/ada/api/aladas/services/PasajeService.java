package ar.com.ada.api.aladas.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.aladas.entities.Pasaje;
import ar.com.ada.api.aladas.entities.Reserva;
import ar.com.ada.api.aladas.entities.Vuelo;
import ar.com.ada.api.aladas.entities.Reserva.EstadoReservaEnum;
import ar.com.ada.api.aladas.repos.PasajeRepository;

@Service
public class PasajeService {
    @Autowired
    PasajeRepository repo;

    @Autowired
    ReservaService reservaService;

    @Autowired
    VueloService vueloService;

    public Pasaje emitirPasaje(Integer reservaId) {
        Pasaje pasaje = new Pasaje();
        pasaje.setFechaEmision(new Date());
        
        Reserva reserva = reservaService.buscarPorId(reservaId);
        reserva.setEstadoReservaId(EstadoReservaEnum.EMITIDA);
        reserva.asociarPasaje(pasaje);
        Integer capacidad= reserva.getVuelo().getCapacidad();
        reserva.getVuelo().setCapacidad(capacidad-1);
        

        Vuelo vuelo= reserva.getVuelo();
        //vuelo.agregarReserva(reserva);

        vueloService.actualizar(vuelo);


        return pasaje;
    }

    public List<Pasaje> obtenerPasajes() {
        return repo.findAll();
    }


}
