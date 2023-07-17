package peaksoft.house.gadgetariumb9.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.house.gadgetariumb9.entities.Mailing;

@Repository
public interface MailingRepository extends JpaRepository<Mailing, Long> {

}