package com.naveennaveen.automation.tests;

import com.google.gson.Gson;
import com.naveennaveen.automation.base.BaseTest;
import com.naveennaveen.automation.base.DriverManager;
import com.naveennaveen.automation.pages.DynamicTablePage;
import com.naveennaveen.automation.pages.Person;
import org.testng.annotations.Test;


public class DynamicTableTest extends BaseTest {

        @Test
        public void navigateToLoginPage(){
            DynamicTablePage dynamicTablePage = new DynamicTablePage(DriverManager.getDriver());
            dynamicTablePage.verifyTableIsDisplayed();
            //Click on Table Data button , verify new input text box will be display
            dynamicTablePage.clickTableDataButton();
            //fetch the data from json file
            Person[] data = dynamicTablePage.readTestDataFromFile();
            //enter the json data into text box
            dynamicTablePage.enterData(new Gson().toJson(data));
            //click on Refresh Button
            dynamicTablePage.clickRefreshButton();
            //verify the table data that should match with json data
            dynamicTablePage.verifyPersonDataInTable(data, new String[]{"name", "age", "gender"});
        }
}

