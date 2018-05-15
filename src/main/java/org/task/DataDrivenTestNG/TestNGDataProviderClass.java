package org.task.DataDrivenTestNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGDataProviderClass {
	@DataProvider(name = "TestData")
	public Object[][] testData1() throws Throwable {
		Object[][] testObj = DataReadFromExcel.getValuesFromExcel();
		return testObj;

	}

	@Test(dataProvider = "TestData")
	public void test1(String userName, String password) {
		System.out.println("user Name = " + userName);
		System.out.println("password" + password);

	}
}