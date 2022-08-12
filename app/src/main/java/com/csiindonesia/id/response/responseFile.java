package com.csiindonesia.id.response;

import java.io.Serializable;
import java.lang.String;

public class responseFile implements Serializable {
  private String imagename;

  private String message;

  private Boolean status;

  public String getImagename() {
    return this.imagename;
  }

  public void setImagename(String imagename) {
    this.imagename = imagename;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Boolean getStatus() {
    return this.status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }
}
