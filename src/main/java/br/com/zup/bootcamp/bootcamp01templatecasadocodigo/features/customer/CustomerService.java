package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.customer;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Customer;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateCustomerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public Long create(final CreateCustomerRequest request) {
        final Customer customer = this.customerRepository.save(request.toCostumer());
        return customer.getId();
    }
}
