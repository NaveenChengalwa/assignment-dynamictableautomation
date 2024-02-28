package com.naveennaveen.automation.pages;

import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class DynamicTablePage {
    private WebDriver driver;

    @FindBy(xpath = "//summary[.='Table Data']")
    private WebElement tableDataButton;

    @FindBy(id = "jsondata")
    private WebElement dataInput;

    @FindBy(id = "refreshtable")
    private WebElement refreshTableButton;

    @FindBy(id="dynamictable")
    private  WebElement dynamicTable;

    public DynamicTablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickTableDataButton() {
        Assert.assertTrue(tableDataButton.isDisplayed());
        tableDataButton.click();
        Assert.assertTrue(dataInput.isDisplayed());
        Assert.assertTrue(refreshTableButton.isDisplayed());
    }

    public void verifyTableIsDisplayed(){
        Assert.assertTrue(dynamicTable.isDisplayed());
    }

    public void enterData(String jsonData) {
        Assert.assertTrue(dataInput.isEnabled());
        dataInput.clear();
        dataInput.sendKeys(jsonData);
    }

    public void clickRefreshButton() {
        Assert.assertTrue(refreshTableButton.isEnabled());
        refreshTableButton.click();
    }

     public Person[] readTestDataFromFile() {
        try {
            Reader reader = new FileReader("src/main/resources/testdata.json");
            return new Gson().fromJson(reader, Person[].class);
        } catch (Exception e) {
            throw new RuntimeException("Unable to read test data from file", e);
        }
    }





     public void verifyPersonDataInTable(Person[] persons, String[] headers) {
        for (int i = 1; i < persons.length; i++) {
            String name = persons[i-1].getName();
            int age = persons[i-1].getAge();
            String gender = persons[i-1].getGender();

            // Iterate over columns (td elements)
            for (int j = 1; j <= headers.length; j++) {
                String cellValue = dynamicTable.findElement(By.xpath("//tr[" + (i+1) + "]//td[" + j + "]")).getText();
                switch (headers[j - 1]) { // Adjust index to match headers array
                    case "name":
                        Assert.assertEquals(cellValue, name);
                        break;
                    case "age":
                        Assert.assertEquals(cellValue, String.valueOf(age));
                        break;
                    case "gender":
                        Assert.assertEquals(cellValue, gender);
                        break;
                    default:
                        break;
                }
            }
        }
    }


}
