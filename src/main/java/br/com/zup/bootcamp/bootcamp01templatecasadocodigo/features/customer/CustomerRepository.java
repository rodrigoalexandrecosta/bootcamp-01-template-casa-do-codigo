package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.customer;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    
}
