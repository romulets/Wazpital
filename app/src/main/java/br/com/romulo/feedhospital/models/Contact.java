package br.com.romulo.feedhospital.models;

import java.io.Serializable;

/**
 * Created by romul_000 on 25/03/2016.
 */
public class Contact implements Serializable{

    public static final String PHONE = "phone";
    public static final String EMAIL = "email";
    public static final String SITE = "site";

    private String type;
    private String content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
