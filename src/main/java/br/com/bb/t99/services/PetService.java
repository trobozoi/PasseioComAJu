package br.com.bb.t99.services;

import br.com.bb.t99.models.Pet;

public interface PetService {
    Pet salvar(Pet pet);

    Pet getOne(Long id);
}
