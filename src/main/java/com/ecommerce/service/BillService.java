package com.ecommerce.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Bill;
import com.ecommerce.model.Product;
import com.ecommerce.repositories.BillRepository;
import com.ecommerce.repositories.GstRateRepository;
import com.ecommerce.repositories.ProductRepository;

@Service
public class BillService {

	@Autowired
	private BillRepository billRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private GstRateRepository gstRateRepository;

	public List<Bill> getBillsByDate(LocalDate date) {
		return billRepository.findByDate(date);
	}

	public List<Bill> getBillsForDateRange(LocalDate startDate, LocalDate endDate) {
		return billRepository.findByDateBetween(startDate, endDate);
	}

	public double getTotalRevenueForDate(LocalDate date) {
		return billRepository.findByDate(date).stream().mapToDouble(Bill::getAmount).sum();
	}

	public double getTotalRevenueForDateRange(LocalDate startDate, LocalDate endDate) {
		return billRepository.findByDateBetween(startDate, endDate).stream().mapToDouble(Bill::getAmount).sum();
	}

	public double getTotalRevenue() {
		return billRepository.findAll().stream().mapToDouble(Bill::getAmount).sum();
	}

	public long getTotalBillsCount() {
		return billRepository.count();
	}

	public Bill createBill(Bill bill) {
		double totalAmount = bill.getItems().stream().mapToDouble(item -> {
			Product product = productRepository.findByName(item.getProductName())
					.orElseThrow(() -> new RuntimeException("Product not found"));
			double productPrice = product.getPrice();
			double gstRate = gstRateRepository.findByCategory(product.getCategory()).map(gst -> gst.getPercentage())
					.orElse(0.0);
			double taxAmount = (productPrice * item.getQuantity()) * (gstRate / 100);
			return (productPrice * item.getQuantity()) + taxAmount;
		}).sum();
		bill.setAmount(totalAmount);
		return billRepository.save(bill);
	}
}
