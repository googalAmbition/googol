package org.ctc.googol.javabase.equal;

import org.junit.Test;

public class LongEqual {

    String string = "   {\n" +
        "            \"element_type\": \"service_task\", \n" +
        "            \"id\": 17, \n" +
        "            \"isEditing\": false, \n" +
        "            \"defineText\": \"action\", \n" +
        "            \"text\": \"Add a Task\", \n" +
        "            \"noteText\": \"\", \n" +
        "            \"nodeDepth\": 1, \n" +
        "            \"iconType\": \"icon-task_add action\", \n" +
        "            \"fieldExtensions\": [\n" +
        "                {\n" +
        "                    \"element_type\": \"field_extension\", \n" +
        "                    \"fieldName\": \"taskId\", \n" +
        "                    \"stringValue\": \"76\"\n" +
        "                }\n" +
        "            ], \n" +
        "            \"implementation\": \"add_task\"\n" +
        "        }";

    @Test
    public void newAndNew() {
        Long long1_1 = 12L;
        long long2_2 = 12L;
        Long long3_3 = new Long(12L);
    }
}
