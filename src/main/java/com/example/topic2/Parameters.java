package com.example.topic2;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Data
@ConfigurationProperties("taco.order")
@Validated
public class Parameters {
    @Min(0)
    @Max(25)
    private int listSize1 = 1;
}
