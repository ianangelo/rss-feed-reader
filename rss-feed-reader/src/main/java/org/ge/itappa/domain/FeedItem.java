package org.ge.itappa.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="item")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeedItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	private String link;
	
	private String description1;
	
	private String description2;
	
	private String description3;
	
	private Date updatedDate;
	
	private String zonedDateRetrieved;

}
