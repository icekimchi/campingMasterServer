package cbnu.campingmaster.gocamping.service;

import cbnu.campingmaster.gocamping.domain.Campsite;
import cbnu.campingmaster.gocamping.dto.CampingSiteDto;
import cbnu.campingmaster.gocamping.dto.GoCampingItemDto;
import cbnu.campingmaster.gocamping.repository.CampsiteRepository;
import jakarta.transaction.Transactional;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampsiteService {

    private final CampsiteRepository campsiteRepository;

    @Autowired
    public CampsiteService(CampsiteRepository campsiteRepository) {
        this.campsiteRepository = campsiteRepository;
    }

    @Transactional
    public Campsite saveCampsite(JSONObject jsonObject) {
        Campsite campsite = Campsite.createCampsite(jsonObject);
        campsiteRepository.save(campsite);
        return campsite;
    }
}