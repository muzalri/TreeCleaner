package com.Anmuk.TreeCleaner.controller;

import com.Anmuk.TreeCleaner.DTO.RiviewDTO;
import com.Anmuk.TreeCleaner.model.Jasa;
import com.Anmuk.TreeCleaner.model.Riview;
import com.Anmuk.TreeCleaner.model.Sepatu;
import com.Anmuk.TreeCleaner.service.JasaService;
import com.Anmuk.TreeCleaner.service.RiviewService;
import com.Anmuk.TreeCleaner.service.SepatuService;

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
            model.addAttribute("jasas", jasaService.findAll());
            model.addAttribute("sepatus", sepatuService.findAll());
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

        @GetMapping("/custom-login")
         public String login() {
        return "login";
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

    
    
    @Autowired
    private JasaService jasaService;

    @GetMapping("/admin")
    public String getAllJasa(Model model) {
        model.addAttribute("jasas", jasaService.findAll());
        model.addAttribute("sepatus", sepatuService.findAll());
        return "tables";
    }

    @GetMapping("/jasa/new")
    public String createJasaForm(Model model) {
        model.addAttribute("jasa", new Jasa());
        return "formJasa";
    }

    @PostMapping("jasa/new/save")
    public String createOrUpdateJasa(@ModelAttribute Jasa jasa) {
        jasaService.save(jasa);
        return "redirect:/admin";
    }

    @GetMapping("/jasa/edit/{id}")
    public String editJasaForm(@PathVariable Long id, Model model) {
        Jasa jasa = jasaService.findById(id).orElse(new Jasa());
        model.addAttribute("jasa", jasa);
        return "formJasa";
    }

    @GetMapping("/jasa/delete/{id}")
    public String deleteJasa(@PathVariable Long id) {
        jasaService.deleteById(id);
        return "redirect:/admin";
    }



    @Autowired
    private SepatuService sepatuService;

   

    @GetMapping("/sepatu/new")
    public String createSepatuForm(Model model) {
        model.addAttribute("sepatu", new Sepatu());
        return "formSepatu";
    }

    @PostMapping("/sepatu/new/save")
    public String createOrUpdateSepatu(@ModelAttribute Sepatu sepatu) {
        sepatuService.save(sepatu);
        return "redirect:/admin";
    }

    @GetMapping("/sepatu/edit/{id}")
    public String editSepatuForm(@PathVariable Long id, Model model) {
        Sepatu sepatu = sepatuService.findById(id).orElse(new Sepatu());
        model.addAttribute("sepatu", sepatu);
        return "formSepatu";
    }

    @GetMapping("/sepatu/delete/{id}")
    public String deleteSepatu(@PathVariable Long id) {
        sepatuService.deleteById(id);
        return "redirect:/admin";
    }



    @GetMapping("/admin/riview")
        public String getRiview(Model model) {
            List<Riview> riviews = riviewService.findAll();
            List<RiviewDTO> riviewDTOs = riviews.stream().map(riview -> new RiviewDTO(
                    riview.getId(),
                    riview.getNama(),
                    riview.getDeskripsi(),
                    riview.getGambar() != null ? Base64Utils.encodeToString(riview.getGambar()) : null
            )).collect(Collectors.toList());
            model.addAttribute("riviews", riviewDTOs);
            return "riview";
        }


}
