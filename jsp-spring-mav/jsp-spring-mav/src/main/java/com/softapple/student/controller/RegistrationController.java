package com.softapple.student.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.softapple.student.model.dto.StudentDTO;
import com.softapple.student.service.RegistrationService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(value = "/student/save", method = RequestMethod.POST)
	public String saveStudent(StudentDTO studentDTO, MultipartFile photo, Model model) {
		registrationService.saveStudent(studentDTO, photo);
		return "redirect:/registration/student/find-all";
	}

	@RequestMapping(value = "/student/update", method = RequestMethod.POST)
	public String updateStudent(StudentDTO studentDTO, MultipartFile photo, Model model) {
		registrationService.saveStudent(studentDTO, photo);
		return "redirect:/registration/student/find-all";
	}

	@RequestMapping(value = "/student/find-all", method = RequestMethod.GET)
	public String findAllStudent(Model model) {
		model.addAttribute("students", registrationService.findAllStudent());
		model.addAttribute("teachers", registrationService.findAllTeachers());
		return "student";
	}

	@RequestMapping(value = "/student/edit", method = RequestMethod.GET)
	public String findStudentById(@RequestParam Long studentId, Model model) {
		model.addAttribute("student", registrationService.findStudentById(studentId));
		model.addAttribute("teachers", registrationService.findAllTeachers());
		return "std-update";
	}

	@RequestMapping(value = "/student/delete", method = RequestMethod.GET)
	public String deleteStudentById(@RequestParam Long studentId) {
		registrationService.deleteStudent(studentId);
		return "redirect:/registration/student/find-all";
	}

	@RequestMapping(value = "/teacher/find-all", method = RequestMethod.GET)
	public String findAllTeachers(Model model) {
		model.addAttribute("teachers", registrationService.findAllTeachers());
		return "student";
	}

	@RequestMapping(value = "/pie-chart", method = RequestMethod.GET)
	public void draPiechart(HttpServletResponse response, Model model) {
		try {
			Files.write(Paths.get("D:/images" + File.separator + "my_chart_pie.jpg"),
					ChartUtilities.encodeAsPNG(registrationService
							.createChart("Test Pie Chart")
							.createBufferedImage(500, 300)));
			
			Files.write(Paths.get("D:/images" + File.separator + "my_chart_bar.PNG"),
					ChartUtilities.encodeAsPNG(registrationService
							.createBarChart().createBufferedImage(600, 200)));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
