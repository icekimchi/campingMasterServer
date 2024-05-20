package cbnu.campingmaster.gocamping.service;

import cbnu.campingmaster.gocamping.domain.Campsite;
import cbnu.campingmaster.gocamping.dto.GoCampingItemDto;
import cbnu.campingmaster.gocamping.repository.CampsiteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampsiteService {

    private final CampsiteRepository campsiteRepository;

    @Autowired
    public CampsiteService(CampsiteRepository campsiteRepository) {
        this.campsiteRepository = campsiteRepository;
    }

    @Transactional
    public void saveCampsite(GoCampingItemDto goCampingItemDto) {
        Campsite campsite = Campsite.createCampsite(goCampingItemDto);
        campsiteRepository.save(campsite);
    }
}