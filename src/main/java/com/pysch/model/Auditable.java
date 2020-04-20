package com.pysch.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator=ObjectIdGenerators.StringIdGenerator.class,property="id")
public abstract class Auditable implements Serializable{
	@Id
	@GeneratedValue(generator="sequence",strategy=GenerationType.IDENTITY)
	@SequenceGenerator(name="sequence",allocationSize=10)
	@Getter
	@Setter
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(nullable=false,updatable=false)
	@Getter
	@Setter
	private Date createdAt=new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(nullable=false)
	@Getter
	@Setter
	private Date updatedAt=new Date();
}


