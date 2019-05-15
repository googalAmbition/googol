package org.ctc.googol.springboot.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ValidateDemo {

  @NotBlank(groups = UpdateGroup.class, message = "The id can not be empty")
  private String id;
  @NotBlank(groups = {SaveGroup.class, UpdateGroup.class}, message = "The name can not empty")
  private String name;

  public interface SaveGroup {
  }

  public interface UpdateGroup {
  }

}
