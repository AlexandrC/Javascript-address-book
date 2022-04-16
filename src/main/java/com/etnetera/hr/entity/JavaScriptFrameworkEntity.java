package com.etnetera.hr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * Simple data entity describing basic properties of every JavaScript framework.
 * 
 * @author Etnetera
 *
 */
@Entity
@Getter
@Setter
@Table(name = "JAVASCRITPFRAMEWORK")
@NoArgsConstructor @AllArgsConstructor
public class JavaScriptFrameworkEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 30)
	private String name;

	@Column(nullable = false,length = 30)
	private String version;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column()
	private Date date;


	@Override
	public String toString() {
		return "JavaScriptFramework [id=" + id + ", name=" + name + "]";
	}

}
