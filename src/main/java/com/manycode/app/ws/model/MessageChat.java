package com.manycode.app.ws.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.manycode.app.ws.model.ChatMessage.MessageType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="tbl_menssage")
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder
public class MessageChat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String messagechat;
	private String messagetype;
	private String status;
	@Column(name="create_at")		
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
}


