package com.kbstar.littlestar.moneytracker.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kbstar.littlestar.moneytracker.dto.CategoryDto;
import com.kbstar.littlestar.moneytracker.dto.TransactionDto;

@Mapper
public interface MoneyTrackerMapper {

	List<CategoryDto> getCategories(int userId);

	void addCategory(CategoryDto category);

	List<TransactionDto> getTransactions(int userId);

	void addTransaction(TransactionDto transaction);

	void editTransaction(TransactionDto transaction);

	void deleteTransaction(int transactionId);
}
