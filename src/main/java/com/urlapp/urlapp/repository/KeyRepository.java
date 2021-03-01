package com.urlapp.urlapp.repository;

import com.urlapp.urlapp.entity.KeyStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface KeyRepository extends JpaRepository<KeyStore,String> {
    @Query(nativeQuery = true, value ="select key_url, active_state from keystore k where k.active_state=false limit 1")
    public List<KeyStore> getTop();

    KeyStore findBykeyUrl(String keyUrl);
}
