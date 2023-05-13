package admin.profile.db.repositories;

import admin.profile.db.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    List<Profile> findByPersonId(Long personId);

    @Query("select p from Profile p where p.person.id = :personId and p.registerProfile is true")
    Profile findLoggedInUserProfile(@Param("personId") Long personId);

    @Query("select p from Profile p where p.registerProfile is true")
    List<Profile> findAllRegisterProfiles();

    @Query("select p from Profile p where p.person.role = 'ADMIN'")
    List<Profile> findAllAdminProfiles();

    Profile findByFirstName(String firstName);

    Profile findByLastName(String lastName);

    Profile findByDob(String dateOfBirth);
}
