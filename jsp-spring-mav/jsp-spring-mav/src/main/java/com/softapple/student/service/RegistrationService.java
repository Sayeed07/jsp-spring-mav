package com.softapple.student.service;

import java.awt.Paint;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.softapple.model.entity.Student;
import com.softapple.model.entity.Teacher;
import com.softapple.repository.StudentRepository;
import com.softapple.repository.TeacherRepository;
import com.softapple.student.model.dto.StudentDTO;
import com.softapple.student.model.dto.TeacherDTO;

import javafx.scene.paint.Color;

@Service
@Transactional
public class RegistrationService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	private static final String customStudentIdPrefix1 = "CSE18";
	private static final Long customStudentIdPrefix2 = 100000L;

	public void saveStudent(StudentDTO studentDTO, MultipartFile photo) {
		studentDTO.setPhotoName(writeFile(photo));
		studentRepository.save(copyStudentDtoToStudent(studentDTO));
	}

	public String writeFile(MultipartFile photo) {
		try {
			Files.write(Paths.get("D:/images" + File.separator + "abc" + photo.getOriginalFilename()),
					photo.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "abc" + photo.getOriginalFilename();
	}

	public void updateStudent(StudentDTO studentDTO, MultipartFile photo) {
		Student student = studentRepository.findOne(studentDTO.getStudentId());
		if (!studentDTO.getPhotoName().equals(null) || !studentDTO.getPhotoName().equals("")) {
			student.setPhotoName(writeFile(photo));
		}
		studentRepository.save(copyStudentDtoToStudent(studentDTO, student));

	}

	public Student copyStudentDtoToStudent(StudentDTO studentDTO, Student student) {
		student.setStudentName(studentDTO.getPhotoName());
		student.setStudentRoll(studentDTO.getStudentRoll());
		return student;
	}

	public List<Student> findStdByStdNameAndTName(String studentName, String teacherName) {
		return studentRepository.findByStudentNameAndTeacher_TeacherName(studentName, teacherName);
	}

	public List<StudentDTO> findAllStudent() {
		List<Student> students = studentRepository.findAll();
		List<StudentDTO> studentDTOs = new ArrayList<>();
		// return students.stream().map(student ->
		// copyStudentToStudentDTO(student)).collect(Collectors.toList());
		for (Student student : students) {
			studentDTOs.add(copyStudentToStudentDTO(student));
		}
		return studentDTOs;
	}

	public List<TeacherDTO> findAllTeachers() {
		List<TeacherDTO> teacherDTOs = new ArrayList<>();
		List<Teacher> teachers = teacherRepository.findAll();
		for (Teacher teacher : teachers) {
			TeacherDTO teacherDTO = new TeacherDTO();
			BeanUtils.copyProperties(teacher, teacherDTO);
			teacherDTOs.add(teacherDTO);
		}
		return teacherDTOs;
	}

	public void deleteStudent(Long studentId) {
		studentRepository.delete(studentId);
	}

	public PieDataset createPieDataSet() {
		DefaultPieDataset dataSet = new DefaultPieDataset();
		dataSet.setValue("One", 10);
		dataSet.setValue("Two", 15);
		dataSet.setValue("Three", 30);
		dataSet.setValue("Four", 20);
		dataSet.setValue("Five", 25);
		return dataSet;
	}

	public DefaultCategoryDataset createBarDataSet() {
		final String classOne = "One";
		final String classTwo = "Two";
		final String classThree = "Three";
		final String numberOfStudents = "Number Of Students";
		
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(100, "values", classOne);
		
		dataset.addValue(70, "values", classTwo);
		
		dataset.addValue(40, "values", classThree);
			return dataset;
	}

	public JFreeChart createChart(String chartTitle) {
		JFreeChart chart = ChartFactory.createPieChart3D(chartTitle, createPieDataSet(), true, true, false);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setBackgroundAlpha(0.5f);
		return chart;
	}

	public JFreeChart createBarChart() {
		JFreeChart chart = 
	ChartFactory.createBarChart("Test Title", "Class", "Number Of Students", createBarDataSet(),
				PlotOrientation.HORIZONTAL, true, true, true);
		return chart;
	}

	public Teacher provideTeacher(Long teacherId) {
		Teacher teacher = new Teacher();
		teacher.setTeacherId(teacherId);
		return teacher;
	}

	public StudentDTO findStudentById(Long studentId) {
		return copyStudentToStudentDTO(studentRepository.findOne(studentId));
	}

	public StudentDTO copyStudentToStudentDTO(Student student) {
		StudentDTO studentDTO = new StudentDTO();
		BeanUtils.copyProperties(student, studentDTO);
		return studentDTO;
	}

	public Student copyStudentDtoToStudent(StudentDTO studentDTO) {
		Student student = new Student();
		BeanUtils.copyProperties(studentDTO, student);
		student.setTeacher(provideTeacher(studentDTO.getTeacherId()));
		student.setCustomStudentId(prepareCustomStudentId());
		return student;
	}

	public String prepareCustomStudentId() {
		List<Student> students = studentRepository.findAll();
		Long maxStudentId=1L;
		Long maxStdId = customStudentIdPrefix2 + maxStudentId;
		if(!students.isEmpty()) {
			maxStudentId = students.get(students.size() - 1).getStudentId();
			maxStudentId++;
			maxStdId = customStudentIdPrefix2 + maxStudentId;
		}
		
		return customStudentIdPrefix1 + maxStdId;

	}

}
