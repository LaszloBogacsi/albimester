package com.laszlobogacsi.albimester.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class BillUploadController {

    @RequestMapping(value = "/upload-bill", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ResponseEntity<Object> uploadBill(@RequestParam(value = "data", required = true) List<MultipartFile> files) {

        return ResponseEntity.accepted().build();
    }
}
