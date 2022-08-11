package br.com.bb.t99.services.impl;

import br.com.bb.t99.models.Pet;
import br.com.bb.t99.repositories.PetRepository;
import br.com.bb.t99.services.PetService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @CacheEvict(value = "agendas", allEntries = true)
    public Pet salvar(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet getOne(Long id) {
        return petRepository.getOne(id);
    }
}
