package org.ctc.googol.springboot.controller;

import org.ctc.googol.springboot.model.ValidateDemo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ctc
 */
@Slf4j
@RestController("/validateDemo")
public class ValidateDemoController {

  @RequestMapping(value = "/save")
  public ValidateDemo save(@RequestParam("validateDemo") @Validated(ValidateDemo.SaveGroup.class) ValidateDemo validateDemo) {
    log.info("validate is {}", validateDemo);
    return validateDemo;
  }

}
