package org.ctc.googol.jackson.version2;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

/**
 * @author ctc
 * @date 2019-04-19
 */
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property =
    "type", visible =
    true)
public class BaseElement {

    String id;

    String type;
}

