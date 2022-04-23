package com.example.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.model.VideoGame;
import com.example.springboot.repository.VideoGameRepository;

@RestController
@RequestMapping("/api")
public class VideoGameController {
	
	@Autowired
	VideoGameRepository vRepo; 
	
	@GetMapping("/videogames")
	public ResponseEntity<List<VideoGame>> getAllVideoGames(){
		
		Optional<List<VideoGame>> getAll = Optional.ofNullable(vRepo.findAll());
		
		if(getAll.isPresent()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(getAll.get());
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
		
	}
	
	@GetMapping("/videogames/{id}")
	public ResponseEntity<Object> getVideoGameById(@PathVariable Long id){
		
		Optional<VideoGame> found = vRepo.findById(id);
		if(found.isPresent()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(found.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video Game Not Found by ID: " + id);
	}
	
	@GetMapping("/videogames/publishers/{publisher}")
	public ResponseEntity<Object> getVideoGameByPublisher(@PathVariable String publisher){
		
		List<VideoGame> found = vRepo.findByPublisher(publisher);
		
		if(found != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(found);
		}
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video Game Not Found by Publisher: " + publisher);
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> addVideoGame(@RequestBody VideoGame videoGame){
		
		VideoGame newVideoGame = vRepo.save(videoGame);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("New Entry Created: " + newVideoGame.toString());
		
	}
	
	@DeleteMapping("/delete/videogames/{id}")
	public ResponseEntity<Object> deleteVideoGameById(@PathVariable Long id){
		
		Optional<VideoGame> found = vRepo.findById(id);
		
		if(found.isPresent()) {
			vRepo.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Video Game Found and Deleted."
					+ " " + found.toString());
			
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sorry but entered Id does not exist!");
			
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateVideoGame(@RequestBody VideoGame videoGame){
		
		Optional<VideoGame> found = vRepo.findById(videoGame.getId());
		
		if(found.isPresent()) {
			vRepo.save(videoGame);
			return ResponseEntity.status(HttpStatus.OK).body(found.toString());
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't update the video game since it cannot be found");
	}
}
