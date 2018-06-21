package com.shixzh.bcms.framework.enums;

import org.junit.Assert;
import org.junit.Test;

public class TestPayrollEnum {
    @Test
    public void testPay() {
        double payrollWeekday = PayrollDayEnum.MONDAY.pay(15, 75);
        System.out.println(payrollWeekday);
        Assert.assertEquals(1387.5, payrollWeekday, 0.001);
        double payrollWeekend = PayrollDayEnum.SATURDAY.pay(8, 75);
        System.out.println(payrollWeekend);
        Assert.assertEquals(900, payrollWeekend, 0.001);
    }
}
