All the Listener classes are given in textng.xml file for testing.
maven with surefire and compiler plugin needed along with allure plugin.
for allure execution use coomand ....."Allure serve 'path of Allure results'....
priority for test cases can be given and put enabled = false for skipping the test
allure annotations are:

	@Severity(SeverityLevel.MINOR)	
	@Test(priority=1, description="Verify Logo presence on Home Page")
	@Description("Verify Logo presence on Home Page........")
	@Epic("EP001")
	@Feature("Feature1: Logo")
	@Story("Story:Logo Presence")
	@Step("Verify logo Presence")
