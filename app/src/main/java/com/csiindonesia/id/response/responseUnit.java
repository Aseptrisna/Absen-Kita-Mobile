package com.csiindonesia.id.response;

import com.csiindonesia.id.model.modelUnits;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

public class responseUnit implements Serializable {
  private List<modelUnits> data;

  private String message;

  private Boolean status;

  public List<modelUnits> getData() {
    return this.data;
  }

  public void setData(List<modelUnits> data) {
    this.data = data;
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
