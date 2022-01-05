package com.lawencon.lms.dto.histories;

public class HistoriesReportResDto {

	private String usersEmail;
	
	private String employeesCode;
	
	private String employeesName;
	
	private String assetsName;
	
	private String activityName;

	public String getUsersEmail() {
		return usersEmail;
	}

	public void setUsersEmail(String usersEmail) {
		this.usersEmail = usersEmail;
	}

	public String getEmployeesCode() {
		return employeesCode;
	}

	public void setEmployeesCode(String employeesCode) {
		this.employeesCode = employeesCode;
	}

	public String getEmployeesName() {
		return employeesName;
	}

	public void setEmployeesName(String employeesName) {
		this.employeesName = employeesName;
	}

	public String getAssetsName() {
		return assetsName;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

}
