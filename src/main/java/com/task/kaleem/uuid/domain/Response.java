package com.task.kaleem.uuid.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

  @JsonProperty("Sequence")
  private String uuidSequence;

  public String getUuidSequence() {
    return uuidSequence;
  }

  public void setUuidSequence(String uuidSequence) {
    this.uuidSequence = uuidSequence;
  }

  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof Response)) {
      return false;
    }
    Response response = (Response) other;
    return Objects.equals(uuidSequence, response.uuidSequence);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuidSequence);
  }
}
