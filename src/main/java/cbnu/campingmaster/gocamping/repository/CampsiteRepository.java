package cbnu.campingmaster.gocamping.repository;

import cbnu.campingmaster.gocamping.domain.Campsite;
import cbnu.campingmaster.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampsiteRepository extends JpaRepository<Campsite, Long> {
}