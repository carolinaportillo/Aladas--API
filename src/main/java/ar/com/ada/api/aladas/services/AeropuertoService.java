package ar.com.ada.api.aladas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.aladas.entities.Aeropuerto;
import ar.com.ada.api.aladas.repos.AeropuertoRepository;

@Service
public class AeropuertoService {

    @Autowired
    private AeropuertoRepository repo;


    public void crear(Integer aeropuertoId, String nombre, String codigoIATA){
        
        Aeropuerto aeropuerto = new Aeropuerto();
        aeropuerto.setAeropuertoId(aeropuertoId);
        aeropuerto.setNombre(nombre);
        aeropuerto.setCodigoIATA(codigoIATA);
         
        repo.save(aeropuerto);
    }

    public List<Aeropuerto> obtenerTodos(){

       return repo.findAll();
    }

    public Aeropuerto buscarPorCodigoIATA(String codigoIATA){
        return repo.findByCodigoIATA(codigoIATA);
    }


    
    public Aeropuerto buscarAeropuerto(Integer aeropuertoId){
        Optional <Aeropuerto> resultado= repo.findById(aeropuertoId);
        if(resultado.isPresent()){
            return resultado.get();
        }
        else return null;
    }


    public boolean existeAeropuertoId(Integer aeropuertoId){
        if(buscarAeropuerto(aeropuertoId)!=null)
            return true;
        else return false;
    }


    public boolean validarCodigoIATA(Aeropuerto aeropuerto){

        String codigoIATA= aeropuerto.getCodigoIATA();

        if(codigoIATA.length()!=3)
            return false;
        
        for(int i=0; i<codigoIATA.length(); i++){
            char c= codigoIATA.charAt(i);
            if(!(c>='A'&&c<='Z'))
                return false;
        }
        return true;

        //if(Character.isLetter(c) && c==Character.toUpperCase(c))
    }

    
}
