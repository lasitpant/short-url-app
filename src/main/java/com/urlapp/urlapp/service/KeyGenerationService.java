package com.urlapp.urlapp.service;

import com.urlapp.urlapp.entity.KeyStore;
import com.urlapp.urlapp.repository.KeyRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

@Service
public class KeyGenerationService {


    private final KeyRepository keyRepository;

    public KeyGenerationService(KeyRepository keyRepository){
        this.keyRepository = keyRepository;
    }

    public void generateKeys(){

        String generatedString = this.getAlphaNumericString(7);
        if(!keyRepository.existsById(generatedString)){
            KeyStore keyStore = new KeyStore();
            keyStore.setKey(generatedString);
            keyStore.setActiveState(false);
            keyRepository.save(keyStore);
        }

    }

    public void saveToKeyStore(){

    }

    public String getKey(){
        List<KeyStore> data=  keyRepository.getTop();
        data.get(0).getKey();
        System.out.println(data.get(0).getKey());
        return data.get(0).getKey();
    }
    public void updateState(String shortUrl){
        KeyStore key = keyRepository.findBykeyUrl(shortUrl);
        key.setActiveState(true);
        keyRepository.save(key);
    }

    private String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
