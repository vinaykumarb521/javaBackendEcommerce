package com.ecommerce.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.service.BillService;

@RestController
@RequestMapping("/api/summary")
public class SummaryController {

    @Autowired
    private BillService billService;

    @GetMapping("/total-revenue")
    public ResponseEntity<Double> getTotalRevenueSummary() {
        double totalRevenue = billService.getTotalRevenue();
        return new ResponseEntity<>(totalRevenue, HttpStatus.OK);
    }
    
    @GetMapping("/total-bills")
    public ResponseEntity<Long> getTotalBillsCount() {
        long totalBills = billService.getTotalBillsCount();
        return new ResponseEntity<>(totalBills, HttpStatus.OK);
    }

    @GetMapping("/revenue-by-date")
    public ResponseEntity<Double> getTotalRevenueByDate(@RequestParam("date") LocalDate date) {
        double totalRevenue = billService.getTotalRevenueForDate(date);
        return new ResponseEntity<>(totalRevenue, HttpStatus.OK);
    }

    @GetMapping("/revenue-by-date-range")
    public ResponseEntity<Double> getTotalRevenueByDateRange(@RequestParam("startDate") LocalDate startDate,
                                                             @RequestParam("endDate") LocalDate endDate) {
        double totalRevenue = billService.getTotalRevenueForDateRange(startDate, endDate);
        return new ResponseEntity<>(totalRevenue, HttpStatus.OK);
    }

    @GetMapping("/revenue-by-month")
    public ResponseEntity<Double> getTotalRevenueByMonth(@RequestParam("year") int year, @RequestParam("month") int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        double totalRevenue = billService.getTotalRevenueForDateRange(startDate, endDate);
        return new ResponseEntity<>(totalRevenue, HttpStatus.OK);
    }

    @GetMapping("/revenue-by-year")
    public ResponseEntity<Double> getTotalRevenueByYear(@RequestParam("year") int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        double totalRevenue = billService.getTotalRevenueForDateRange(startDate, endDate);
        return new ResponseEntity<>(totalRevenue, HttpStatus.OK);
    }

    @GetMapping("/revenue-this-month")
    public ResponseEntity<Double> getTotalRevenueThisMonth() {
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(1);
        LocalDate endDate = now.withDayOfMonth(now.lengthOfMonth());
        double totalRevenue = billService.getTotalRevenueForDateRange(startDate, endDate);
        return new ResponseEntity<>(totalRevenue, HttpStatus.OK);
    }

    @GetMapping("/revenue-this-year")
    public ResponseEntity<Double> getTotalRevenueThisYear() {
        LocalDate now = LocalDate.now();
        LocalDate startDate = LocalDate.of(now.getYear(), 1, 1);
        LocalDate endDate = LocalDate.of(now.getYear(), 12, 31);
        double totalRevenue = billService.getTotalRevenueForDateRange(startDate, endDate);
        return new ResponseEntity<>(totalRevenue, HttpStatus.OK);
    }
    
    @GetMapping("/revenue-today")
    public ResponseEntity<Double> getTotalRevenueToday() {
    
        LocalDate today = LocalDate.now();
        double totalRevenue = billService.getTotalRevenueForDate(today);
    	System.out.println(" todays " + totalRevenue);
        return new ResponseEntity<>(totalRevenue, HttpStatus.OK);
    }

}
