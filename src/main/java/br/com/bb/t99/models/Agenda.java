package br.com.bb.t99.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name="AGENDA", schema="exemplo")
@NamedNativeQueries({
        @NamedNativeQuery(name="CONSULTAR_AGENDA", query = "SELECT * from exemplo.AGENDA", resultClass = Agenda.class),
})
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Um pet deve ser selicionado")
    @OneToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
    @NotNull(message = "Um dia e um horário deve ser selecionado")
    private LocalDateTime data;
    @NotNull(message = "Escolha uma duração do passeio")
    private int duracao;

    public String returnAttributes(){
        return "{\n" +
                "   \"id\" : " + id + ",\n" +
                "   \"pet\" : " + pet.returnAttributes() + ",\n" +
                "   \"data\" : \"" + data + "\",\n" +
                "   \"duracao\" : " + duracao + "\n" +
                '}';
    }
}
