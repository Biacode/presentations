package com.barcamp.tdd.test;

import com.barcamp.tdd.helper.ServiceImplTestHelper;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.junit.Ignore;
import org.junit.runner.RunWith;

/**
 * Author: Arthur Asatryan
 * Company: SFL LLC
 * Date: 6/19/16
 * Time: 12:02 AM
 */
@RunWith(EasyMockRunner.class)
@Ignore
public abstract class AbstractServiceImplTest extends EasyMockSupport {

    //region Dependencies
    private final ServiceImplTestHelper serviceImplTestHelper;
    //endregion

    //region Constructors
    public AbstractServiceImplTest() {
        serviceImplTestHelper = new ServiceImplTestHelper();
    }
    //endregion

    //region Public methods
    public ServiceImplTestHelper getServiceImplTestHelper() {
        return serviceImplTestHelper;
    }
    //endregion

}
