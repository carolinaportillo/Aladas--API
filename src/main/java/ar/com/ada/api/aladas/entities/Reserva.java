package ar.com.ada.api.aladas.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserva_id")
    private Integer reservaId;

    @ManyToOne
    @JsonIgnore//para no tener anidado al buscar vuelos BIEN
    @JoinColumn(name = "vuelo_id", referencedColumnName = "vuelo_id")
    private Vuelo vuelo;

    @ManyToOne
    @JsonIgnore//sino me va a imprimir todo lo que lleva pasajero y adentro tiene una lista reservas
    @JoinColumn(name = "pasajero_id", referencedColumnName = "pasajero_id")
    private Pasajero pasajero;

    //se establece la relacion entre ambos objetos (reserva y pasaje)
    //nombre del atributo que hace ref a la tabla
    @JsonIgnore
    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pasaje pasaje;
    

    @Column(name = "estado_reserva_id")
    private Integer estadoReservaId;

    @Column(name = "fecha_emision")
    private Date fechaEmision;

    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;

    public Integer getReservaId() {
        return reservaId;
    }

    public void setReservaId(Integer reservaId) {
        this.reservaId = reservaId;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Pasaje getPasaje() {
        return pasaje;
    }

    public void setPasaje(Pasaje pasaje) {
        this.pasaje = pasaje;
        pasaje.setReserva(this); //con esta linea se puede establecer la RB del oneToOne
    }                               //entre reserva y pasaje a traves del setter.

    public EstadoReservaEnum getEstadoReservaId() {
        return EstadoReservaEnum.parse(estadoReservaId);
    }

    public void setEstadoReservaId(EstadoReservaEnum estadoReservaId) {
        this.estadoReservaId = estadoReservaId.getValue();
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    //metodo de asociacion alternativo para establecer la RB entre reserva y pasaje

    public void asociarPasaje(Pasaje pasaje){
        this.pasaje = pasaje;
        pasaje.setReserva(this);
    }




    public enum EstadoReservaEnum {

        CREADA(1), TRANSMITIENDO_AL_PG(2), ERROR_AL_CONECTAR_PG(3), PENDIENTE_DE_PAGO(4), PAGADA(5),
        CANCELADO_POR_USUARIO(6), CANCELADO_POR_EMPRESA(7), PAGO_RECHAZADO(8), EXPIRADO(9), EMITIDA(10);

        private final Integer value;

        
        private EstadoReservaEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static EstadoReservaEnum parse(Integer id) {
            EstadoReservaEnum status = null; 
            for (EstadoReservaEnum item : EstadoReservaEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }

    


    
}
