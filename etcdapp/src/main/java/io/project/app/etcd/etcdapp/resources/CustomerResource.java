package io.project.app.etcd.etcdapp.resources;

import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.kv.GetResponse;
import io.project.app.etcd.etcdapp.domain.Customer;
import io.project.app.etcd.etcdapp.services.CustomerService;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author armena
 */
@RestController
@Slf4j
@RequestMapping("/api/v2/customers")
@AllArgsConstructor

public class CustomerResource {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        GetResponse updateTopCustomers = customerService.updateTopCustomers(customer);
        log.info("Customer created: " + updateTopCustomers.toString());
        return ResponseEntity.status(HttpStatus.OK).body("Customer created successfully");
    }

    @GetMapping("/find/{key}")
    public ResponseEntity<String> findCustomerByKey(@PathVariable String key) {
        GetResponse customerData = customerService.findByKey(key);
        log.info("Customer found: " + customerData.toString());
        List<KeyValue> kvs = customerData.getKvs();
        String data = "";
        for (KeyValue kv : kvs) {
            data = new String(kv.getValue().getBytes(), StandardCharsets.UTF_8);
        }
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}
