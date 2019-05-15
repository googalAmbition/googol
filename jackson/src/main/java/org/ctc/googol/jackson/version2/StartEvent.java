package org.ctc.googol.jackson.version2;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * @author ctc
 * @date 2019-04-19
 */
@ToString(callSuper = true)
@Data
@JsonTypeName(value = "startEvent")
public class StartEvent extends BaseElement {

  private String startEvent;

  private String startInfo;

  private Boolean isSuccess;

  private List<String> list;

}
