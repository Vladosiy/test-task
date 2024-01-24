package org.example.web.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class User {

	@Id
	private Long id;
	private String body;
	private Long postId;
	private String username;
	private LocalDateTime updatedAt;

}
