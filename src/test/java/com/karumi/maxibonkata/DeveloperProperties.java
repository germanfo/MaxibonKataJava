package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.java.lang.IntegerGenerator;
import com.pholser.junit.quickcheck.generator.java.lang.StringGenerator;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Before;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(JUnitQuickcheck.class)
public class DeveloperProperties {

    private Developer developer;

    @Before
    public void setUp(){

    }

    //@From(IntegerGenerator.class) NO ES NECESARIO PONERLO PORQUE ES UN TIPO PRIMITIVO
    @Property public void theNumberOfMaxibonsToGrabShouldNeverBeNegative(@From(IntegerGenerator.class) int numberOfMaxibonsToGrab){
        developer = new Developer("PEPE", numberOfMaxibonsToGrab);

        System.out.println(numberOfMaxibonsToGrab);
        assertTrue(developer.getNumberOfMaxibonsToGrab() >= 0);

    }

    @Property public void theNameOfDeveloperIsTheSameAfterSettingIt(@From(StringGenerator.class) String nameOfDeveloper) {
        developer = new Developer(nameOfDeveloper, 0);

        System.out.println(nameOfDeveloper);

        assertEquals(nameOfDeveloper, developer.getName());
    }
}
