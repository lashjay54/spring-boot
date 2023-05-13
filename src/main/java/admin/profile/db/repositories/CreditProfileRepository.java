package admin.profile.db.repositories;

import admin.profile.db.models.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CreditProfileRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByPersonId(Long personId);
}
