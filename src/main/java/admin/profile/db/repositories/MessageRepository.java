package admin.profile.db.repositories;

import admin.profile.db.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select m from Message m where m.person.id = :personId and m.isRead is not true")
    List<Message> findUnreadMessages(@Param("personId") Long personId);

    @Query("select m from Message m where m.person.id = :personId and m.isRead is true")
    List<Message> findReadMessages(@Param("personId") Long personId);
}
