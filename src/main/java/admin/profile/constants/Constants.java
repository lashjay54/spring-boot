package admin.profile.constants;

public class Constants {

    public static class Urls {
        public static final String LOGIN = "/login";
        public static final String LOG_OUT = "/logout";
        public static final String REGISTER = "/register";
        public static final String HOME = "/dashboard";
        public static final String MY_PROFILE = "/profile";
        public static final String DELETE_MY_PROFILE = "/delete-profile";
        public static final String CREDIT_PROFILE = "/credit-profile";
        public static final String DELETE_CREDIT_PROFILE = "/delete-credit-profile";
        public static final String INBOX = "/inbox";
        public static final String USERS = "/users";
        public static final String SUPPORT = "/support";
        public static final String DELETE_PERSON = "/delete-person";
        public static final String DELETE_MESSAGE = "/delete-message";
        public static final String MESSAGE_REPLY = "/message-reply";
        public static final String ADMIN_USER = "/administrator";
    }

    public static class Views {
        public static final String LOGIN = "login";
        public static final String REGISTER = "register";
        public static final String HOME = "dashboard";
        public static final String MY_PROFILE = "userProfile";
        public static final String CREDIT_PROFILE = "credits";
        public static final String INBOX = "clientInbox";
        public static final String ADMIN_INBOX = "administratorInbox";
        public static final String SUPPORT = "support";
        public static final String USERS = "allUsers";
        public static final String MESSAGE_REPLY = "administratorReply";
        public static final String ADMIN_USER = "administratorSupport";
    }

    public static class Redirects {
        private static final String REDIRECT = "redirect:";
        public static final String LOGIN = REDIRECT + Urls.LOGIN;
        public static final String MY_PROFILE = REDIRECT + Urls.MY_PROFILE;
        public static final String CREDIT_PROFILE = REDIRECT + Urls.CREDIT_PROFILE;
        public static final String INBOX = REDIRECT + Urls.INBOX;
        public static final String SUPPORT = REDIRECT + Urls.SUPPORT;
        public static final String USERS = REDIRECT + Urls.USERS;
        public static final String ADMIN_USER = REDIRECT + Urls.ADMIN_USER;
        public static final String REGISTER = REDIRECT + Urls.REGISTER;
    }

}
