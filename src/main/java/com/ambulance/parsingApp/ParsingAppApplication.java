package com.ambulance.parsingApp;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class ParsingAppApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ParsingAppApplication.class, args);
	}


}
