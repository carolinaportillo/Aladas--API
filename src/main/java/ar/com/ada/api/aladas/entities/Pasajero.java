package ar.com.ada.api.aladas.entities;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "pasajero")
public class Pasajero extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pasajero_id")
    private Integer pasajeroId;

    @OneToMany(mappedBy = "pasajero", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<Reserva> reservas = new ArrayList<>();

    public Integer getPasajeroId(){
        return pasajeroId;
    }

    public void setPasajeroId(Integer pasajeroId){
        this.pasajeroId = pasajeroId;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    //RB con reserva
    public void agregarReserva(Reserva reserva){
        this.reservas.add(reserva);
        reserva.setPasajero(this);
    }

    
    
}
