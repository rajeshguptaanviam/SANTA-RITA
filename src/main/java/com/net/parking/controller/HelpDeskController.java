package com.net.parking.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/support")
public class HelpDeskController {

	private static Logger logger = Logger.getLogger(HelpDeskController.class);
	private ClassLoader classLoader;

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public void openPDF(HttpServletRequest req, HttpServletResponse res) {
		try {
			classLoader = getClass().getClassLoader();
			String path  = classLoader.getResource("SKIDATA.pdf").getPath();
			System.out.println(path);
			res.setContentType("application/pdf");
			res.setHeader("Expires", "0");
			res.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			res.setHeader("Content-Disposition", "inline;filename=" + "SKIDATA.pdf");
			res.setHeader("Accept-Ranges", "bytes");
			File nfsPDF = new File(path);
			FileInputStream fis = new FileInputStream(nfsPDF);
			BufferedInputStream bis = new BufferedInputStream(fis);
			ServletOutputStream sos = res.getOutputStream();
			byte[] buffer = new byte[2048];
			while (true) {
				int bytesRead = bis.read(buffer, 0, buffer.length);
				if (bytesRead < 0) {
					break;
				}
				sos.write(buffer, 0, bytesRead);
				sos.flush();
			}
			sos.flush();
			bis.close();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
		}
	}
}
