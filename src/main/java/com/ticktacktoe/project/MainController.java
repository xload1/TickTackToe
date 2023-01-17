package com.ticktacktoe.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    Service service;

    @Autowired
    public MainController(Service service) {
        this.service = service;
    }

    @GetMapping("/ticktacktoe")
    public String ticktacktoe(Model model) {
        String message = service.check();
        model.addAttribute("a11", service.getBoard()[0][0]);
        model.addAttribute("a12", service.getBoard()[0][1]);
        model.addAttribute("a13", service.getBoard()[0][2]);
        model.addAttribute("a21", service.getBoard()[1][0]);
        model.addAttribute("a22", service.getBoard()[1][1]);
        model.addAttribute("a23", service.getBoard()[1][2]);
        model.addAttribute("a31", service.getBoard()[2][0]);
        model.addAttribute("a32", service.getBoard()[2][1]);
        model.addAttribute("a33", service.getBoard()[2][2]);
        model.addAttribute("check", message);
        if (message.equals("") && service.isFull()) {
            model.addAttribute("check", "DRAW");
        }
        return "ticktacktoe";
    }

    @PostMapping("/ticktacktoe/change")
    public String change(@RequestParam String a) {
        if(!service.check().equals("")) return "redirect:/ticktacktoe";
        int first = Character.getNumericValue(a.toCharArray()[0]);
        int second = Character.getNumericValue(a.toCharArray()[1]);
        if (service.getBoard()[first][second] == ' ') {
            if (service.getTurns() % 2 == 0) {
                service.getBoard()[first][second] = 'X';
            } else {
                service.getBoard()[first][second] = 'O';
            }
            service.setTurns(service.getTurns() + 1);
        }
        return "redirect:/ticktacktoe";
    }

    @PostMapping("ticktacktoe/clear")
    public String clear() {
        for (int i = 0; i < service.getBoard().length; i++) {
            for (int j = 0; j < service.getBoard()[1].length; j++) {
                service.getBoard()[i][j] = ' ';
            }
        }
        service.setTurns(0);
        return "redirect:/ticktacktoe";
    }
}
