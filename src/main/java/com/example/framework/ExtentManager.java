package com.example.framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(ConfigReader.getProperty("report.path") + "extent-report.html");
            sparkReporter.config().setDocumentTitle(ConfigReader.getProperty("report.title"));
            sparkReporter.config().setReportName(ConfigReader.getProperty("report.name"));
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }
}
