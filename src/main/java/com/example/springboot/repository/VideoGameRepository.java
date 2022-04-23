package com.example.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.model.VideoGame;

@Repository
public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {
	
	//return games from the same publisher in the database. 
	List<VideoGame> findByPublisher(String publisher);
	
	

	
}
