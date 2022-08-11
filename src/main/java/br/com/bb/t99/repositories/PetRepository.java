package br.com.bb.t99.repositories;

import br.com.bb.t99.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByNomeContaining(String nome);

    @Query("select p from Pet p where p.nome = :nome order by p.id")
    List<Pet> findPet(@Param("nome") String nome);

    //@Query(value = "select * from PET p where p.nome = :nome order by p.id", nativeQuery = true)
    //List<Pet> findPetNative(@Param("nome") String nome);
}
