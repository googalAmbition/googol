package org.ctc.googol.jackson.version1;

import lombok.Data;
import lombok.ToString;

/**
 * @author ctc
 * @date 2019-04-19
 */
@ToString(callSuper = true)
@Data
public class StartEvent extends BaseElement {

    private String startEvent;

    private String startInfo;
}
