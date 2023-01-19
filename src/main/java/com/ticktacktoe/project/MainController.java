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
        model.addAttribute("a11", service.board[0][0]);
        model.addAttribute("a12", service.board[0][1]);
        model.addAttribute("a13", service.board[0][2]);
        model.addAttribute("a21", service.board[1][0]);
        model.addAttribute("a22", service.board[1][1]);
        model.addAttribute("a23", service.board[1][2]);
        model.addAttribute("a31", service.board[2][0]);
        model.addAttribute("a32", service.board[2][1]);
        model.addAttribute("a33", service.board[2][2]);
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
        if (service.board[first][second] == ' ') {
            if (service.turns % 2 == 0) {
                service.board[first][second] = 'X';
            } else {
                service.board[first][second] = 'O';
            }
            service.turns++;
        }
        return "redirect:/ticktacktoe";
    }

    @PostMapping("ticktacktoe/clear")
    public String clear() {
        for (int i = 0; i < service.board.length; i++) {
            for (int j = 0; j < service.board[1].length; j++) {
                service.board[i][j] = ' ';
            }
        }
        service.turns=0;
        return "redirect:/ticktacktoe";
    }
}
