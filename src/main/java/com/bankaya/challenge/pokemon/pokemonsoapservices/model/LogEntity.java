package com.bankaya.challenge.pokemon.pokemonsoapservices.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TABITACORALOGS")
@Data
public class LogEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String ipOrigin;
	private LocalDateTime dateRequest;
	private String methodExecuted;
	private Long elapsedTime;
	
	@Lob
	private String requestBody;
	
	@Lob
	private String responseBody;
	
	
}
