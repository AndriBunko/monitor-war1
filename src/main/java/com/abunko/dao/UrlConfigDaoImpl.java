package com.abunko.dao;

import com.abunko.model.UrlConfig;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UrlConfigDaoImpl implements UrlConfigDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void add(UrlConfig urlConfig) {
        Query query = entityManager.createQuery("insert into UrlConfig (url, cooldown, expectedResponseCode, maxResponseLength, minResponseLength, responseTimeOK, responseTimeWARNING, substring) values ( "+ urlConfig.getUrl() + ", " + urlConfig.getCooldown() +
                ", " + urlConfig.getResponseTimeOK() + ", " + urlConfig.getResponseTimeWARNING() + ", " + urlConfig.getExpectedResponseCode() +
                ", " + urlConfig.getMinResponseLength() + ", " + urlConfig.getMaxResponseLength() + ", " + urlConfig.getSubstring() + ")");
   //     entityManager.persist(urlConfig);
    }

    @Override
    public List<UrlConfig> getAll() {
        TypedQuery<UrlConfig> query = entityManager.createQuery("FROM UrlConfig c", UrlConfig.class);
        return query.getResultList();
    }

}
