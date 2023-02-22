package com.ates.rating.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ResourceNotFoundController {

    @RequestMapping(value = "not-found", method = RequestMethod.GET)
    public String resourceNotFound() {
        return "index.html";
    }
}
