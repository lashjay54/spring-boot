package admin.profile.services;

import admin.profile.api.models.*;
import admin.profile.db.models.*;
import admin.profile.db.repositories.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {
    @Autowired
    private EventRepository activityRepository;

    @Autowired
    private CreditProfileRepository creditProfileRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ReplyRepository replyRepository;

    public void saveActivity(ActivityDAO activityDAO) {
        activityRepository.save(Event.valueOf(activityDAO));
    }

    public ActivityDAO getRegisteredActivity(Long personId) {
        List<Event> activities = activityRepository.findByEventTypeAndPersonIdOrderByEventDate("REGISTER", personId);
        return !activities.isEmpty() ? ActivityDAO.valueOf(activities.get(0)) : new ActivityDAO();
    }

    public void saveCreditProfile(CreditDAO creditProfileDAO) {
        Credit creditProfile = Credit.valueOf(creditProfileDAO);
        Person person = personRepository.getOne(creditProfileDAO.getPersonDAO().getId());
        creditProfile.setPerson(person);
        creditProfileRepository.save(creditProfile);
    }

    public void updateCreditProfile(CreditDAO creditProfileDAO) {
        Credit creditProfile = creditProfileRepository.getOne(creditProfileDAO.getId());
        creditProfile.setCardHolderName(creditProfileDAO.getCardHolderName());
        creditProfile.setCreditCardNumber(creditProfileDAO.getCreditCardNumber());
        creditProfile.setCreditCardType(creditProfileDAO.getCreditCardType());
        creditProfile.setExpirationDate(creditProfileDAO.getExpirationDate());
        creditProfile.setDefaultOrPreferred(creditProfileDAO.getDefaultOrPreferred());
        creditProfileRepository.save(creditProfile);
    }

    public List<CreditDAO> findAllCreditProfilesByPersonId(Long personId) {
        List<CreditDAO> creditProfileDAOS = new ArrayList<>();
        creditProfileRepository.findByPersonId(personId).forEach(creditProfile -> {
            creditProfileDAOS.add(CreditDAO.valueOf(creditProfile));
        });
        return creditProfileDAOS;
    }

    public CreditDAO findOneByCreditProfileId(Long creditProfileId) {
        return CreditDAO.valueOf(creditProfileRepository.getOne(creditProfileId));
    }

    public void deleteCreditProfile(Long creditProfileId) {
        creditProfileRepository.deleteById(creditProfileId);
    }

    public void saveMessage(MessageDAO messageDAO) {
        Message message = Message.valueOf(messageDAO);
        message.setMsgDate(DateTime.now().getMillis());
        Person person = personRepository.getOne(messageDAO.getPersonDAO().getId());
        message.setPerson(person);
        messageRepository.save(message);
    }

    public List<MessageDAO> findAllMessages() {
        List<MessageDAO> messageDAOS = new ArrayList<>();
        messageRepository.findAll().forEach(message -> {
            messageDAOS.add(MessageDAO.valueOf(message));
        });
        return messageDAOS;
    }

    public void savePerson(PersonDAO personDAO) {
        Person person = Person.valueOf(personDAO);
        personRepository.save(person);
    }

    public void registerPerson(ProfileDAO profileDAO) {
        Person person = Person.valueOf(profileDAO.getPersonDAO());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword().trim()));
        person = personRepository.save(person);
        Profile profile = Profile.valueOf(profileDAO);
        profile.setPerson(person);
        profileRepository.save(profile);
        ActivityDAO activityDAO = new ActivityDAO();
        activityDAO.setType("REGISTER");
        activityDAO.setDateTime(DateTime.now().getMillis());
        activityDAO.setPersonDAO(PersonDAO.valueOf(person));
        saveActivity(activityDAO);
    }

    public void registerAdmin(ProfileDAO profileDAO) {
        Person person = Person.valueOf(profileDAO.getPersonDAO());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword().trim()));
        person.setRole("ADMIN");
        person = personRepository.save(person);
        Profile profile = Profile.valueOf(profileDAO);
        profile.setPerson(person);
        profileRepository.save(profile);
        ActivityDAO activityDAO = new ActivityDAO();
        activityDAO.setType("REGISTER");
        activityDAO.setDateTime(DateTime.now().getMillis());
        activityDAO.setPersonDAO(PersonDAO.valueOf(person));
        saveActivity(activityDAO);
    }

    public Person findPersonByUserName(String username) {
        return personRepository.findByUsername(username);
    }

    public void saveProfile(ProfileDAO profileDAO) {
        Profile profile = Profile.valueOf(profileDAO);
        Person person = personRepository.getOne(profileDAO.getPersonDAO().getId());
        profile.setPerson(person);
        profileRepository.save(profile);
    }

    public void updateProfile(ProfileDAO profileDAO) {
        Profile profile = profileRepository.getOne(profileDAO.getId());
        profile.setAddress(profileDAO.getAddress());
        profile.setCity(profileDAO.getCity());
        profile.setCountry(profileDAO.getCountry());
        profile.setDob(profileDAO.getDateOfBirth());
        profile.setDefaultBillingAddress(profileDAO.getDefaultBillingAddress());
        profile.setDefaultShippingAddress(profileDAO.getDefaultShippingAddress());
        profile.setEmail(profileDAO.getEmail());
        profile.setFirstName(profileDAO.getFirstName());
        profile.setLastName(profileDAO.getLastName());
        profile.setPostal(profileDAO.getPostal());
        profileRepository.save(profile);
        ActivityDAO activityDAO = new ActivityDAO();
        activityDAO.setType("PROFILE_UPDATE");
        activityDAO.setDateTime(DateTime.now().getMillis());
        activityDAO.setPersonDAO(PersonDAO.valueOf(profile.getPerson()));
        saveActivity(activityDAO);
    }

    public List<ProfileDAO> findAllProfilesByPersonId(Long personId) {
        List<ProfileDAO> profileDAOS = new ArrayList<>();
        profileRepository.findByPersonId(personId).forEach(profile -> {
            profileDAOS.add(ProfileDAO.valueOf(profile));
        });
        return profileDAOS;
    }

    public ProfileDAO findOneByProfileId(Long profileId) {
        return ProfileDAO.valueOf(profileRepository.getOne(profileId));
    }

    public void deleteProfile(Long profileId) {
        profileRepository.deleteById(profileId);
    }

    public List<ProfileDAO> findAllProfiles() {
        List<ProfileDAO> profileDAOS = new ArrayList<>();
        profileRepository.findAllRegisterProfiles().forEach(profile -> {
            ActivityDAO activityDAO = getRegisteredActivity(profile.getPerson().getId());
            ProfileDAO profileDAO = new ProfileDAO();
            profileDAO = ProfileDAO.valueOf(profile);
            profileDAO.setDateTime(activityDAO.getDateTime());
            profileDAOS.add(profileDAO);
        });
        return profileDAOS;
    }

    public void deleteProfiles(List<Profile> profiles) {
        profileRepository.deleteAll(profiles);
    }

    public List<ProfileDAO> findAllAdminProfiles() {
        List<ProfileDAO> profileDAOS = new ArrayList<>();
        profileRepository.findAllAdminProfiles().forEach(profile -> {
            ActivityDAO activityDAO = getRegisteredActivity(profile.getPerson().getId());
            ProfileDAO profileDAO = new ProfileDAO();
            profileDAO = ProfileDAO.valueOf(profile);
            profileDAO.setDateTime(activityDAO.getDateTime());
            profileDAOS.add(profileDAO);
        });
        return profileDAOS;
    }

    public void saveReply(ReplyDAO replyDAO) {
        Message message = messageRepository.getOne(replyDAO.getMessageDAO().getId());
        Person person = personRepository.getOne(replyDAO.getPersonDAO().getId());
        MessageReply reply = new MessageReply();
        reply.setMessage(replyDAO.getReplyMessage());
        reply.setPerson(person);
        reply.setFirstMessage(message);
        reply.setRead(false);
        reply.setReplyDate(DateTime.now().getMillis());
        replyRepository.save(reply);
    }

    public List<ReplyDAO> gerReplies(Long personId) {
        List<MessageReply> replies = replyRepository.getRepliesOfUser(personId);
        List<ReplyDAO> replyDAOS = new ArrayList<>();
        replies.forEach(reply -> {
            replyDAOS.add(ReplyDAO.valueOf(reply));
        });
        return replyDAOS;
    }
}
