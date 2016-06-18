package com.barcamp.tdd.test;

import com.barcamp.tdd.TddApplication;
import com.barcamp.tdd.helper.ServiceIntegrationTestHelper;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Author: Arthur Asatryan
 * Company: SFL LLC
 * Date: 6/19/16
 * Time: 12:11 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TddApplication.class)
@Ignore
public abstract class AbstractServiceIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    //region Dependencies
    @Autowired
    private ServiceIntegrationTestHelper serviceIntegrationTestHelper;
    //endregion

    //region Constructors
    public AbstractServiceIntegrationTest() {
    }
    //endregion

    //region Public methods
    public ServiceIntegrationTestHelper getServiceIntegrationTestHelper() {
        return serviceIntegrationTestHelper;
    }
    //endregion

}
