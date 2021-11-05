package ar.com.ada.api.aladas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.aladas.entities.Vuelo;
import ar.com.ada.api.aladas.models.request.EstadoVueloRequest;
import ar.com.ada.api.aladas.models.response.GenericResponse;
import ar.com.ada.api.aladas.services.AeropuertoService;
import ar.com.ada.api.aladas.services.VueloService;
import ar.com.ada.api.aladas.services.VueloService.ValidacionVueloDataEnum;


@RestController
public class VueloController {

    @Autowired
    private VueloService service;

    @Autowired
    private AeropuertoService aeropuertoService;


    @PostMapping("/api/vuelos")
    public ResponseEntity<GenericResponse> postCrearVuelo(@RequestBody Vuelo vuelo){

        GenericResponse respuesta = new GenericResponse();

        ValidacionVueloDataEnum resultadoValidacion = service.validar(vuelo);
        
        if(resultadoValidacion == ValidacionVueloDataEnum.OK){

            service.crear(vuelo);

            respuesta.isOk = true;
            respuesta.id = vuelo.getVueloId();
            respuesta.message = "Vuelo creado correctamente";

            return ResponseEntity.ok(respuesta);
        }else{

            respuesta.isOk = false;
            respuesta.message = "ERROR(" + resultadoValidacion.toString() + ")";

            return ResponseEntity.badRequest().body(respuesta);
        }


    }

    //PUT-> cambia el estado de un vuelo que puede pasar de creado a "abierto" para incializar las reservas y/o venta de pasajes
    @PutMapping("/api/vuelos/{id}/estados")
    public ResponseEntity<GenericResponse> putActualizarEstadoVuelo(@PathVariable Integer id, @RequestBody EstadoVueloRequest estadoVuelo){
        
        GenericResponse respuesta = new GenericResponse();
        respuesta.isOk = true;
        respuesta.message = "Estado de vuelo actualizado correctamente";

        Vuelo vuelo = service.buscarVueloPorId(id);
        vuelo.setEstadoVueloId(estadoVuelo.estado);
        service.actualizar(vuelo);
        
        return ResponseEntity.ok(respuesta);
    }


    @GetMapping("/api/vuelos/abiertos")
    public List<Vuelo> getVuelosAbiertos(){

        return service.obtenerVuelosAbiertos();
    }

}
