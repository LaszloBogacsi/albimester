package com.laszlobogacsi.albimester.controller;

import com.laszlobogacsi.albimester.bills.elmu.ElmuBill;
import com.laszlobogacsi.albimester.parser.BillParserFactory;
import com.laszlobogacsi.albimester.parser.ElmuBillParser;
import com.laszlobogacsi.albimester.parser.XMLParserService;
import com.laszlobogacsi.albimester.persistance.BillData;
import com.laszlobogacsi.albimester.persistance.BillTransformer;
import com.laszlobogacsi.albimester.persistance.BillType;
import com.laszlobogacsi.albimester.upload.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class BillUploadController {
    @Autowired
    BillService billService;
    @Autowired
    XMLParserService parserService;

    @RequestMapping(value = "/upload-bill", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ResponseEntity<Object> uploadBill(@RequestParam(value = "data") List<MultipartFile> files, @RequestParam(value = "type") BillType billType) {
        files.forEach(file -> System.out.println(file.getOriginalFilename()));
        files.forEach(file -> {
            try(final InputStream inputStream = file.getInputStream()) {
                billService.save(new BillData(billType, BillTransformer.transformToJson(parserService.parseInput(inputStream, BillParserFactory.getParser(billType)))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return ResponseEntity.accepted().build();
    }
}
