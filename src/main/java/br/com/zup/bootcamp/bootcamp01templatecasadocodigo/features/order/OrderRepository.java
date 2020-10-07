package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.order;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
