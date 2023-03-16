package io.project.app.etcd.etcdapp.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author armena
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer implements Serializable{
    
    private String key;
    
    private String value;
    
}
