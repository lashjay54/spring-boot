package admin.profile.db.repositories;

import admin.profile.db.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByEventTypeAndPersonIdOrderByEventDate(String type, Long personId);

}
