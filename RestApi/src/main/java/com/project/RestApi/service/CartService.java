package com.project.RestApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.RestApi.entity.Cart;
import com.project.RestApi.entity.Cycle;
import com.project.RestApi.repository.CartRepository;
import com.project.RestApi.repository.CycleRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CycleRepository repo;

	
	public void listCycles() {
		cartRepository.findAll();
	}
	
	public void addToCart(Cart cart,int id) {
		var currentCycleOptional = repo.findById(id);
		if (currentCycleOptional.isEmpty()) {
			//TODO: deal with the cycle not being found for whatever reason
			System.out.println("cycle could not be found");
		}
		var currentCycle = currentCycleOptional.get();
		int currentstock = repo.findById(id).get().getStock();
		currentCycle.setStock(currentstock - 1);
		repo.save(currentCycle);
		cart.setName(currentCycle.getName());
		cart.setPrice(currentCycle.getPrice());
		if (cart.getQuantity()!=0)
			cart.setQuantity(cart.getQuantity()+1);
		else
			cart.setQuantity(1);
		cartRepository.save(cart);
		
	}
}
