package com.project.professorallocation.model;

import java.time.DayOfWeek;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "allocation")
public class Allocation {
	@JsonProperty(access = Access.READ_ONLY)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "day")
	private DayOfWeek dayofweek;
	
	@ApiModelProperty(example = "15:00-0300")
	@JsonFormat(pattern = "HH:mmZ", timezone = "America/Recife")
	@JsonSerialize(using = DateSerializer.class)
	@JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date startHour;
	
	@ApiModelProperty(example = "15:00-0300")
	@JsonFormat(pattern = "HH:mmZ", timezone = "America/Recife")
	@JsonSerialize(using = DateSerializer.class)
	@JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
	@Column(nullable = false)
	private Date endHour;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "professor_id", nullable = false)
	private Long professorId;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "professor_id", nullable = false, insertable = false, updatable = false)
	private Professor professor;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "course_id", nullable = false)
	private Long courseId;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "course_id", nullable = false, insertable = false, updatable = false)
	private Course course;

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Allocation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DayOfWeek getDayofweek() {
		return dayofweek;
	}

	public void setDayofweek(DayOfWeek dayofweek) {
		this.dayofweek = dayofweek;
	}

	public Date getStartHour() {
		return startHour;
	}

	public void setStartHour(Date startHour) {
		this.startHour = startHour;
	}

	public Date getEndHour() {
		return endHour;
	}

	public void setEndHour(Date endHour) {
		this.endHour = endHour;
	}

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "Allocation [id=" + id + ", dayofweek=" + dayofweek + ", startHour=" + startHour + ", endHour=" + endHour
				+ ", professorId=" + professorId + ", courseId=" + courseId + "]";
	}


	
}
