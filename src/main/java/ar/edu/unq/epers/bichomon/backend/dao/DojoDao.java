package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Dojo;

public interface DojoDao extends GenericDao<Dojo, String>{
    Bicho getCampeonHistorico(String nombreDojo);
}
