package go.ti.goItHW12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {

@GetMapping
    public ModelAndView hello(){


    return new ModelAndView("test/testTemplate");

    }

}
