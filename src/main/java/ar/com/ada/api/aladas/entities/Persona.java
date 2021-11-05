package ar.com.ada.api.aladas.entities;

import java.util.Date;

import javax.persistence.*;

import ar.com.ada.api.aladas.entities.Pais.PaisEnum;
import ar.com.ada.api.aladas.entities.Pais.TipoDocuEnum;

@MappedSuperclass
public class Persona {

    private String nombre;
    
    @Column(name = "tipo_documento_id")// es un enu, lo debo cambiar en el getter y setter
    private Integer tipoDocumentoId;

    private String documento;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "pais_id")
    private Integer paisId;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoDocuEnum getTipoDocumentoId() {
        return TipoDocuEnum.parse(this.tipoDocumentoId);//debe devolver un enum, uso parse ()
    }

    public void setTipoDocumentoId(TipoDocuEnum tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId.getValue();//debe ser un entero, los transformo con getValue()
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public PaisEnum getPaisId() {
        return PaisEnum.parse(this.paisId);//con el metodo parse se esta transformando a enum
    }

    public void setPaisId(PaisEnum paisId) {
        this.paisId = paisId.getValue();
    }

    

    
}
