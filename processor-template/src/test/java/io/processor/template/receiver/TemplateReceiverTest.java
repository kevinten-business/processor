package io.processor.template.receiver;

import io.processor.core.response.StandardProcessorResponse;
import io.processor.template.AppTemplate;
import io.processor.template.context.TemplateContext;
import io.processor.template.error.code.TemplateErrorCodes;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The Template receiver test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppTemplate.class)
public class TemplateReceiverTest {

    @Autowired
    private TemplateReceiver templateReceiver;

    /**
     * Test.
     */
    @Test
    public void test() {
        TemplateContext templateContext = new TemplateContext();
        StandardProcessorResponse response = templateReceiver.receive(templateContext);
        Assert.assertEquals(TemplateErrorCodes.BUSINESS_ERROR.getCode(), response.getCode());
    }
}