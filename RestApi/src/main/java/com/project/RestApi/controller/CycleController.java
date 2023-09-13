package com.project.RestApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.RestApi.repository.CycleRepository;
import com.project.RestApi.service.CycleService;

import com.project.RestApi.entity.Cycle;

@RestController
@RequestMapping("/cycle")
public class CycleController {
	
	@Autowired
	private CycleRepository cycleRepository;
	
	@Autowired
	private CycleService cycleService;
	
	@GetMapping("/cycles")
	@ResponseBody
	public List<Cycle> all(){
		return cycleRepository.findAll();
	}
	
	@PostMapping("/add")
	@ResponseBody
	public Cycle newCycle(@RequestBody Cycle cycle) {
		return cycleRepository.save(cycle);
	}
	
	@GetMapping("/borrow/{id}")
	public String borrowCycle(@PathVariable("id") int id) {
		cycleService.borrow(id);
		return "Successfully borrowed!";
		
	}
	
	@GetMapping("/return/{id}")
	public String returnCycle(@PathVariable("id") int id) {
		cycleService.returns(id);
		return "Successfully returned!";
	}
	
	@PostMapping("/restock/{id}")
	public String restock(@PathVariable("id") int id) {
		cycleService.get(id);
		cycleService.save(cycle);
		return "Success!";
	}
	
	@GetMapping("/delete/{id}")
	public String del(@PathVariable("id") int id) {
		cycleRepository.deleteById(id);
		return "Done";
	}

}
