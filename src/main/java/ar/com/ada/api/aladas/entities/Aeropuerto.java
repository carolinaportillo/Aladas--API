package ar.com.ada.api.aladas.entities;

import javax.persistence.*;

@Entity
@Table( name = "aeropuerto")
public class Aeropuerto {

    @Id
    @Column(name = "aeropuerto_id")
    private Integer aeropuertoId;

    @Column(name = "nombre_aeropuerto")
    private String nombre;

    @Column(name = "codigo_iata")
    private String codigoIata;

    public Integer getAeropuertoId() {
        return aeropuertoId;
    }

    public void setAeropuertoId(Integer aeropuertoId) {
        this.aeropuertoId = aeropuertoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoIata() {
        return codigoIata;
    }

    public void setCodigoIata(String codigoIata) {
        this.codigoIata = codigoIata;
    }

    
    
}
