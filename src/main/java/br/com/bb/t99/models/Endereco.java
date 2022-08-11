package br.com.bb.t99.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name="ENDERECO", schema="exemplo")
@NamedNativeQueries({
        @NamedNativeQuery(name="CONSULTAR_ENDERECO", query = "SELECT * from exemplo.ENDERECO", resultClass = Endereco.class),
})
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String endereco;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;

    public String returnAttributes(){
        return "{\n" +
                "   \"id\" : " + id + ",\n" +
                "   \"cep\" : \"" + cep + "\",\n" +
                "   \"endereco\" : \"" + endereco + "\",\n" +
                "   \"numero\" : \"" + numero + "\",\n" +
                "   \"bairro\" : \"" + bairro + "\",\n" +
                "   \"cidade\" : \"" + cidade + "\",\n" +
                "   \"uf\" : \"" + uf + "\"\n" +
                '}';
    }
}
