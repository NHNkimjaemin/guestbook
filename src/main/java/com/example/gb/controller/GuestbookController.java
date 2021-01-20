package com.example.gb.controller;


import com.example.gb.dto.PageRequestDTO;
import com.example.gb.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookService service;

    @GetMapping("/")
    public String index(){

        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list. .. .. . . ... .. . . .. " + pageRequestDTO);

        //이 부분이 타임리프 html에 패러미터를 넘겨주는 부분
        model.addAttribute("result", service.getList(pageRequestDTO));
    }
}
