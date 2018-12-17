package com.pronin.url.shorter.service;

import com.pronin.url.shorter.dao.UrlEntity;
import com.pronin.url.shorter.dao.UrlRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    /**
     * create new short url
     * @param shortUrl short url
     * @param originalUrl original url
     * @return true if success
     */
    public boolean addShortUrl(String shortUrl, String originalUrl) {
        UrlEntity firstByShortUrl = urlRepository.findFirstByShortUrl(shortUrl);
        if (firstByShortUrl != null) {
            return false;
        }
        UrlEntity newEntity = createEntity(shortUrl, originalUrl);
        urlRepository.save(newEntity);
        return true;
    }

    /**
     * try to generate short url
     * @param originalUrl to map to short url
     * @return generated short url
     */
    public String addShortUrlAuto(String originalUrl) {
        while (true) {
            String shortUrl = RandomString.make(4); //I've found this method in IJ
            UrlEntity firstByShortUrl = urlRepository.findFirstByShortUrl(shortUrl);
            if(firstByShortUrl == null){
                UrlEntity newEntity = new UrlEntity();
                newEntity.setOriginalUrl(originalUrl);
                newEntity.setShortUrl(shortUrl);
                urlRepository.save(newEntity);
                return shortUrl;
            }
        }
    }

    /**
     * return original by short
     * @param shortUrl
     * @return
     */
    public UrlEntity getOriginal(String shortUrl){
        return urlRepository.findFirstByShortUrl(shortUrl);
    }

    private UrlEntity createEntity(String shortUrl, String originalUrl){
        UrlEntity entity = new UrlEntity();
        entity.setOriginalUrl(originalUrl);
        entity.setShortUrl(shortUrl);
        return entity;
    }

}
