package com.hariyali.hariyali_project.service;

import java.io.FileNotFoundException;

import net.sf.jasperreports.engine.JRException;

public interface JasperReportService {

	public void generatePdf(String reciptNo) throws FileNotFoundException, JRException;
	
	public void generateCertificate(String certiNo,int userId)throws FileNotFoundException, JRException;
	
}
