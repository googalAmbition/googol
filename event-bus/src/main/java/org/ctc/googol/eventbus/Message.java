package org.ctc.googol.eventbus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.junit.Test;

/**
 * @author ctc on 2019/4/13
 */
@Data
@Builder
public class Message {

  private String name;

  private String content;

  private Type type;

  @AllArgsConstructor
  public enum Type {
    CREATE(1, "create data"),
    READ(1, "read data"),
    UPDATE(1, "update"),
    DELETE(1, "delete data");

    @Getter
    private Integer value;
    @Getter
    private String describe;

  }
}
