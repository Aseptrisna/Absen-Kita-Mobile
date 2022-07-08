package com.csiindonesia.id.response;

import java.io.Serializable;
import java.lang.String;

public class responseFile implements Serializable {
  private String filename;

  private String message;

  private Boolean status;

  public String getFilename() {
    return this.filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
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
