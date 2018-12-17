package com.pronin.url.shorter.dao;

import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<UrlEntity, Long> {

    UrlEntity findFirstByShortUrl(String shortUrl);
}
