package io.project.app.etcd.etcdapp.services;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.kv.GetResponse;
import io.project.app.etcd.etcdapp.domain.Customer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author armena
 */
@Service
@Slf4j
public class CustomerService {

    public GetResponse updateTopCustomers(Customer customer) {
        try {
            log.info("Starting update of top customers ");
            

            Client etcdClient = Client.builder().endpoints("http://etcd:2379").build();
            KV kvClient = etcdClient.getKVClient();

            ByteSequence key = ByteSequence.from(customer.getKey().getBytes());
            ByteSequence value = ByteSequence.from(customer.getValue().getBytes());

            log.info("PUT customer data");
            kvClient.put(key, value).get();

            CompletableFuture<GetResponse> getFuture = kvClient.get(key);

            GetResponse response = getFuture.get();
            log.info("RESPONSE " + response.toString());

            return response;
            // kvClient.delete(key).get();
        } catch (InterruptedException | ExecutionException ex) {
            log.error("Error happened");
        }

        return null;

    }

    public GetResponse findByKey(String customerKey) {
        try {
            log.info("Starting update of top customers ");

            Client etcdClient = Client.builder().endpoints("http://etcd:2379").build();
            KV kvClient = etcdClient.getKVClient();

            ByteSequence key = ByteSequence.from(customerKey.getBytes());

            CompletableFuture<GetResponse> getFuture = kvClient.get(key);

            GetResponse response = getFuture.get();
            log.info("Find by key  " + response.toString());

            return response;
            // kvClient.delete(key).get();
        } catch (InterruptedException | ExecutionException ex) {
            log.error("Error happened");
        }

        return null;

    }
}
