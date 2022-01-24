package org.example;

import java.time.LocalDateTime;
import javax.inject.Inject;
import javax.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named("TestDelegate")
public class TestDelegate implements JavaDelegate {

  @Inject
  private EngineService engineService;
  private static final Logger logger = LoggerFactory.getLogger(TestDelegate.class);
  @Override
  public void execute(DelegateExecution delegateExecution) throws Exception {
    boolean suspended = engineService.checkSuspensionForActivity(delegateExecution.getCurrentActivityId());
    boolean suspended2 = engineService.checkSuspension2ForActivity(delegateExecution.getCurrentActivityId());
    if(suspended){
      logger.info("Test service {} is suspended",delegateExecution.getCurrentActivityId());
    } else {
      logger.info("Test service {} is not suspended",delegateExecution.getCurrentActivityId());
    }
    if(suspended2){
      logger.info("Test service {} is suspended2",delegateExecution.getCurrentActivityId());
    } else {
      logger.info("Test service {} is not suspended2",delegateExecution.getCurrentActivityId());
    }
    logger.info("Test service {} started at {}", delegateExecution.getCurrentActivityId(), LocalDateTime.now());
    Thread.sleep(30000);
    logger.info("Test service {} finished at {}", delegateExecution.getCurrentActivityId(),LocalDateTime.now());
  }
}
