package com.ppcredit.bamboo.backend.web.rest.admin.step.controller;


import javax.inject.Inject;

import com.ppcredit.bamboo.backend.web.rest.type.StepType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ppcredit.bamboo.backend.BambooBackendserviceApp;
import com.ppcredit.bamboo.backend.web.rest.admin.step.service.ProcessStepService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BambooBackendserviceApp.class)
public class ProcessStepControllerTest {

	@Inject
	private ProcessStepService myProcessStepService;
	
	@Test
	public void testSave() throws Exception {
			myProcessStepService.save("7f7a3ae976de45f6bce86c76fe77cd6c","7f7a3ae976de45f6bce86c76fe77cd6c", StepType.INPUT, "用户输入信息", "1", "2");
	}
	@Test
	public void queryStepBystepId() {
			myProcessStepService.queryStepBystepId("9997f748dda64e949aa5531786198f60");
	}
}
