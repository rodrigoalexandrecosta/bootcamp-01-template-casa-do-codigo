package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.customer;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    
}
