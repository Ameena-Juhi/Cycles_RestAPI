package com.project.RestApi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.RestApi.entity.CartItem;
import com.project.RestApi.entity.Cycle;
import com.project.RestApi.entity.Orders;
import com.project.RestApi.repository.CartRepository;
import com.project.RestApi.repository.CycleRepository;
import com.project.RestApi.repository.OrdersRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CycleRepository repo;

	@Autowired
	private OrdersRepository ordersRepository;

	
	public void addToCart(int id) {
		System.out.println("cartitem received");
		var currentCycleOptional = repo.findById(id);
		if (currentCycleOptional.isEmpty()) {
			System.out.println("Cycle could not be found");
			return; // Return early if the cycle is not found
		}
	
		var currentCycle = currentCycleOptional.get();
		Optional<CartItem> cartItem = cartRepository.findByName(currentCycle.getName());
		if(cartItem.isPresent()){
			cartItem.get().setQuantity(cartItem.get().getQuantity() + 1);
			cartItem.get().setTotal(cartItem.get().getQuantity() * currentCycle.getPrice());
			cartRepository.save(cartItem.get());
		}
		else{
			CartItem newcartItem = new CartItem();
			newcartItem.setName(currentCycle.getName());
			newcartItem.setQuantity(1);
			newcartItem.setTotal(currentCycle.getPrice());
			cartRepository.save(newcartItem);
		}

	}


	public List<CartItem> checkout(){
		List<CartItem> cartItems = cartRepository.findAll();
		
		for (CartItem cartItem : cartItems) {
			
			Optional<Cycle> cycle = repo.findByName(cartItem.getName());
			int quantity = cartItem.getQuantity();
			System.out.println("cart"+ cartItem);
			// Create a new order or update an existing one
			// Orders order = ordersRepository.findByName(cycle.getName()).orElse(null);
			// if (order == null) {
			// 	order = new Orders();
			// 	order.setName(cycle.getName());
			// 	order.setQuantity(quantity);
			// }
			//  else {
			// 	order.setQuantity(order.getQuantity() + quantity);
			// }
			
			// order.setTotalPrice(cycle.getPrice() * quantity);
			// order.setOrderedAt(new Date());
			// ordersRepository.save(order);
			Orders order = new Orders();
			order.setName(cycle.get().getName());
			order.setQuantity(quantity);
			order.setTotalPrice(cycle.get().getPrice() * quantity);
			order.setOrderedAt(new Date());
			ordersRepository.save(order);
			// Update cycle stock
			cycle.get().setStock(cycle.get().getStock() - quantity);
			repo.save(cycle.get());
			cartRepository.delete(cartItem);
		}
		
		// // Clear the cart after processing
		// cartRepository.deleteAll(cartItems);
		return cartRepository.findAll(); // You can return the cleared cart items if needed
	}
	
	// public List<CartItem> checkout(){
	// 	List<CartItem> cartItems = new ArrayList<>();
	// 	cartItems = cartRepository.findAll();
	// 	for (CartItem cartItem: cartItems){
	// 		Cycle cycle = cartItem.getCycle();
	// 		int quantity = cartItem.getQuantity();
    //         cycle.setStock(cycle.getStock() - quantity);
    //         repo.save(cycle);

	// 		Orders order = ordersRepository.findByCycle(cycle).orElseGet(() -> {
    //             Orders newOrder = new Orders();
    //             newOrder.setCycle(cycle);
    //             newOrder.setQuantity(quantity);
    //             return newOrder;
    //         });
    //         ordersRepository.save(order);
	// 	}
	// 	cartItems.clear();
	// 	return cartItems;
			
	// }
}
	
// public Cart checkoutAll() {
    //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //     User user = userRepository.findByName(authentication.getName()).get();
    //     Cart cart = cartRepository.findByUser(user).orElseGet(Cart::new);

    //     for (CartItem cartItem : cart.getCartItems()) {
    //         Cycle cycle = cartItem.getCycle();
    //         int quantity = cartItem.getQuantity();
    //         cycle.setStock(cycle.getStock() - quantity);
    //         cycleRepository.save(cycle);

    //         Order order = orderRepository.findByUserAndCycle(user, cycle).orElseGet(() -> {
    //             Order newOrder = new Order();
    //             newOrder.setUser(user);
    //             newOrder.setCycle(cycle);
    //             newOrder.setQuantity(quantity);
    //             return newOrder;
    //         });
    //         orderRepository.save(order);
    //     }

    //     cart.getCartItems().clear();
    //     return cart;
    // }
