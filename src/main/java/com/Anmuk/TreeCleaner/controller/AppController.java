package com.Anmuk.TreeCleaner.controller;

import com.Anmuk.TreeCleaner.DTO.RiviewDTO;
import com.Anmuk.TreeCleaner.model.Riview;
import com.Anmuk.TreeCleaner.service.RiviewService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



@Controller
public class AppController {

    @Autowired
    private RiviewService riviewService;

    @GetMapping("/")
    public String getAllriview (Model model) {
        model.addAttribute("riview", new Riview());
        List<Riview> riviews = riviewService.findAll();
        List<RiviewDTO> riviewDTOs = riviews.stream().map(riview -> new RiviewDTO(
                riview.getId(),
                riview.getNama(),
                riview.getDeskripsi(),
                riview.getGambar() != null ? Base64Utils.encodeToString(riview.getGambar()) : null
        )).collect(Collectors.toList());
        model.addAttribute("riviews", riviewDTOs);
        return "indexx";
    }

    @PostMapping("/save")
    public String saveRiview(@RequestParam("nama") String nama,

                           @RequestParam("deskripsi") String deskripsi,
                           @RequestParam("gambar") MultipartFile gambar,
                           Model model) {
        Riview riview = new Riview();
        riview.setNama(nama);
        riview.setDeskripsi(deskripsi);
        try {
            riview.setGambar(gambar.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            // handle error
        }
        riviewService.save(riview);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditRiviewForm (@PathVariable("id") Long id, Model model) {
        Riview riview = riviewService.findById(id);
        RiviewDTO riviewDTO = new RiviewDTO(
                riview.getId(),
                riview.getNama(),
                riview.getDeskripsi(),
                riview.getGambar() != null ? Base64Utils.encodeToString(riview.getGambar()) : null
        );
        model.addAttribute("riview", riviewDTO);
        return "riview-edit";
    }

    // @PostMapping("/menu/update/{id}")
    // public String updateMenu(@PathVariable("id") Long id,
    //                          @RequestParam("nama") String nama,
    //                          @RequestParam("harga") Double harga,
    //                          @RequestParam("deskripsi") String deskripsi,
    //                          @RequestParam("gambar") MultipartFile gambar) {
    //     Menu menu = riviewService.findById(id);
    //     menu.setNama(nama);
    //     menu.setHarga(harga);
    //     menu.setDeskripsi(deskripsi);
    //     try {
    //         menu.setGambar(gambar.getBytes());
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //         // handle error
    //     }
    //     riviewService.save(menu);
    //     return "redirect:/menus";
    // }

    @RequestMapping("/delete/{id}")
    public String deleteRiview(@PathVariable Long id) {
        riviewService.deleteRiview(id);
        return "redirect:/";
    }
}
