package io.project.app.etcd.etcdapp.resources;

import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.kv.GetResponse;
import io.project.app.etcd.etcdapp.domain.Customer;
import io.project.app.etcd.etcdapp.services.CustomerService;
import java.nio.charset.StandardCharsets;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author armena
 */
@RestController
@Slf4j
@RequestMapping("/api/v2/customers")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/create")
    public ResponseEntity put(@RequestBody Customer customer) {
        GetResponse updateTopCustomers = customerService.updateTopCustomers(customer);
        log.info("Controller 1  " + updateTopCustomers.toString());
        return ResponseEntity.status(HttpStatus.OK).body("Done");

    }

    @GetMapping(path = "/find")
    public ResponseEntity get(@RequestParam String key) {
        GetResponse customerData = customerService.findByKey(key);
        log.info("Controller  " + customerData.toString());
        List<KeyValue> kvs = customerData.getKvs();
        String data = "";
        for (KeyValue kv : kvs) {
            ///   String ckey = new String(kv.getKey().getBytes(), StandardCharsets.UTF_8);
            data = new String(kv.getValue().getBytes(), StandardCharsets.UTF_8);
        }

        return ResponseEntity.status(HttpStatus.OK).body(data);

    }

}
