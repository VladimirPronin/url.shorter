package com.pronin.url.shorter.dao;

import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<UrlEntity, Long> {
    /**
     *https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details
     * created own method (property expressions)
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-property-expressions
     */
    UrlEntity findFirstByShortUrl(String shortUrl);
}
