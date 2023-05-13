package admin.profile.controllers;

import admin.profile.api.models.*;
import admin.profile.constants.Constants;
import admin.profile.db.models.Event;
import admin.profile.db.models.Message;
import admin.profile.db.models.MessageReply;
import admin.profile.db.models.Profile;
import admin.profile.db.repositories.*;
import admin.profile.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private MainService mainService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private EventRepository activityRepository;

    @GetMapping(Constants.Urls.LOGIN)
    public String viewLoginPage(Model model) {
        return Constants.Views.LOGIN;
    }

    @GetMapping(Constants.Urls.REGISTER)
    public String viewRegisterPage(Model model) {
        return Constants.Views.REGISTER;
    }

    @GetMapping(Constants.Urls.HOME)
    public String viewHomePage(Model model, HttpSession httpSession) {
        DashboardDAO dashboardDAO = new DashboardDAO();
        ResponseDAO mainDAO = new ResponseDAO();
        List<Event> activities = activityRepository.findByEventTypeAndPersonIdOrderByEventDate("LOGIN", (Long) httpSession.getAttribute("personId"));
        if (activities.size() > 1) {
            dashboardDAO.setLoginActivity(activities.get(1).getEventDate());
        } else {
            dashboardDAO.setLoginActivity(activities.get(0).getEventDate());
        }
        List<Event> activities2 = activityRepository.findByEventTypeAndPersonIdOrderByEventDate("PROFILE_UPDATE_ACTIVITY", (Long) httpSession.getAttribute("personId"));
        if (!activities2.isEmpty()) {
            dashboardDAO.setProfileUpdateActivity(activities2.get(0).getEventDate());
        }
        if ("ADMIN".equals(httpSession.getAttribute("role"))) {
            List<Message> messages1 = messageRepository.findReadMessages((Long) httpSession.getAttribute("personId"));
            List<Message> messages2 = messageRepository.findUnreadMessages((Long) httpSession.getAttribute("personId"));
            dashboardDAO.setReadMessages(String.valueOf(messages1.size()));
            dashboardDAO.setUnreadMessages(String.valueOf(messages2.size()));
        } else {
            List<MessageReply> replies1 = replyRepository.findReadReplies((Long) httpSession.getAttribute("personId"));
            List<MessageReply> replies2 = replyRepository.findUnreadReplies((Long) httpSession.getAttribute("personId"));
            dashboardDAO.setReadMessages(String.valueOf(replies1.size()));
            dashboardDAO.setUnreadMessages(String.valueOf(replies2.size()));
        }
        mainDAO.setDashboardDAO(dashboardDAO);
        mainDAO.setProfileDAO(ProfileDAO.valueOf(profileRepository.findLoggedInUserProfile((Long) httpSession.getAttribute("personId"))));
        model.addAttribute("data", mainDAO);
        return Constants.Views.HOME;
    }

    @GetMapping(Constants.Urls.MY_PROFILE)
    public String viewMyProfilePage(@RequestParam(name = "id", required = false) Long profileId, Model model, HttpSession httpSession) {
        ResponseDAO mainDAO = new ResponseDAO();
        if (profileId != null) {
            mainDAO.setProfileDAO(mainService.findOneByProfileId(profileId));
        } else if ("ADMIN".equals(httpSession.getAttribute("role"))) {
            Profile profile = profileRepository.findLoggedInUserProfile((Long) httpSession.getAttribute("personId"));
            mainDAO.setProfileDAO(ProfileDAO.valueOf(profile));
        }
        mainDAO.setProfileDAOS(mainService.findAllProfilesByPersonId((Long) httpSession.getAttribute("personId")));
        model.addAttribute("data", mainDAO);
        return Constants.Views.MY_PROFILE;
    }

    @GetMapping(Constants.Urls.DELETE_MY_PROFILE)
    public String deleteMyProfile(@RequestParam(name = "id") Long profileId) {
        if (profileId != null) {
            mainService.deleteProfile(profileId);
        }
        return Constants.Redirects.MY_PROFILE;
    }

    @GetMapping(Constants.Urls.CREDIT_PROFILE)
    public String viewCreditProfilePage(@RequestParam(name = "id", required = false) Long creditProfileId, Model model, HttpSession httpSession) {
        ResponseDAO mainDAO = new ResponseDAO();
        if (creditProfileId != null) {
            mainDAO.setCreditProfileDAO(mainService.findOneByCreditProfileId(creditProfileId));
        }
        mainDAO.setCreditProfileDAOS(mainService.findAllCreditProfilesByPersonId((Long) httpSession.getAttribute("personId")));
        model.addAttribute("data", mainDAO);
        return Constants.Views.CREDIT_PROFILE;
    }

    @GetMapping(Constants.Urls.DELETE_CREDIT_PROFILE)
    public String deleteCreditProfile(@RequestParam(name = "id") Long creditProfileId) {
        if (creditProfileId != null) {
            mainService.deleteCreditProfile(creditProfileId);
        }
        return Constants.Redirects.CREDIT_PROFILE;
    }

    @GetMapping(Constants.Urls.INBOX)
    public String viewInbox(@RequestParam(name = "id", required = false) Long messageId, Model model, HttpSession httpSession) {
        ResponseDAO mainDAO = new ResponseDAO();
        if ("ADMIN".equals(httpSession.getAttribute("role"))) {
            mainDAO.setMessageDAOS(mainService.findAllMessages());
            if (messageId != null) {
                Message message = messageRepository.getOne(messageId);
                message.setRead(true);
                message = messageRepository.save(message);
                mainDAO.setMessageDAO(MessageDAO.valueOf(message));
            }
            model.addAttribute("data", mainDAO);
            return Constants.Views.ADMIN_INBOX;
        }
        List<ReplyDAO> replyDAOS = mainService.gerReplies((Long) httpSession.getAttribute("personId"));
        mainDAO.setReplyDAOS(replyDAOS);
        if (messageId != null) {
            MessageReply reply = replyRepository.getOne(messageId);
            reply.setRead(true);
            reply = replyRepository.save(reply);
            mainDAO.setReplyDAO(ReplyDAO.valueOf(reply));
        }
        model.addAttribute("data", mainDAO);
        return Constants.Views.INBOX;
    }

    @GetMapping(Constants.Urls.SUPPORT)
    public String viewSupportPage(Model model, HttpSession httpSession) {
        Long personId = (Long) httpSession.getAttribute("personId");
        Profile profile = profileRepository.findLoggedInUserProfile(personId);
        ResponseDAO mainDAO = new ResponseDAO();
        MessageDAO messageDAO = new MessageDAO();
        messageDAO.setFirstName(profile.getFirstName());
        messageDAO.setEmail(profile.getEmail());
        mainDAO.setMessageDAO(messageDAO);
        model.addAttribute("data", mainDAO);
        return Constants.Views.SUPPORT;
    }

    @GetMapping(Constants.Urls.USERS)
    public String viewUsersPage(Model model) {
        ResponseDAO mainDAO = new ResponseDAO();
        List<ProfileDAO> profileDAOS = mainService.findAllProfiles();
        mainDAO.setProfileDAOS(profileDAOS);
        model.addAttribute("data", mainDAO);
        return Constants.Views.USERS;
    }

    @GetMapping(Constants.Urls.DELETE_PERSON)
    public String deletePerson(@RequestParam(name = "id") Long personId) {
        List<Profile> profiles = profileRepository.findByPersonId(personId);
        profileRepository.deleteAll(profiles);
        personRepository.deleteById(personId);
        return Constants.Redirects.USERS;
    }

    @GetMapping(Constants.Urls.DELETE_MESSAGE)
    public String deleteMessage(@RequestParam(name = "id", required = false) Long messageId, Model model, HttpSession httpSession) {
        ResponseDAO mainDAO = new ResponseDAO();
        if ("ADMIN".equals(httpSession.getAttribute("role"))) {
            if (messageId != null) {
                messageRepository.deleteById(messageId);
            }
            return Constants.Redirects.INBOX;
        }
        if (messageId != null) {
            replyRepository.deleteById(messageId);
        }
        return Constants.Redirects.INBOX;
    }

    @GetMapping(Constants.Urls.MESSAGE_REPLY)
    public String messageReply(@RequestParam(name = "id") Long messageId, Model model, HttpSession httpSession) {
        ResponseDAO mainDAO = new ResponseDAO();
        Profile profile = profileRepository.findLoggedInUserProfile((Long) httpSession.getAttribute("personId"));
        mainDAO.setProfileDAO(ProfileDAO.valueOf(profile));
        mainDAO.setMessageDAO(MessageDAO.valueOf(messageRepository.getOne(messageId)));
        model.addAttribute("data", mainDAO);
        return Constants.Views.MESSAGE_REPLY;
    }

    @GetMapping(Constants.Urls.ADMIN_USER)
    public String viewAdminSupportPage(Model model) {
        ResponseDAO mainDAO = new ResponseDAO();
        mainDAO.setProfileDAOS(mainService.findAllAdminProfiles());
        model.addAttribute("data", mainDAO);
        return Constants.Views.ADMIN_USER;
    }

    @PostMapping(Constants.Urls.REGISTER)
    public String registerPerson(ProfileDAO profileDAO) {
        ResponseDAO mainDAO = new ResponseDAO();
        if (profileRepository.findByFirstName(profileDAO.getFirstName()) == null &&
                profileRepository.findByLastName(profileDAO.getLastName()) == null &&
                profileRepository.findByDob(profileDAO.getDateOfBirth()) == null) {
            mainService.registerPerson(profileDAO);
            mainDAO.setErrorMessage("Successfully registered");
            return Constants.Redirects.LOGIN;
        }
        mainDAO.setErrorMessage("Invalid inputs");
        return Constants.Redirects.REGISTER;
    }

    @PostMapping(Constants.Urls.MY_PROFILE)
    public String updateProfile(ProfileDAO profileDAO) {
        if (profileDAO.getId()==null) {
            if (profileRepository.findByFirstName(profileDAO.getFirstName()) == null &&
                    profileRepository.findByLastName(profileDAO.getLastName()) == null &&
                    profileRepository.findByDob(profileDAO.getDateOfBirth()) == null) {
                mainService.saveProfile(profileDAO);
            }
        } else {
            mainService.updateProfile(profileDAO);
        }
        return Constants.Redirects.MY_PROFILE;
    }

    @PostMapping(Constants.Urls.CREDIT_PROFILE)
    public String updateCreditProfile(CreditDAO creditProfileDAO) {
        if (creditProfileDAO.getId()==null) {
            mainService.saveCreditProfile(creditProfileDAO);
        } else {
            mainService.updateCreditProfile(creditProfileDAO);
        }
        return Constants.Redirects.CREDIT_PROFILE;
    }

    @PostMapping(Constants.Urls.SUPPORT)
    public String createSupportMessage(MessageDAO messageDAO) {
        messageDAO.setReadMessage(false);
        mainService.saveMessage(messageDAO);
        return Constants.Redirects.SUPPORT;
    }

    @PostMapping(Constants.Urls.MESSAGE_REPLY)
    public String createMessageReply(ReplyDAO replyDAO, HttpSession httpSession) {
        mainService.saveReply(replyDAO);
        return Constants.Redirects.INBOX;
    }

    @PostMapping(Constants.Urls.ADMIN_USER)
    public String createAdminUser(ProfileDAO profileDAO) {
        if (profileRepository.findByFirstName(profileDAO.getFirstName()) == null &&
                profileRepository.findByLastName(profileDAO.getLastName()) == null &&
                profileRepository.findByDob(profileDAO.getDateOfBirth()) == null) {
            mainService.registerAdmin(profileDAO);
        }
        return Constants.Redirects.ADMIN_USER;
    }
}
