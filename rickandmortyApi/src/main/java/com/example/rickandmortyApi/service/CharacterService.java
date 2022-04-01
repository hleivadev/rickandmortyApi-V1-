package com.example.rickandmortyApi.service;

import ch.qos.logback.core.LogbackException;
import com.example.rickandmortyApi.model.CharacterCartoon;
import com.example.rickandmortyApi.model.LocationCartoon;
import com.example.rickandmortyApi.model.OriginCharacter;
import com.example.rickandmortyApi.model.Personage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CharacterService {
    private static final Logger log = LoggerFactory.getLogger(LogbackException.class);

    /***
     * Metodo que busca los datos del personaje desde la API RickAndMorty
     *
     * @param characterId
     * @return Personage
     */
    public Personage findPersonage(String characterId) {
        log.debug("Begin Method findPersonage()");
        try {
            String urlApi = "https://rickandmortyapi.com/api/character/" + characterId;
            RestTemplate restTemplate = new RestTemplate();
            /* Obtiene mediante restemplate datos desde API personaje y se almacenan en entidad Personage **/
            Personage personageResponse = restTemplate.getForObject(urlApi, Personage.class);

            return personageResponse != null ? personageResponse : new Personage();

        } catch (Exception e) {
            log.debug("Exception Method findPersonage(" + characterId + ")", e.getCause());
            log.error(String.format("Error Method findPersonage(%s)", characterId), e.getMessage());
            return null;
        }
    }

    /**
     * Metodo que obtiene los datos de la locacion enviandole la ruta de la API
     *
     * @param locationUrl
     * @return
     */
    public Optional<LocationCartoon> findLocationCartoon(String locationUrl) {
        log.debug("Begin Method findLocationCartoon()");
        try {
            RestTemplate restTemplate = new RestTemplate();
            /* Obtiene mediante restemplate datos desde API Locacion y se almacenan en entidad Personage **/
            Optional<LocationCartoon> locationCartoon = Optional.ofNullable(restTemplate.getForObject(locationUrl, LocationCartoon.class)) ;

            return locationCartoon.isPresent() ? locationCartoon : Optional.of(new LocationCartoon());
        } catch (Exception e) {
            log.debug("Exception Method findLocationCartoon()", e.getCause());
            log.error(e.getMessage());
            return Optional.empty();
        }
    }


    public CharacterCartoon getCharacterCartoon(String characterId) {
        log.debug("Begin Method getCharacterCartoon()");
        try {
            CharacterCartoon characterCartoon =  new CharacterCartoon();
            OriginCharacter originCharacter = new OriginCharacter();
            Optional<LocationCartoon> locationCartoon;
            /* llamamos al metodo findPersonage para obtener los datos del personaje buscado**/
            Optional<Personage> personage = Optional.ofNullable(findPersonage(characterId));
            if (personage.isPresent()) {
                /* obtenemos la Url del origen del personaje y enviamos a buscar su Locacion**/
                String urlLocation = personage.stream().map(x -> x.getLocation().getUrl()).collect(Collectors.joining());
                locationCartoon = findLocationCartoon(urlLocation);
                /* seteamo en el objeto los valores para el personaje a retornar en la API**/
                characterCartoon.setId(personage.get().getId());
                characterCartoon.setName(personage.get().getName());
                characterCartoon.setStatus(personage.get().getStatus());
                characterCartoon.setSpecies(personage.get().getSpecies());
                characterCartoon.setType(personage.get().getType());
                characterCartoon.setEpisodeCount(personage.get().getEpisode().size());
                /*Seteamo los valores de la ubicacion del presonaje**/
                if(locationCartoon.isPresent()) {
                    originCharacter.setName(locationCartoon.get().getName());
                    originCharacter.setUrl(locationCartoon.get().getUrl());
                    originCharacter.setDimension(locationCartoon.get().getDimension());
                    originCharacter.setResidents(locationCartoon.get().getResidents());
                }
                /*seteamos el valor del objeto del Origen del personaje**/
                characterCartoon.setOriginCharacter(originCharacter);

            }
            return characterCartoon;
        } catch (Exception e) {
            log.debug("Exception Method getCharacterCartoon()", e.getCause());
            log.error(String.format("Error Method getCharacterCartoon(%s)", characterId),e.getMessage());
            return null;
        }

    }
}
