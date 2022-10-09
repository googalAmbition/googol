package org.ctc.googol.jackson.version2;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.ToString;

/**
 * @author ctc
 * @date 2019-04-19
 */
@ToString(callSuper = true)
@Data
@JsonTypeName(value = "endEvent")
public class EndEvent extends BaseElement {

    private String endEvent;
    private String endInfo;
}
