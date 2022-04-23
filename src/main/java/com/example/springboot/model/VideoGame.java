package com.example.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class VideoGame {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id; 
	
	@Column(nullable=false)
	private String game_name;
	
	@Column(columnDefinition="varchar(50) default 'Undecided' ")
	private String genre; 
	
	@Column(name="publisher", columnDefinition="varchar(30) default 'Unknown' ")
	private String publisher; 
	
	
	public VideoGame() {
		this(-1L, "N/A", "Undecided", "Unknown");
	}

	
	public VideoGame(long id, String game_name, String genre, String publisher) {
		super();
		this.id = id;
		this.game_name = game_name;
		this.genre = genre;
		this.publisher = publisher;
	}



	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getGame_name() {
		return game_name;
	}


	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	@Override
	public String toString() {
		return "VideoGame [id=" + id + ", game_name=" + game_name + ", genre=" + genre + ", publisher=" + publisher
				+ "]";
	}
	
	
	
	
	

}
