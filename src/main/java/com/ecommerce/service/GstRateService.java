package com.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.GstRate;
import com.ecommerce.repositories.GstRateRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GstRateService {

    @Autowired
    private GstRateRepository gstRateRepository;

    public GstRate saveOrUpdateGstRate(GstRate gstRate) {
        Optional<GstRate> existingGstRate = gstRateRepository.findByCategory(gstRate.getCategory());

        if (existingGstRate.isPresent()) {
            GstRate updatedGstRate = existingGstRate.get();
            updatedGstRate.setPercentage(gstRate.getPercentage());
            return gstRateRepository.save(updatedGstRate);
        } else {
            return gstRateRepository.save(gstRate);
        }
    }

    public Optional<GstRate> getGstRateByCategory(String category) {
        return gstRateRepository.findByCategory(category);
    }

    public List<GstRate> getAllGstRateByProduct() {
        return gstRateRepository.findAll();
    }
}
