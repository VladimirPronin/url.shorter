package com.pronin.url.shorter.rest;

import com.pronin.url.shorter.dao.UrlEntity;
import com.pronin.url.shorter.service.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UrlController {
    private static final Logger logger = LoggerFactory.getLogger(UrlController.class);
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    /**
     * Get original url by short url
     * @param shortUrl - short url
     * @param response - response to set statuses
     * @throws IOException if problem with redirect
     */
    @GetMapping(value = "/{shortUrl}")
    public void getOriginal(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        logger.debug("path = " + shortUrl);
        UrlEntity entity = urlService.getOriginal(shortUrl);
        if (entity == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            response.sendRedirect(entity.getOriginalUrl());
        }
    }

    /**
     * create new short url mapping
     * @param urlEntity contains mapping between short url and original
     * @param response to set statuses - SC_BAD_REQUEST if short url exists
     */
    @PutMapping(value = "/short")
    public void addShortUrl(@RequestBody UrlEntity urlEntity, HttpServletResponse response) {
        logger.debug("add short url = " + urlEntity);
        boolean added = urlService.addShortUrl(urlEntity.getShortUrl(), urlEntity.getOriginalUrl());
        if (!added) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    /**
     * Short url will generated randomly
     *
     * @param original original url
     * @return new short url
     */
    @PutMapping(value = "/short_auto")
    public String addShortUrlAuto(@RequestBody String original) {
        logger.debug("generate new short url for url = " + original);
        return urlService.addShortUrlAuto(original);
    }

}


