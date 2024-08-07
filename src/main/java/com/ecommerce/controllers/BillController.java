package com.ecommerce.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Bill;
import com.ecommerce.service.BillService;

@RestController
@RequestMapping("/api/bills")
public class BillController {

	@Autowired
	private BillService billService;
//    @Autowired
//    private UserService userService;
    
    @PostMapping("/create")
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
//        if (userService.findByUsername(bill.getBuyerName()) == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

        Bill createdBill = billService.createBill(bill);
        return new ResponseEntity<>(createdBill, HttpStatus.CREATED);
    }
    
	@GetMapping("/date")
	public ResponseEntity<List<Bill>> getBillsByDate(@RequestParam("date") LocalDate date) {
		List<Bill> bills = billService.getBillsByDate(date);
		return new ResponseEntity<>(bills, HttpStatus.OK);
	}

	@GetMapping("/date-range")
	public ResponseEntity<List<Bill>> getBillsForDateRange(@RequestParam("startDate") LocalDate startDate,
			@RequestParam("endDate") LocalDate endDate) {
		List<Bill> bills = billService.getBillsForDateRange(startDate, endDate);
		return new ResponseEntity<>(bills, HttpStatus.OK);
	}

	@GetMapping("/total-revenue/date")
	public ResponseEntity<Double> getTotalRevenueForDate(@RequestParam("date") LocalDate date) {
		double totalRevenue = billService.getTotalRevenueForDate(date);
		return new ResponseEntity<>(totalRevenue, HttpStatus.OK);
	}

	@GetMapping("/total-revenue/date-range")
	public ResponseEntity<Double> getTotalRevenueForDateRange(@RequestParam("startDate") LocalDate startDate,
			@RequestParam("endDate") LocalDate endDate) {
		double totalRevenue = billService.getTotalRevenueForDateRange(startDate, endDate);
		return new ResponseEntity<>(totalRevenue, HttpStatus.OK);
	}
}
