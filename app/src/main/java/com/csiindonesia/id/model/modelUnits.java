package com.csiindonesia.id.model;

import java.io.Serializable;

public class modelUnits implements Serializable {
    private String acces_token;

    private Integer __v;

    private String name;

    private String package_name;

    private String guid;

    private String _id;

    private String client_secret;

    private String type;

    private String create_at;

    private String client_id;

    private String instansi;

    public String getAcces_token() {
        return this.acces_token;
    }

    public void setAcces_token(String acces_token) {
        this.acces_token = acces_token;
    }

    public Integer get__v() {
        return this.__v;
    }

    public void set__v(Integer __v) {
        this.__v = __v;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackage_name() {
        return this.package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String get_id() {
        return this._id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getClient_secret() {
        return this.client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreate_at() {
        return this.create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getClient_id() {
        return this.client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getInstansi() {
        return this.instansi;
    }

    public void setInstansi(String instansi) {
        this.instansi = instansi;
    }
}