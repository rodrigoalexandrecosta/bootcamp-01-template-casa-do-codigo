package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.localization;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.CountryState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryStateRepository extends CrudRepository<CountryState, Long> {

}
