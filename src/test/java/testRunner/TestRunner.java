package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
//run all features: @CucumberOptions(features=".//Features/", glue="stepDefinitions", dryRun=false, monochrome=true, plugin= {"pretty","html:test-output"})
//run 1 feature file: @CucumberOptions(features=".//Features/Customers.feature", glue="stepDefinitions", dryRun=false, monochrome=true, plugin= {"pretty","html:test-output"})
/*run specific feature files: @CucumberOptions(features={".//Features/Customers.feature", ".//Features/Login.feature"}, 
		  glue="stepDefinitions", 
		  dryRun=false, 
		  monochrome=true, 
		  plugin= {"pretty","html:test-output"})*/

/*
 * add just specific scenarios to run (see tags in the feature files)
 * 
 * @CucumberOptions(features=".//Features/", 
		  glue="stepDefinitions", 
		  dryRun=false, 
		  monochrome=true, 
		  plugin= {"pretty","html:test-output"},
		  tags= {"@sanity, @regression"}
		  )
 * 
 * */

@CucumberOptions(features=".//Features/Login.feature", 
		  glue="stepDefinitions", 
		  dryRun=false, 
		  monochrome=true, 
		  plugin= {"pretty","html:test-output"})
public class TestRunner {

}


