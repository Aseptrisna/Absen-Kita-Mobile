package com.csiindonesia.id.response;

import com.csiindonesia.id.model.modelAbsen;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

public class responseAbsen implements Serializable {
  private List<modelAbsen> data;

  private Integer totalPages;

  private String message;

  private String currentPage;

  private Boolean status;

  public List<modelAbsen> getData() {
    return this.data;
  }

  public void setData(List<modelAbsen> data) {
    this.data = data;
  }

  public Integer getTotalPages() {
    return this.totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getCurrentPage() {
    return this.currentPage;
  }

  public void setCurrentPage(String currentPage) {
    this.currentPage = currentPage;
  }

  public Boolean getStatus() {
    return this.status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

}
