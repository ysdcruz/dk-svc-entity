package com.devkinetics.svc.entity.repository;

import com.devkinetics.svc.entity.model.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository extends JpaRepository<App, Long> {

    App findByMerchantId(String merchantId);

    @Query(value = "SELECT " +
            "c.* " +
            "FROM cfg_app c " +
            "WHERE " +
            "?1 = ANY (string_to_array(c.domains, ',')) " +
            "AND c.is_active IS TRUE",
            nativeQuery = true
    )
    App findByDomains(String domain);

}
