package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.order;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

}
