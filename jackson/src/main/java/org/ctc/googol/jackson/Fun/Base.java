package org.ctc.googol.jackson.Fun;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "targetType",
    visible = true,
    defaultImpl = Old.class

)
@JsonSubTypes(
    {
        // @JsonSubTypes.Type(value = Old.class, name = "100"),
        // @JsonSubTypes.Type(value = Old.class, name = "200"),
        // @JsonSubTypes.Type(value = Old.class, name = "201"),
        // @JsonSubTypes.Type(value = Old.class, name = "202"),
        // @JsonSubTypes.Type(value = Old.class, name = "300"),
        // @JsonSubTypes.Type(value = Old.class, name = "301"),
        // @JsonSubTypes.Type(value = Old.class, name = "302"),
        // @JsonSubTypes.Type(value = Old.class, name = "303"),
        // @JsonSubTypes.Type(value = Old.class, name = "304"),
        // @JsonSubTypes.Type(value = Old.class, name = "400"),
        // @JsonSubTypes.Type(value = Old.class, name = "500"),
        // @JsonSubTypes.Type(value = Old.class, name = "501"),
        // @JsonSubTypes.Type(value = Old.class, name = "502"),
        // @JsonSubTypes.Type(value = Old.class, name = "600"),
        // @JsonSubTypes.Type(value = Old.class, name = "700"),

        // old  defaultImpl = Old.class替换
        @JsonSubTypes.Type(value = New.class, name = "800")
    }
)
public class Base {

    private int targetType;
}

