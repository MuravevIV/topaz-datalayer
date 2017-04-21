package com.ilyamur.topaz.datalayer.webapp;

public class Constants {

    public static class Param {

        public static final String ID = "id";
        public static final String USERS = "users";
        public static final String USER_LOGIN = "userLogin";
        public static final String EMAIL_TEXT = "emailText";
    }

    public static class Path {

        public static final String ROOT = "/";

        public static final String USERS = "/users";
        public static final String USERS_EMAIL_SEND = "/users/email/send";
        public static final String USERS_EMAIL_REPORT = "/users/email/report";
    }

    public static class Template {

        public static final String INDEX = "/index";

        public static final String USERS = "/users";
        public static final String USERS_EMAIL_REPORT = "/users/email/report";

        public static final String PAGE_NOT_FOUND = "/404";
        public static final String SERVER_ERROR = "/500";
    }
}
