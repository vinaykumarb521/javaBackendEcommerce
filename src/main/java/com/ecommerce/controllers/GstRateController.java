package com.ecommerce.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.CategoryGstRateDto;
import com.ecommerce.model.GstRate;
import com.ecommerce.service.GstRateService;

@RestController
@RequestMapping("/api/gstRate")
public class GstRateController {

    @Autowired
    private GstRateService gstRateService;

    @PostMapping("/set")
    public ResponseEntity<String> setGstRate(@RequestBody GstRate gstRate) {
        gstRateService.saveOrUpdateGstRate(gstRate);
        return new ResponseEntity<>("GST rate set successfully", HttpStatus.CREATED);
    }
    

    @GetMapping("/get/{category}")
    public ResponseEntity<GstRate> getGstRateByProduct(@PathVariable String category) {
        Optional<GstRate> gstRate = gstRateService.getGstRateByCategory(category);
        if (gstRate.isPresent()) {
            return new ResponseEntity<>(gstRate.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryGstRateDto>> getAllGstRateByProduct() {
        List<GstRate> gstRate = gstRateService.getAllGstRateByProduct();
        if (gstRate != null) {
            return new ResponseEntity<>(gstRate.stream()
                    .map(e -> new CategoryGstRateDto(e.getCategory(), e.getPercentage())).collect(Collectors.toList()),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
