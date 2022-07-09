package com.csiindonesia.id.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.math.BigDecimal;

public class modelAbsen implements Serializable {
  private Double latitude;

  private String gambar;

  private String alamat;

  private String unit;

  private String nama;

  private String jam;

  private Integer __v;

  private String guid;

  private String _id;

  private String tanggal;

  private String deskripsi;

  private String create_at;

  private String user;

  private String absen;

  private String instansi;

  private Double longitude;

  public Double getLatitude() {
    return this.latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public String getGambar() {
    return this.gambar;
  }

  public void setGambar(String gambar) {
    this.gambar = gambar;
  }

  public String getAlamat() {
    return this.alamat;
  }

  public void setAlamat(String alamat) {
    this.alamat = alamat;
  }

  public String getUnit() {
    return this.unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public String getNama() {
    return this.nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public String getJam() {
    return this.jam;
  }

  public void setJam(String jam) {
    this.jam = jam;
  }

  public Integer get__v() {
    return this.__v;
  }

  public void set__v(Integer __v) {
    this.__v = __v;
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

  public String getTanggal() {
    return this.tanggal;
  }

  public void setTanggal(String tanggal) {
    this.tanggal = tanggal;
  }

  public String getDeskripsi() {
    return this.deskripsi;
  }

  public void setDeskripsi(String deskripsi) {
    this.deskripsi = deskripsi;
  }

  public String getCreate_at() {
    return this.create_at;
  }

  public void setCreate_at(String create_at) {
    this.create_at = create_at;
  }

  public String getUser() {
    return this.user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getAbsen() {
    return this.absen;
  }

  public void setAbsen(String absen) {
    this.absen = absen;
  }

  public String getInstansi() {
    return this.instansi;
  }

  public void setInstansi(String instansi) {
    this.instansi = instansi;
  }

  public Double getLongitude() {
    return this.longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }
}
