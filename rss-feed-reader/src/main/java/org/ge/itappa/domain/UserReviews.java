package org.ge.itappa.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="user_reviews")
@JsonPropertyOrder({"username", "reviewContent", "date", "rating"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserReviews implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(hidden = true)
	private Long id;
	
	private String username;
	
	private String reviewContent;
	
	@ApiModelProperty(hidden = true)
	@CreationTimestamp
	private Date date;
	
	
	

}
