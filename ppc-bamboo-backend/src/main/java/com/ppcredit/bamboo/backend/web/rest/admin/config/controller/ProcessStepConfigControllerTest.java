package com.ppcredit.bamboo.backend.web.rest.admin.config.controller;

import com.ppcredit.bamboo.backend.BambooBackendserviceApp;
import com.ppcredit.bamboo.backend.web.rest.admin.config.service.ProcessStepConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.junit.Assert.fail;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BambooBackendserviceApp.class)
@ActiveProfiles("dev")
public class ProcessStepConfigControllerTest {

	@Inject
	private ProcessStepConfigService processStepConfigService;
	@Test
	public void testQueryApiList() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveInterface() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveInput() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveLogic() {
		fail("Not yet implemented");
	}
	@Test
	public void classLoad() {
		processStepConfigService.delRefenrenceArg();
	}


}
