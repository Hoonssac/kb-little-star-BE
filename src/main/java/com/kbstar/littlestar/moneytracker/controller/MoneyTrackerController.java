package com.kbstar.littlestar.moneytracker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kbstar.littlestar.moneytracker.dto.CategoryDto;
import com.kbstar.littlestar.moneytracker.dto.TransactionDto;
import com.kbstar.littlestar.moneytracker.service.MoneyTrackerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MoneyTrackerController {

	private final MoneyTrackerService service;

	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDto>> getCategories(@RequestParam("user_id") int userId) {
		List<CategoryDto> categories = service.getCategories(userId);
		return ResponseEntity.ok(categories);
	}

	@PostMapping("/categories")
	public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto category) {
		CategoryDto newCategory = service.addCategory(category);
		return ResponseEntity.ok(newCategory);
	}

	@GetMapping("/transactions")
	public ResponseEntity<List<TransactionDto>> getTransactions(@RequestParam("user_id") int userId) {
		List<TransactionDto> transactions = service.getTransactions(userId);
		return ResponseEntity.ok(transactions);
	}

	@PostMapping("/transactions")
	public ResponseEntity<TransactionDto> addTransaction(@RequestBody TransactionDto transaction) {
		TransactionDto newTransaction = service.addTransaction(transaction);
		return ResponseEntity.ok(newTransaction);
	}

	@PutMapping("/transactions/{transactionId}")
	public ResponseEntity<TransactionDto> editTransaction(@PathVariable int transactionId,
		@RequestBody TransactionDto transaction) {
		TransactionDto updatedTransaction = service.editTransaction(transactionId, transaction);
		return ResponseEntity.ok(updatedTransaction);
	}

	@DeleteMapping("/transactions/{transactionId}")
	public ResponseEntity<Void> deleteTransaction(@PathVariable int transactionId) {
		service.deleteTransaction(transactionId);
		return ResponseEntity.ok().build();
	}
}
