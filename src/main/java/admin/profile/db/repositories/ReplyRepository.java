package admin.profile.db.repositories;

import admin.profile.db.models.MessageReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<MessageReply, Long> {

    @Query("select r from MessageReply r where r.message.person.id = :personId")
    List<MessageReply> getRepliesOfUser(@Param("personId") Long personId);

    @Query("select m from MessageReply m where m.message.person.id = :personId and m.isRead is not true")
    List<MessageReply> findUnreadReplies(@Param("personId") Long personId);

    @Query("select m from MessageReply m where m.message.person.id = :personId and m.isRead is true")
    List<MessageReply> findReadReplies(@Param("personId") Long personId);
}
