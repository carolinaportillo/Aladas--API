package ar.com.ada.api.aladas.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.aladas.entities.Reserva;
import ar.com.ada.api.aladas.entities.Usuario;
import ar.com.ada.api.aladas.entities.Usuario.TipoUsuarioEnum;
import ar.com.ada.api.aladas.models.request.EstadoReservaRequest;
import ar.com.ada.api.aladas.models.request.InfoReservaNueva;
import ar.com.ada.api.aladas.models.response.GenericResponse;
import ar.com.ada.api.aladas.models.response.ReservaResponse;
import ar.com.ada.api.aladas.services.ReservaService;
import ar.com.ada.api.aladas.services.UsuarioService;

@RestController
public class ReservaController {
    @Autowired
    ReservaService service;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/api/reservas")
    public ResponseEntity<ReservaResponse> generarReserva(@RequestBody InfoReservaNueva infoReserva){
        ReservaResponse respuesta = new ReservaResponse();

        //obtengo a quien esta autenticado del otro lado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //de lo que esta autenticado obtengo su username
        String username = authentication.getName();

        Usuario usuario= usuarioService.buscarPorUsername(username);

        Reserva reserva = service.generarReserva(infoReserva.vueloId, usuario.getPasajero().getPasajeroId());
       
        respuesta.isOk=true;
        respuesta.reservaId=reserva.getReservaId();
        respuesta.fechaEmision=reserva.getFechaEmision();
        respuesta.fechaVencimiento=reserva.getFechaVencimiento();
        respuesta.message="Reserva creada con éxito";

        return ResponseEntity.ok(respuesta);
    }

   

    @PutMapping("api/reservas/{id}/estados")
    public ResponseEntity<GenericResponse> eliminarReserva(@PathVariable Integer id, 
    @RequestBody EstadoReservaRequest estadoReserva){

        GenericResponse respuesta =  new GenericResponse();

        Reserva reserva = service.buscarPorId(id);
        reserva.setEstadoReservaId(estadoReserva.estado);
        service.actualizar(reserva);

        respuesta.isOk=true;
        respuesta.message="Estado reserva actualizado correctamente";

        return ResponseEntity.ok(respuesta);
    }
}
