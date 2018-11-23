package ar.edu.unq.epers.bichomon.backend.model.Eventos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import de.undercouch.bson4jackson.types.ObjectId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.time.LocalDate;

@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Abandono.class, name = "Abandono"),
        @JsonSubTypes.Type(value = Arribo.class, name = "Arribo"),
        @JsonSubTypes.Type(value = Captura.class, name = "Captura"),
        @JsonSubTypes.Type(value = Coronacion.class, name = "Coronacion")
})
public abstract class Evento {


	@MongoObjectId
	private ObjectId id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate fecha;

	private String entrenador;
	private String ubicacion;

    public Evento() {
    }

    public Evento(ObjectId id, LocalDate fecha, String entrenador, String ubicacion) {
        this.id = id;
        this.fecha = fecha;
        this.entrenador =entrenador;
        this.ubicacion = ubicacion;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
