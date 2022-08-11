package br.com.bb.t99.models;

import br.com.bb.t99.models.enums.Sexo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name="PET", schema="exemplo")
@NamedNativeQueries({
        @NamedNativeQuery(name="CONSULTAR_PET", query = "SELECT * from exemplo.PET", resultClass = Pet.class),
})
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nome nao pode ser null")
    @NotEmpty(message = "Nome nao pode ser vazio")
    private String nome;

    private String cor;
    private Sexo sexo;
    private String especie;
    private String raca;
    private LocalDate nascimento;
    private double peso;
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    //@OneToOne(mappedBy = "pet", cascade = CascadeType.ALL)
    //private Agenda agenda;

    public String returnAttributes(){
        return "{\n" +
                "   \"id\" : " + id + ",\n" +
                "   \"nome\" : \"" + nome + "\",\n" +
                "   \"cor\" : \"" + cor + "\",\n" +
                "   \"sexo\" : \"" + sexo + "\",\n" +
                "   \"raca\" : \"" + raca + "\",\n" +
                "   \"nascimento\" : \"" + nascimento + "\",\n" +
                "   \"peso\" : " + peso + ",\n" +
                "   \"telefone\" : \"" + telefone + "\",\n" +
                "   \"endereco\" : " + endereco.returnAttributes() + "\n" +
                '}';
    }
}