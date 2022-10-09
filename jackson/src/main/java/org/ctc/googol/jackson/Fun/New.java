package org.ctc.googol.jackson.Fun;

import lombok.Data;

import java.util.List;

@Data
public class New extends Base {

    private List<Reason> reasons;

    @Data
    public static class Reason {

        private long appId;
        private double newValue;
        private double oldValue;
    }
}

