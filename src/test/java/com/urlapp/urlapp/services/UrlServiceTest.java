package com.urlapp.urlapp.services;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import com.urlapp.urlapp.entity.UrlMapping;
import com.urlapp.urlapp.excepions.UrlNotFoundException;
import com.urlapp.urlapp.repository.UrlMappingRepository;
import com.urlapp.urlapp.service.UrlShorteningService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UrlServiceTest {

    @InjectMocks
    private UrlShorteningService urlShorteningService;

    @Mock
    private UrlMappingRepository urlMappingRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = NullPointerException.class)
    public void whenCodeDoesNotExistThrowsUrlNotFoundException() {
        // Given
        String notExistingCode = "2YpwKFJ";

        Mockito.when(urlMappingRepository.findById(notExistingCode))
                .thenThrow(new NullPointerException());

        // When
        urlShorteningService.getOriginalUrl(notExistingCode);

    }

    @Test
    public void whenCodeExists(){
        //Given
        String existingCode = "acva12a";
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setLongUrl("google.com");
        urlMapping.setShortUrl(existingCode);

        Mockito.when(urlMappingRepository.findByShortUrl(existingCode))
                .thenReturn(urlMapping);

        String urlMapping1 = urlShorteningService.getOriginalUrl(existingCode);
        Assert.assertEquals(urlMapping1, "google.com");
    }


}
