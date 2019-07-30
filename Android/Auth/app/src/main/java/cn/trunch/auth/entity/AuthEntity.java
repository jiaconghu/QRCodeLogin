package cn.trunch.auth.entity;


import java.io.Serializable;
import java.sql.Timestamp;

public class AuthEntity implements Serializable {

  private String authToken;
  private Timestamp authTime;
  private String authIp;
  private String authAddress;
  private Integer authState;
  private long userId;


  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken = authToken;
  }


  public Timestamp getAuthTime() {
    return authTime;
  }

  public void setAuthTime(Timestamp authTime) {
    this.authTime = authTime;
  }


  public String getAuthIp() {
    return authIp;
  }

  public void setAuthIp(String authIp) {
    this.authIp = authIp;
  }


  public String getAuthAddress() {
    return authAddress;
  }

  public void setAuthAddress(String authAddress) {
    this.authAddress = authAddress;
  }


  public Integer getAuthState() {
    return authState;
  }

  public void setAuthState(Integer authState) {
    this.authState = authState;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

}
