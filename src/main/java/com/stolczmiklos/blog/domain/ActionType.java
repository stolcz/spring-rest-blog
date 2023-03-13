package com.stolczmiklos.blog.domain;

public enum ActionType {
    REGISTRATION("\n\nÖn éppen most regisztrált a Blogba." +
            " \nKérem igazolja vissza regisztrációs szándékát a lenti linkre kattintva:\n\n",
            "http://localhost:3000/confirmRegistration?token=",
            "Regisztráció érvényesítése",
            "\n\n\nÜdvözlettel:\nBlog Üzemeltetők");

    private String mailMainText;
    private String baseUrl;
    private String emailSubject;
    private String mailSignature;

    ActionType(String mailMainText, String baseUrl, String emailSubject, String mailSignature) {
        this.mailMainText = mailMainText;
        this.baseUrl = baseUrl;
        this.emailSubject = emailSubject;
        this.mailSignature = mailSignature;
    }

    public String getMailMainText() {
        return mailMainText;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public String getMailSignature() {
        return mailSignature;
    }
}
